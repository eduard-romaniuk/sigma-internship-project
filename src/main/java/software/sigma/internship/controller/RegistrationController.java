package software.sigma.internship.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.internship.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class RegistrationController {
    private final UserService userService;
}
