package app.langdiary.progress_tracking;

import jakarta.persistence.Embeddable;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Embeddable
public class ProgressDefinition {

    private String metricType;

    private Integer maxValue;

    public ProgressDefinition() {
    }

    public ProgressDefinition(String metricType, int maxValue) {
        this.metricType = metricType;
        this.maxValue = maxValue;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof ProgressDefinition that)) return false;
        return getMetricType() != null && Objects.equals(getMetricType(), that.getMetricType())
                && getMaxValue() != null && Objects.equals(getMaxValue(), that.getMaxValue());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(metricType, maxValue);
    }

    @Override
    public String toString() {
        return "ProgressDefinition{" +
                "metricType='" + metricType + '\'' +
                ", maxValue=" + maxValue +
                '}';
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }
}
