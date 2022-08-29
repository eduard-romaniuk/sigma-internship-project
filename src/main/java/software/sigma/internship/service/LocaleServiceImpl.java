package software.sigma.internship.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import software.sigma.internship.dto.LocaleDto;
import software.sigma.internship.mapper.LocaleMapper;
import software.sigma.internship.repository.LocaleRepository;

@Service
@RequiredArgsConstructor
public class LocaleServiceImpl implements LocaleService {
    private final LocaleRepository localeRepository;
    private final LocaleMapper localeMapper;

    @Override
    public Page<LocaleDto> findAll(Pageable pageable) {
        return localeRepository.findAll(pageable).map(localeMapper::toDto);
    }
}
