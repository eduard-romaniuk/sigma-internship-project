package software.sigma.internship.service;

import software.sigma.internship.dto.StatisticDataDto;

import java.util.List;

public interface WarAPIService {
    String SCHEME = "https";
    String HOST = "russianwarship.rip/api/v1";

    String PATH_STATISTICS = "statistics";
    String PATH_LATEST = "latest";
    String SEPARATOR = "/";

    StatisticDataDto getLatestStatistics();

    List<StatisticDataDto> getStatisticsList(int startDayNumber);
}
