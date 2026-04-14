package app.langdiary.features.studylog;

import app.langdiary.progress_tracking.ProgressUpdateLogRepository;
import app.langdiary.progress_tracking.TimeFrame;
import app.langdiary.progress_tracking.TimeFrameProgressSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyLogService {

    private ProgressUpdateLogRepository progressUpdateLogRepository;
    private TimeFrameProgressSummaryRepository timeFrameProgressSummaryRepository;

    public StudyLogService(@Autowired ProgressUpdateLogRepository progressUpdateLogRepository, @Autowired TimeFrameProgressSummaryRepository timeFrameProgressSummaryRepository) {
        this.progressUpdateLogRepository = progressUpdateLogRepository;
        this.timeFrameProgressSummaryRepository = timeFrameProgressSummaryRepository;
    }

    public List<MediaInTimeframeResponseDTO> getMediaInTimeframe(String username, LocalDateTime startDate, LocalDateTime endDate) {
        List<RawMediaProgressProjection> rawProgress = progressUpdateLogRepository.findRawProgressForMonth(username, startDate, endDate);

        return rawProgress.stream().map(this::mapToMediaMonthDTO).collect(Collectors.toList());
    }

    public List<MediaInTimeframeResponseDTO> getMediaInTimeframe(String username, LocalDate startDate, TimeFrame timeframe) {
        List<RawMediaProgressProjection> rawProgress = timeFrameProgressSummaryRepository.findRawProgressForMonth(username, startDate, timeframe);

        return rawProgress.stream().map(this::mapToMediaMonthDTO).collect(Collectors.toList());
    }

    private MediaInTimeframeResponseDTO mapToMediaMonthDTO(RawMediaProgressProjection raw) {
        Integer startAbsolute = raw.getProgressAtStart();
        Integer endAbsolute = raw.getProgressAtEnd();
        Integer maxValue = raw.getMaxValue();

        Integer startPercent = null;
        Integer endPercent = null;

        if (startAbsolute != null && endAbsolute != null && maxValue != null && maxValue > 0) {
            startPercent = (int) Math.round(((float) startAbsolute / maxValue) * 100);
            endPercent = (int) Math.round(((float) endAbsolute / maxValue) * 100);
        }

        return new MediaInTimeframeResponseDTO(raw.getTitle(), raw.getType(), raw.getMainSkill(), raw.getRating(), startPercent, endPercent);
    }
}