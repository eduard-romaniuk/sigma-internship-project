package software.sigma.internship.mapper;

import org.mapstruct.Mapper;
import software.sigma.internship.dto.UserDto;
import software.sigma.internship.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto user);
}
