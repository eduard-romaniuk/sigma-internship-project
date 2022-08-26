package software.sigma.internship.service;

import software.sigma.internship.dto.CalculatedStatisticDataDto;
import software.sigma.internship.dto.LossDayDto;
import software.sigma.internship.dto.StatisticDataDto;

import java.util.List;

public interface StatisticDataService {
    StatisticDataDto findLatest();

    void save(StatisticDataDto statisticDataDto);

    List<LossDayDto> getLossDataset(String lossType);

    CalculatedStatisticDataDto getCalculations(String lossType);
}
