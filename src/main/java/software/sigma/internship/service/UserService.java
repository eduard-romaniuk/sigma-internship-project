package software.sigma.internship.service;

import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.exception.UserAlreadyExistException;

public interface UserService {
    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

    UserDto register(AuthUserDto user) throws UserAlreadyExistException;

    boolean checkIfUserExist(String email, String username);
}
