package software.sigma.internship.service;

import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.dto.UserFullDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

    UserDto register(AuthUserDto user);

    boolean checkIfUserExist(String email);

    List<UserFullDto> getAllUsers();
}
