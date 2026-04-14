package app.langdiary.features.studylog;

import app.langdiary.progress_tracking.ProgressUpdateLogRepository;
import app.langdiary.progress_tracking.TimeFrame;
import app.langdiary.progress_tracking.TimeFrameProgressSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static app.langdiary.utils.DateTimeUtils.getChronoUnitForTimeFrame;

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

        LocalDate startBound = startDate.toLocalDate();
        LocalDate endBound = endDate.toLocalDate();

        return rawProgress.stream().map(raw -> mapToMediaMonthDTO(raw, startBound, endBound)).collect((Collectors.toList()));
    }

    public List<MediaInTimeframeResponseDTO> getMediaInTimeframe(String username, LocalDate startDate, TimeFrame timeframe) {
        List<RawMediaProgressProjection> rawProgress = timeFrameProgressSummaryRepository.findRawProgressForMonth(username, startDate, timeframe);

        ChronoUnit unitToAdd = getChronoUnitForTimeFrame(timeframe);
        LocalDate endBound = startDate.plus(1, unitToAdd);

        return rawProgress.stream().map(raw -> mapToMediaMonthDTO(raw, startDate, endBound)).collect((Collectors.toList()));
    }

    private MediaInTimeframeResponseDTO mapToMediaMonthDTO(RawMediaProgressProjection raw, LocalDate startBound, LocalDate endBound) {
        Integer startAbsolute = raw.getProgressAtStart();
        Integer endAbsolute = raw.getProgressAtEnd();
        Integer maxValue = raw.getMaxValue();

        Integer startPercent = null;
        Integer endPercent = null;

        if (startAbsolute != null && endAbsolute != null && maxValue != null && maxValue > 0) {
            startPercent = (int) Math.round(((float) startAbsolute / maxValue) * 100);
            endPercent = (int) Math.round(((float) endAbsolute / maxValue) * 100);
        }

        boolean wasCompletedInTimeFrame = false;
        LocalDate finishedDate = raw.getFinishedDate();
        if (finishedDate != null) {
            wasCompletedInTimeFrame = !finishedDate.isBefore(startBound) && !finishedDate.isAfter(endBound);
        }

        return new MediaInTimeframeResponseDTO(raw.getTitle(), raw.getType(), raw.getMainSkill(), raw.getRating(), startPercent, endPercent, wasCompletedInTimeFrame);
    }
}