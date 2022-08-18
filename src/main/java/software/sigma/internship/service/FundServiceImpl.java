package software.sigma.internship.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.sigma.internship.dto.FundDto;
import software.sigma.internship.exception.WebException;
import software.sigma.internship.mapper.FundMapper;
import software.sigma.internship.repository.FundRepository;

@Service
@RequiredArgsConstructor
public class FundServiceImpl implements FundService {
    private final FundRepository fundRepository;
    private final FundMapper fundMapper;

    @Override
    public Page<FundDto> findAll(Pageable pageable) {
        return fundRepository.findAll(pageable)
                .map(fundMapper::toDto);
    }

    @Override
    public FundDto findById(long id) {
        return fundRepository.findById(id)
                .map(fundMapper::toDto)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "Fund not found"));
    }
}
