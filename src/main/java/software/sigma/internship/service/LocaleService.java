package software.sigma.internship.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import software.sigma.internship.dto.LocaleDto;

public interface LocaleService {

    Page<LocaleDto> findAll(Pageable pageable);
}
