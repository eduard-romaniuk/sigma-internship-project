package software.sigma.internship.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record LocaleDto(long id,
                        @NotNull @NotBlank String name,
                        @NotNull @NotBlank String isoCode) {
}
