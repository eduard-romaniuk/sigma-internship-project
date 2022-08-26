package software.sigma.internship.dto;

import java.time.LocalDate;

public record LossDayDto(LocalDate date, Integer lossAmount) {
}
