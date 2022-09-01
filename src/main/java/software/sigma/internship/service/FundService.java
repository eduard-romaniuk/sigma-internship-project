package software.sigma.internship.service;

import software.sigma.internship.dto.FundDto;

import java.util.List;

public interface FundService {
    List<FundDto> findAll();

    FundDto findById(long id);

    FundDto update(long id, FundDto fund);

    FundDto save(FundDto fund);

    void remove(long id);
}
