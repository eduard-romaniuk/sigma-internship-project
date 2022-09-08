package software.sigma.internship.service;

import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.dto.UserFullDto;

import java.util.List;

public interface UserService {
    UserFullDto findByEmail(String email);
    UserDto register(AuthUserDto user, String isoCode);

    boolean checkIfUserExist(String email);

    List<UserFullDto> getAllUsers();

    void deleteUserById(long id);
}
