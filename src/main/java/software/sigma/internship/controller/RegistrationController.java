package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.service.UserService;

@Tag(name = "Fund controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    public UserDto registration(@RequestBody AuthUserDto user) {
        return userService.register(user);
    }
}
