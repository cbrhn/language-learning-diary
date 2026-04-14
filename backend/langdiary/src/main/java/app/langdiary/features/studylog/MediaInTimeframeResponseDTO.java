package app.langdiary.features.studylog;

public record MediaInTimeframeResponseDTO(String title, String type, String mainSkill, Integer rating, Integer startPercent, Integer endPercent, Boolean wasCompletedInTimeFrame) {}
