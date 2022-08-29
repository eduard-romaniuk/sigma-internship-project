package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.internship.dto.LocaleDto;
import software.sigma.internship.service.LocaleService;

@Tag(name = "Locale controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/locale")
public class LocaleController {
    private final LocaleService localeService;

    @Operation(summary = "Find all locales")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Page of locales")
    })
    @GetMapping
    public Page<LocaleDto> findAll(@ParameterObject Pageable pageable) {
        return localeService.findAll(pageable);
    }
}
