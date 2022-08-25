package software.sigma.internship.dto;

import software.sigma.internship.validator.Url;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record FundDto(long id,
                      @NotNull @NotBlank String name,
                      @NotNull @NotBlank String description,
                      @Url @NotNull @NotBlank String link) {
}
