package app.langdiary.media;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Embeddable
public class StudyMethod {

    private Boolean hasSubtitles;
    private Boolean hasAudio;
    private Boolean hasVisuals;

    @Enumerated(EnumType.STRING)
    private StudyIntensity studyIntensity;

    public StudyMethod() {
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof StudyMethod that)) return false;
        return getHasSubtitles() != null && Objects.equals(getHasSubtitles(), that.getHasSubtitles())
                && getHasAudio() != null && Objects.equals(getHasAudio(), that.getHasAudio())
                && getHasVisuals() != null && Objects.equals(getHasVisuals(), that.getHasVisuals())
                && getStudyIntensity() != null && Objects.equals(getStudyIntensity(), that.getStudyIntensity());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(hasSubtitles,
                hasAudio,
                hasVisuals,
                studyIntensity);
    }

    @Override
    public String toString() {
        return "StudyMethod{" +
                "hasSubtitles=" + hasSubtitles +
                ", hasAudio=" + hasAudio +
                ", hasVisuals=" + hasVisuals +
                ", studyIntensity=" + studyIntensity +
                '}';
    }

    public Boolean getHasSubtitles() {
        return hasSubtitles;
    }

    public void setHasSubtitles(Boolean hasSubtitles) {
        this.hasSubtitles = hasSubtitles;
    }

    public Boolean getHasAudio() {
        return hasAudio;
    }

    public void setHasAudio(Boolean hasAudio) {
        this.hasAudio = hasAudio;
    }

    public Boolean getHasVisuals() {
        return hasVisuals;
    }

    public void setHasVisuals(Boolean hasVisuals) {
        this.hasVisuals = hasVisuals;
    }

    public StudyIntensity getStudyIntensity() {
        return studyIntensity;
    }

    public void setStudyIntensity(StudyIntensity studyIntensity) {
        this.studyIntensity = studyIntensity;
    }
}


