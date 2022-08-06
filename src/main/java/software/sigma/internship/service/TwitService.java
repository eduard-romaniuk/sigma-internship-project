package software.sigma.internship.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import software.sigma.internship.dto.TwitDto;

public interface TwitService {
    Page<TwitDto> findAll(Pageable pageable);

    TwitDto findById(long id);
}
