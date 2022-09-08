package software.sigma.internship.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import software.sigma.internship.dto.*;

import java.util.*;

@Service
@Slf4j
public class WarAPIServiceImpl implements WarAPIService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public StatisticDataDto getLatestStatistics() {
        UriComponents uri = UriComponentsBuilder
                .newInstance()
                .scheme(SCHEME)
                .host(HOST)
                .path(PATH_STATISTICS)
                .path(SEPARATOR)
                .path(PATH_LATEST)
                .build();

        ResponseEntity<StatisticLatestDto> response = restTemplate.getForEntity(uri.toUriString(), StatisticLatestDto.class);
        StatisticLatestDataDto statisticLatestDataDto = Objects.requireNonNull(response.getBody()).data();

        return statisticLatestDataDto
                .increase()
                .withDayNumber(statisticLatestDataDto.day())
                .withWarDate(statisticLatestDataDto.date());
    }

    @Override
    public List<StatisticDataDto> getStatisticsList(int startDayNumber) {
        List<StatisticDataDto> statisticDataDtoList = new ArrayList<>();
        while (true) {
            UriComponents uri = UriComponentsBuilder
                    .newInstance()
                    .scheme(SCHEME)
                    .host(HOST)
                    .path(PATH_STATISTICS)
                    .queryParam("offset", startDayNumber)
                    .queryParam("limit", 50)
                    .build();

            ResponseEntity<StatisticsDto> response;
            try {
                response = restTemplate.getForEntity(uri.toUriString(), StatisticsDto.class);
            } catch (HttpClientErrorException e) {
                log.error(e.getMessage());
                break;
            }

            StatisticsRecordsDto statisticLatestDataDto = Objects.requireNonNull(response.getBody()).data();
            startDayNumber += 50;
            for (StatisticsRecordDto statisticsRecordDto : statisticLatestDataDto.records()) {
                statisticDataDtoList.add(statisticsRecordDto
                        .stats()
                        .withDayNumber(statisticsRecordDto.day())
                        .withWarDate(statisticsRecordDto.date())
                );
            }
        }
        return statisticDataDtoList;
    }

}
