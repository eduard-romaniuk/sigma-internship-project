package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import software.sigma.internship.dto.FundDto;
import software.sigma.internship.service.FundService;

import javax.validation.Valid;

@Tag(name = "Fund controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/fund")
public class FundController {
    private final FundService fundService;

    @Operation(summary = "Find all funds")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Page of funds")
    })
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public Page<FundDto> findAll(@ParameterObject Pageable pageable) {
        return fundService.findAll(pageable);
    }

    @Operation(summary = "Find fund by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found the fund"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "fund not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public FundDto findById(@PathVariable long id) {
        return fundService.findById(id);
    }

    @Operation(summary = "add fund")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "fund successfully created")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public FundDto addFund(@Valid @RequestBody FundDto newFund) {
        return fundService.save(newFund);
    }

    @Operation(summary = "delete fund by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "fund successfully deleted")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteFundById(@PathVariable long id) {
        fundService.remove(id);
    }

    @Operation(summary = "update fund by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "fund successfully updated")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FundDto updateFundById(@PathVariable long id, @Valid @RequestBody FundDto fund) {
        return fundService.update(id, fund);
    }
}
