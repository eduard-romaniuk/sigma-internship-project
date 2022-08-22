package software.sigma.internship.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record FundDto(long id,
                      @NotNull @NotBlank String name,
                      @NotNull @NotBlank String description,
                      @NotNull @NotBlank String link) {
}
