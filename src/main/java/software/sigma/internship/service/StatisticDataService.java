package software.sigma.internship.service;

import software.sigma.internship.dto.StatisticDataDto;

public interface StatisticDataService {
    StatisticDataDto findLatest();

    void save(StatisticDataDto statisticDataDto);
}
