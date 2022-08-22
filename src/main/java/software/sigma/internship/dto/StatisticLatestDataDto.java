package software.sigma.internship.dto;

import java.time.LocalDate;

public record StatisticLatestDataDto(
        LocalDate date,
        int day,
        StatisticDataDto increase) {
}
