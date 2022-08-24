package software.sigma.internship.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.exception.WebException;
import software.sigma.internship.mapper.UserMapper;
import software.sigma.internship.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "User not found"));
    }

}