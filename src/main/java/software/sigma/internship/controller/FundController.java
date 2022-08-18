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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.internship.dto.FundDto;
import software.sigma.internship.service.FundService;

@Tag(name = "Fund controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/fund")
public class FundController {
    private final FundService fundService;

    @Operation(summary = "Find all twits")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Page of twits")
    })
    @GetMapping
    public Page<FundDto> findAll(@ParameterObject Pageable pageable) {
        return fundService.findAll(pageable);
    }

    @Operation(summary = "Find twit by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found the twit"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Twit not found")
    })
    @GetMapping("/{id}")
    public FundDto findById(@PathVariable long id) {
        return fundService.findById(id);
    }
}
