package software.sigma.internship.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.mapper.StatisticDataMapper;
import software.sigma.internship.service.StatisticDataService;
import software.sigma.internship.service.WarAPIService;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ScheduledStatisticDataRetriever {
    private final WarAPIService warAPIService;
    private final StatisticDataService statisticDataService;
    private final StatisticDataMapper statisticDataMapper;

    @Scheduled(fixedRate = 1000000L)
//    @Scheduled(cron = "0 0 10 * * ?")
    public void updateStatisticData() {
        StatisticDataDto statisticDataDtoAPI = warAPIService.getLatestStatistics();

        System.out.println("API:  \t" + statisticDataDtoAPI);
        System.out.println("API date:\t" + statisticDataMapper.toEntity(statisticDataDtoAPI).getWarDate());
        LocalDate newDate = statisticDataMapper.toEntity(statisticDataDtoAPI).getWarDate();

        System.out.println("DB:\t" + statisticDataMapper.toEntity(statisticDataService.findLatest()));
        LocalDate bdDate = statisticDataMapper.toEntity(statisticDataService.findLatest()).getWarDate();

        if (!newDate.isEqual(bdDate)) {
            statisticDataService.save(statisticDataDtoAPI);
        }
    }
}
