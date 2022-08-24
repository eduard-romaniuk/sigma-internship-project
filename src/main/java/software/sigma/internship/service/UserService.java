package software.sigma.internship.service;

import software.sigma.internship.dto.UserDto;

public interface UserService {
    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);
}
