package software.sigma.internship.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.service.StatisticDataService;

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

}
