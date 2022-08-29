package software.sigma.internship.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.dto.StatisticLatestDataDto;
import software.sigma.internship.dto.StatisticLatestDto;

import java.util.Objects;

@Service
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
}
