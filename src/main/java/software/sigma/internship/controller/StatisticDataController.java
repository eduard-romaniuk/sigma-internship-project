package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.service.StatisticDataService;

import java.time.LocalDate;
import java.util.Map;

@Tag(name = "Statistic Data controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic-data")
public class StatisticDataController {
    private final StatisticDataService statisticDataService;

    @Operation(summary = "Find latest statistic data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found latest statistic data"),
            @ApiResponse(responseCode = "404", description = "Latest statistic data not found")
    })
    @GetMapping("/latest")
    public StatisticDataDto findLatest() {
        return statisticDataService.findLatest();
    }


    @Operation(summary = "Get dataset by type of loss")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dataset is fetched"),
            @ApiResponse(responseCode = "400", description = "Invalid loss type supplied"),
            @ApiResponse(responseCode = "404", description = "Losses not found")
    })
    @GetMapping("/dataset")
    public Map<LocalDate, Integer> getDatasetByLossType(@RequestParam(value = "lossType", defaultValue = "personnel_units") String lossType) {
        return statisticDataService.getLossDataset(lossType);
    }

    @Operation(summary = "Get min of loses by type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Min calculated"),
            @ApiResponse(responseCode = "400", description = "Invalid loss type supplied"),
            @ApiResponse(responseCode = "404", description = "Losses not found")
    })
    @GetMapping("/min")
    public Integer getMinByLossType(@RequestParam(value = "lossType", defaultValue = "personnel_units") String lossType) {
        return statisticDataService.getMin(lossType);
    }

    @Operation(summary = "Get max of loses by type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Max calculated"),
            @ApiResponse(responseCode = "400", description = "Invalid loss type supplied"),
            @ApiResponse(responseCode = "404", description = "Losses not found")
    })
    @GetMapping("/max")
    public Integer getMaxByLossType(@RequestParam(value = "lossType", defaultValue = "personnel_units") String lossType) {
        return statisticDataService.getMax(lossType);
    }

    @Operation(summary = "Get mean of loses by type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mean calculated"),
            @ApiResponse(responseCode = "400", description = "Invalid loss type supplied"),
            @ApiResponse(responseCode = "404", description = "Losses not found")
    })
    @GetMapping("/mean")
    public Double getMeanByLossType(@RequestParam(value = "lossType", defaultValue = "personnel_units") String lossType) {
        return statisticDataService.getMean(lossType);
    }

    @Operation(summary = "Get median of loses by type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Median calculated"),
            @ApiResponse(responseCode = "400", description = "Invalid loss type supplied"),
            @ApiResponse(responseCode = "404", description = "Losses not found")
    })
    @GetMapping("/median")
    public Double getMedianByLossType(@RequestParam(value = "lossType", defaultValue = "personnel_units") String lossType) {
        return statisticDataService.getMedian(lossType);
    }
}
