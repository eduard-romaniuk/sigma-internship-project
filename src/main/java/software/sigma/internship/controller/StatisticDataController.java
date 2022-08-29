package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.internship.dto.CalculatedStatisticDataDto;
import software.sigma.internship.dto.LossDayDto;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.service.StatisticDataService;

import java.util.List;

@Tag(name = "Statistic Data controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic-data")
public class StatisticDataController {
    private final StatisticDataService statisticDataService;

    @Operation(summary = "Find latest statistic data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found latest statistic data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/latest")
    public StatisticDataDto findLatest() {
        return statisticDataService.findLatest();
    }

    @Operation(summary = "Get dataset by type of loss")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dataset is fetched"),
            @ApiResponse(responseCode = "400", description = "Invalid loss type supplied"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/dataset")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<LossDayDto> getDatasetByLossType(@RequestParam(value = "lossType", defaultValue = "personnel_units") String lossType) {
        return statisticDataService.getLossDataset(lossType);
    }

    @Operation(summary = "Get max, min, mean and median of the statistics data by type of loss")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Calculations are successful"),
            @ApiResponse(responseCode = "400", description = "Invalid loss type supplied"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/math")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public CalculatedStatisticDataDto getMathCalculations(@RequestParam(value = "lossType", defaultValue = "personnel_units") String lossType) {
        return statisticDataService.getCalculations(lossType);
    }
}
