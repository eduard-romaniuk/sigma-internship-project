package software.sigma.internship.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.exception.UserAlreadyExistException;

public interface UserService extends UserDetailsService {
    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

    UserDto register(AuthUserDto user) throws UserAlreadyExistException;

    boolean checkIfUserExist(String email, String username);
}
