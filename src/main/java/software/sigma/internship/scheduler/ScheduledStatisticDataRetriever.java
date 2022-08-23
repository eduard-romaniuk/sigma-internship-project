package software.sigma.internship.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.exception.WebException;
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

    @Scheduled(cron = "0 0 10 * * ?")
    public void updateStatisticData() {
        StatisticDataDto statisticDataDtoAPI = warAPIService.getLatestStatistics();
        LocalDate newDate = statisticDataMapper.toEntity(statisticDataDtoAPI).getWarDate();

        try {
            LocalDate bdLatestDate = statisticDataMapper.toEntity(statisticDataService.findLatest()).getWarDate();
            if (!newDate.isEqual(bdLatestDate)) {
                statisticDataService.save(statisticDataDtoAPI);
            }
        } catch (WebException e) {
            statisticDataService.save(statisticDataDtoAPI);
        }
    }
}
