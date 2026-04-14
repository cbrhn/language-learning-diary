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
@Table(name = "time_frame_progress_summaries", indexes = {
        //@Index(name = "idx_date", columnList = "startingPoint"), // todo
        @Index(name = "idx_timeframe", columnList = "startingPoint, timeFrame")
})
public class TimeFrameProgressSummary {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MediaItem mediaItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private LocalDate startingPoint;

    @Enumerated(EnumType.STRING)
    private TimeFrame timeFrame;

    private Integer progressAtStart;
    private Integer progressAtEnd;

    public TimeFrameProgressSummary() {
    }

    public TimeFrameProgressSummary(MediaItem mediaItem, User user, LocalDate startingPoint, TimeFrame timeframe, Integer progressAtStart) {
        this.mediaItem = mediaItem;
        this.user = user;
        this.startingPoint = startingPoint;
        this.timeFrame = timeframe;
        this.progressAtStart = progressAtStart;
        // At the beginning, there is no progress yet
        this.progressAtEnd = progressAtStart;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof TimeFrameProgressSummary that)) return false;
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
                ", timeframe=" + timeFrame + // todo: convert properly
                ", progressAtStart=" + progressAtStart +
                ", progressAtEnd=" + progressAtEnd +
                '}';
    }

    public Long getId() {
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

    public LocalDate getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(LocalDate startingPoint) {
        this.startingPoint = startingPoint;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
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
