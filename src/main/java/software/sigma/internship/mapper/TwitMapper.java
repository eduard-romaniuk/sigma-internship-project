package software.sigma.internship.mapper;

import org.mapstruct.Mapper;
import software.sigma.internship.dto.TwitDto;
import software.sigma.internship.entity.Twit;

@Mapper(componentModel = "spring")
public interface TwitMapper {
    TwitDto toDto(Twit twit);
}
