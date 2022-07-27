package software.sigma.internship.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.sigma.internship.dto.TwitDto;
import software.sigma.internship.exception.WebException;
import software.sigma.internship.mapper.TwitMapper;
import software.sigma.internship.repository.TwitRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TwitServiceImpl implements TwitService {
    private final TwitRepository twitRepository;
    private final TwitMapper twitMapper;

    @Override
    public List<TwitDto> findAll() {
        return twitRepository.findAll()
                .stream()
                .map(twitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TwitDto findById(long id) {
        return twitRepository.findById(id)
                .map(twitMapper::toDto)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "Twit not found"));
    }
}
