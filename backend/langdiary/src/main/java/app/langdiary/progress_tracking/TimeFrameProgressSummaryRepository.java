package app.langdiary.progress_tracking;

import app.langdiary.features.studylog.MetricCounterResponseDTO;
import app.langdiary.features.studylog.RawMediaProgressProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TimeFrameProgressSummaryRepository extends JpaRepository<TimeFrameProgressSummary, Integer> {

    @Query("""
    SELECT
        m.id AS mediaItemId,
        m.title AS title,
        m.mainSkill AS mainSkill,
        tom.name AS type,
        m.progressDefinition.maxValue AS maxValue,
        m.rating AS rating,
        m.finishedDate AS finishedDate,
        s.progressAtStart AS progressAtStart,
        s.progressAtEnd AS progressAtEnd
    FROM TimeFrameProgressSummary s
    JOIN s.mediaItem m
    JOIN m.typeOfMedia tom
    WHERE s.user.username = :user
        AND s.timeFrame = :timeframe
        AND s.startingPoint = :startDate
    """)
    List<RawMediaProgressProjection> findRawProgressForMonth(
            @Param("user") String user,
            @Param("startDate") LocalDate startDate,
            @Param("timeframe") TimeFrame timeframe
    );

    @Query("""
   
    SELECT new app.langdiary.features.studylog.MetricCounterResponseDTO(
        m.progressDefinition.metricType.name,
        SUM(s.progressAtEnd - s.progressAtStart),
        m.mainSkill
        )
    FROM TimeFrameProgressSummary s
    JOIN s.mediaItem m
    WHERE s.user.username = :user
        AND s.timeFrame = :timeframe
        AND s.startingPoint = :startDate
    GROUP BY m.mainSkill, m.progressDefinition.metricType.name
    """)
    List<MetricCounterResponseDTO> getRawMetricInfo(
            @Param("user") String user,
            @Param("startDate") LocalDate startDate,
            @Param("timeframe") TimeFrame timeframe
    );

}
