package software.sigma.internship.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.exception.WebException;
import software.sigma.internship.mapper.StatisticDataMapper;
import software.sigma.internship.repository.StatisticDataRepository;

@Service
@RequiredArgsConstructor
public class StatisticDataServiceImpl implements StatisticDataService {
    private final StatisticDataRepository statisticDataRepository;
    private final StatisticDataMapper statisticDataMapper;

    @Override
    public StatisticDataDto findLatest() {
        return statisticDataRepository
                .findFirstByOrderByDayNumberAsc()
                .map(statisticDataMapper::toDto)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "Latest statistic data not found"));
    }

    @Override
    public void save(StatisticDataDto statisticDataDto) {
        statisticDataRepository.save(statisticDataMapper.toEntity(statisticDataDto));
    }
}
