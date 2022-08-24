package software.sigma.internship.service;

import software.sigma.internship.dto.StatisticDataDto;

import java.time.LocalDate;
import java.util.Map;

public interface StatisticDataService {
    StatisticDataDto findLatest();

    void save(StatisticDataDto statisticDataDto);

    Map<LocalDate, Integer> getLossDataset(String lossType);
}
