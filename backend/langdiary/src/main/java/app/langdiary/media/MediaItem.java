package app.langdiary.media;

import app.langdiary.progress_tracking.ProgressDefinition;
import app.langdiary.progress_tracking.ProgressUpdateLog;
import app.langdiary.users.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "media_items")
public class MediaItem {

    @Id
    @GeneratedValue
    private Integer id;

    @Column (nullable = false)
    private String title;

    private Integer rating;

    private String language;

    @Enumerated(EnumType.STRING)
    private MainSkill mainSkill;

    @Column (nullable = false)
    private boolean deleted = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    private LocalDateTime updatedAt;

    @Embedded
    private ProgressDefinition progressDefinition;

    @Embedded
    private StudyMethod studyMethod;

    @ElementCollection
    @CollectionTable(name = "additional_media_properties",
            joinColumns = @JoinColumn(name = "media_item_id"))
    @MapKeyColumn(name = "property_key")
    @Column(name = "property_value")
    private Map<String, String> additionalProperties = new HashMap<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "media_type_id")
    private TypeOfMedia typeOfMedia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public MediaItem() {
    }

    public MediaItem(String title, MainSkill mainSkill, TypeOfMedia typeOfMedia, User user) {
        this.title = title;
        this.mainSkill = mainSkill;
        this.typeOfMedia = typeOfMedia;
        this.user = user;
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof MediaItem that)) return false;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", language='" + language + '\'' +
                ", mainSkill=" + mainSkill +
                ", deleted=" + deleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", progressDefinition=" + progressDefinition +
                ", studyMethod=" + studyMethod +
                ", typeOfMedia=" + typeOfMedia +
                '}';
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public MainSkill getMainSkill() {
        return mainSkill;
    }

    public void setMainSkill(MainSkill mainSkill) {
        this.mainSkill = mainSkill;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ProgressDefinition getProgressDefinition() {
        return progressDefinition;
    }

    public void setProgressDefinition(ProgressDefinition progressDefinition) {
        this.progressDefinition = progressDefinition;
    }

    public StudyMethod getStudyMethod() {
        return studyMethod;
    }

    public void setStudyMethod(StudyMethod studyMethod) {
        this.studyMethod = studyMethod;
    }

    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperty(String key, String value) {
        this.additionalProperties.put(key, value);
    }

    public TypeOfMedia getTypeOfMedia() {
        return typeOfMedia;
    }

    public void setTypeOfMedia(TypeOfMedia typeOfMedia) {
        this.typeOfMedia = typeOfMedia;
    }

    public User getUser() {
        return user;
    }
}
