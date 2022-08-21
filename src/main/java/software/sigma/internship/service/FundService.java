package software.sigma.internship.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import software.sigma.internship.dto.FundDto;

public interface FundService {
    Page<FundDto> findAll(Pageable pageable);

    FundDto findById(long id);

    FundDto update(long id, FundDto fund);

    FundDto save(FundDto fund);

    void remove(long id);
}
