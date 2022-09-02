package software.sigma.internship.mapper;

import org.mapstruct.Mapper;
import software.sigma.internship.dto.AuthUserDto;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.dto.UserFullDto;
import software.sigma.internship.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    UserFullDto toUserFullDto(User user);

    User toEntity(UserDto user);

    User toUser(AuthUserDto user);
}
