package software.sigma.internship.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.entity.User;
import software.sigma.internship.exception.UserAlreadyExistException;
import software.sigma.internship.exception.WebException;
import software.sigma.internship.mapper.UserMapper;
import software.sigma.internship.repository.UserRepository;

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
    public UserDto register(AuthUserDto user) throws UserAlreadyExistException {

        //Let's check if user already registered with us
        if(checkIfUserExist(user.email(), user.username())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(userEntity, user);
        return userMapper.toDto(userRepository.save(userEntity));
    }


    @Override
    public boolean checkIfUserExist(String email, String username) {
        return userRepository.findByEmail(email).isPresent()
                || userRepository.findByName(username).isPresent();
    }

    private void encodePassword( User userEntity, AuthUserDto user){
        userEntity.setPassword(passwordEncoder.encode(user.password()));
    }
}