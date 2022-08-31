package software.sigma.internship.service;

import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;

import java.util.Locale;

public interface UserService {
    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

    UserDto register(AuthUserDto user, Locale locale);

    boolean checkIfUserExist(String email);
}
