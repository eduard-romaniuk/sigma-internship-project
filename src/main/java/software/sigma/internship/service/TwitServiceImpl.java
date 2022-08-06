package software.sigma.internship.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.sigma.internship.dto.TwitDto;
import software.sigma.internship.exception.WebException;
import software.sigma.internship.mapper.TwitMapper;
import software.sigma.internship.repository.TwitRepository;

@Service
@RequiredArgsConstructor
public class TwitServiceImpl implements TwitService {
    private final TwitRepository twitRepository;
    private final TwitMapper twitMapper;

    @Override
    public Page<TwitDto> findAll(Pageable pageable) {
        return twitRepository.findAll(pageable)
                .map(twitMapper::toDto);
    }

    @Override
    public TwitDto findById(long id) {
        return twitRepository.findById(id)
                .map(twitMapper::toDto)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "Twit not found"));
    }
}
