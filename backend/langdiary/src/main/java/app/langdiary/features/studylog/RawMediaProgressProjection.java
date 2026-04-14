package app.langdiary.features.studylog;

public interface RawMediaProgressProjection {
    Integer getMediaItemId();
    String getTitle();
    String getType();
    String getMainSkill();
    Integer getProgressAtStart();
    Integer getProgressAtEnd();
    Integer getMaxValue();
    Integer getRating();
}