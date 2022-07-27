package software.sigma.internship.service;

import software.sigma.internship.dto.TwitDto;

import java.util.List;

public interface TwitService {
    List<TwitDto> findAll();

    TwitDto findById(long id);
}
