package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.dto.UserFullDto;
import software.sigma.internship.service.UserService;

import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User is created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user credentials")
    })
    @PostMapping
    public UserDto registration(@Valid @RequestBody AuthUserDto user) {
        log.info(LocaleContextHolder.getLocale().getLanguage());
        return userService.register(user, LocaleContextHolder.getLocale().getLanguage());
    }

    @Operation(summary = "authentication")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully authenticated"),
            @ApiResponse(responseCode = "400", description = "Invalid user credentials")
    })
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/login")
    public UserFullDto authenticatedUser() {
        UserDetails authenticatedUser = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userService.findByEmail(authenticatedUser.getUsername());
    }

    @Operation(summary = "authentication")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }
}
