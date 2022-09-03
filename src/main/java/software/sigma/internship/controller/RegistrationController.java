package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.service.UserService;

import javax.validation.Valid;

@Tag(name = "Fund controller")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto registration(@Valid @RequestBody AuthUserDto user) {
        log.info(LocaleContextHolder.getLocale().getLanguage());
        return userService.register(user, LocaleContextHolder.getLocale().getLanguage());
    }
}
