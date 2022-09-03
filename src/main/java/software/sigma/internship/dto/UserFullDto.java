package software.sigma.internship.dto;

import software.sigma.internship.entity.Locale;
import software.sigma.internship.entity.Role;
import software.sigma.internship.entity.Subscription;

public record UserFullDto(long id,
                          String name,
                          String email,
                          Locale locale,
                          Role role,
                          Subscription subscription) {
}
