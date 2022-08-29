package software.sigma.internship.service;

import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;

public interface UserService {
    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

    UserDto register(AuthUserDto user);

    boolean checkIfUserExist(String email);
}
