package app.langdiary.features.studylog;

import app.langdiary.media.MainSkill;

public record MetricCounterResponseDTO(String metricName, Long value, MainSkill mainSkill) {}
