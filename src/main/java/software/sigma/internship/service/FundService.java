package software.sigma.internship.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import software.sigma.internship.dto.FundDto;
import software.sigma.internship.entity.Fund;

public interface FundService {
    Page<FundDto> findAll(Pageable pageable);

    FundDto findById(long id);

    void update(long id, Fund fund);
}
