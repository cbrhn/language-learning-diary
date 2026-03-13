package app.langdiary.progress_tracking;

import app.langdiary.media.MediaItem;
import app.langdiary.users.User;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "monthly_progress_summaries", indexes = {
        @Index(name = "idx_month", columnList = "month")
})
public class MonthlyProgressSummary {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MediaItem mediaItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private LocalDate month;

    private Integer progressAtStart;
    private Integer progressAtEnd;

    public MonthlyProgressSummary() {
    }

    public MonthlyProgressSummary(MediaItem mediaItem, User user, LocalDate month, Integer progressAtStart) {
        this.mediaItem = mediaItem;
        this.user = user;
        this.month = month;
        this.progressAtStart = progressAtStart;
        // At the beginning, there is no progress yet
        this.progressAtEnd = progressAtStart;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof MonthlyProgressSummary that)) return false;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "MonthlyProgressSummary{" +
                "id=" + id +
                ", month=" + month +
                ", progressAtStart=" + progressAtStart +
                ", progressAtEnd=" + progressAtEnd +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public MediaItem getMediaItem() {
        return mediaItem;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public Integer getProgressAtStart() {
        return progressAtStart;
    }

    public void setProgressAtStart(Integer progressAtStart) {
        this.progressAtStart = progressAtStart;
    }

    public Integer getProgressAtEnd() {
        return progressAtEnd;
    }

    public void setProgressAtEnd(Integer progressAtEnd) {
        this.progressAtEnd = progressAtEnd;
    }
}
