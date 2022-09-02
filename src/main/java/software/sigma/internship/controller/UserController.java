package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.dto.UserFullDto;
import software.sigma.internship.service.UserService;

import java.util.List;

@Tag(name = "User controller")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Find all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of users is retrieved")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserFullDto> findAll() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Register new user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User is created successfully")
    })
    @PostMapping
    public UserDto registration(@RequestBody AuthUserDto user) {
        log.info(LocaleContextHolder.getLocale().getLanguage());
        return userService.register(user, LocaleContextHolder.getLocale().getLanguage());
    }
}
