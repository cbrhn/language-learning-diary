package app.langdiary.progress_tracking;

import app.langdiary.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

import app.langdiary.media.MediaItem;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Table(name = "progress_update_logs")
public class ProgressUpdateLog {

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

    @Column(
        nullable = false
    )
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private int progressValue;

    public ProgressUpdateLog() {
    }

    public ProgressUpdateLog(MediaItem mediaItem, User user, LocalDateTime updatedAt, int progressValue) {
        this.mediaItem = mediaItem;
        this.user = user;
        this.updatedAt = updatedAt;
        this.progressValue = progressValue;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof ProgressUpdateLog that)) return false;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ProgressUpdateLog{" +
                "updatedAt=" + updatedAt +
                ", progressValue=" + progressValue +
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(int progressValue) {
        this.progressValue = progressValue;
    }
}
