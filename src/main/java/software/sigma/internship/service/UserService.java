package software.sigma.internship.service;

import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;

public interface UserService {
    UserDto register(AuthUserDto user, String isoCode);

    boolean checkIfUserExist(String email);
}
