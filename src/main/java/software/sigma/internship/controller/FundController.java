package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.sigma.internship.dto.FundDto;
import software.sigma.internship.mapper.FundMapper;
import software.sigma.internship.repository.FundRepository;
import software.sigma.internship.service.FundService;

@Tag(name = "Fund controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/fund")
public class FundController {
    private final FundService fundService;
    private final FundRepository fundRepository;
    private final FundMapper fundMapper;

    @Operation(summary = "Find all funds")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Page of funds")
    })
    @GetMapping
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
    public FundDto findById(@PathVariable long id) {
        return fundService.findById(id);
    }

    @Operation(summary = "add fund")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "fund successfully created")
    })
    @PostMapping
    public FundDto addFund(@RequestBody FundDto newFund) {
        fundRepository.save(fundMapper.toEntity(newFund));
        return newFund;
    }

    @Operation(summary = "delete fund by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "fund successfully deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFundById(@PathVariable long id) {
        fundRepository.deleteById(id);
        return ResponseEntity.ok("fund Successfully deleted!");
    }

    @Operation(summary = "update fund by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "fund successfully updated")
    })
    @PutMapping("/{id}")
    public FundDto updateFundById(@PathVariable long id, @RequestBody FundDto fund) {
        fundService.update(id, fund);
        return fund;
    }
}
