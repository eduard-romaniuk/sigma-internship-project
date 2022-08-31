package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.internship.dto.UserFullDto;
import software.sigma.internship.service.UserService;

import java.util.List;

@Tag(name = "User controller")
@RestController
@RequiredArgsConstructor
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
}
