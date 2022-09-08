package software.sigma.internship.dto;

import java.time.LocalDate;

public record StatisticsRecordDto(
        LocalDate date,
        int day,
        StatisticDataDto stats) {
}
