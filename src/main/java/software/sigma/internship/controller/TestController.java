package software.sigma.internship.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hi")
    public String helloWorld() {
        return "Hello World!";
    }
}
