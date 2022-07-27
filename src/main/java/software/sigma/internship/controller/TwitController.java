package software.sigma.internship.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.internship.dto.TwitDto;
import software.sigma.internship.service.TwitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/twit")
public class TwitController {
    private final TwitService twitService;

    @GetMapping
    public List<TwitDto> findAll() {
        return twitService.findAll();
    }

    @GetMapping("/{id}")
    public TwitDto findById(@PathVariable long id) {
        return twitService.findById(id);
    }
}
