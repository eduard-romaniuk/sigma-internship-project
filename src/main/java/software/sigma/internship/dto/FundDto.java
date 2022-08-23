package software.sigma.internship.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record FundDto(long id,
                      @NotNull @NotBlank String name,
                      @NotNull @NotBlank String description,
                      @Pattern(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]" +
                              "{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
                      @NotNull @NotBlank String link) {
}
