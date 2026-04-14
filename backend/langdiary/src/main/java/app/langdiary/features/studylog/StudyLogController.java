package app.langdiary.features.studylog;

import app.langdiary.progress_tracking.TimeFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class StudyLogController {

    private final StudyLogService studyLogService;

    public StudyLogController (@Autowired StudyLogService studyLogService) {
        this.studyLogService = studyLogService;
    }

    @GetMapping(value = "/api/{username}/report", params = {"startDate", "endDate"})
    @PreAuthorize("#username == authentication.name")
    public ResponseEntity<List<MediaInTimeframeResponseDTO>> getMediaByTimeframe (
            @PathVariable String username,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        return ResponseEntity.ok(studyLogService.getMediaInTimeframe(username, startDateTime, endDateTime));
    }

    @GetMapping(value = "/api/{username}/report", params = {"startDate", "timeframe"})
    @PreAuthorize("#username == authentication.name")
    public ResponseEntity<List<MediaInTimeframeResponseDTO>> getMediaForStudyLog (
            @PathVariable String username,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam TimeFrame timeframe
    ) {

        return ResponseEntity.ok(studyLogService.getMediaInTimeframe(username, startDate, timeframe));
    }

}
