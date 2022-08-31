package software.sigma.internship.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.dto.UserFullDto;
import software.sigma.internship.entity.User;
import software.sigma.internship.exception.WebException;
import software.sigma.internship.mapper.UserMapper;
import software.sigma.internship.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

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

    @Override
    public UserDto register(AuthUserDto user) {

        //Let's check if user already registered with us
        if(checkIfUserExist(user.email())){
            throw new WebException(HttpStatus.CONFLICT, "User already exists");
        }
        User userEntity = userMapper.toUser(user);
        encodePassword(userEntity, user);
        return userMapper.toDto(userRepository.save(userEntity));
    }


    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public List<UserFullDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserFullDto).collect(Collectors.toList());
    }

    private void encodePassword( User userEntity, AuthUserDto user){
        userEntity.setPassword(passwordEncoder.encode(user.password()));
    }
}