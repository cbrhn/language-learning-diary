package app.langdiary.progress_tracking;

import app.langdiary.features.studylog.RawMediaProgressProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProgressUpdateLogRepository extends JpaRepository<ProgressUpdateLog, Integer> {

    @Query("""
    SELECT
        m.id AS mediaItemId,
        m.title AS title,
        m.mainSkill AS mainSkill,
        tom.name AS type,
        m.progressDefinition.maxValue AS maxValue,
        m.rating AS rating,
        MIN(p.progressValue) AS progressAtStart,
        MAX(p.progressValue) AS progressAtEnd
    FROM ProgressUpdateLog p
    JOIN p.mediaItem m
    JOIN m.typeOfMedia tom
    WHERE p.user.username = :user
        AND p.updatedAt BETWEEN :startDate AND :endDate
    GROUP BY m.id, m.title, m.mainSkill, tom.name, m.progressDefinition.maxValue, m.rating
    """)
    List<RawMediaProgressProjection> findRawProgressForMonth(
            @Param("user") String user,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
