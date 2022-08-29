package software.sigma.internship.mapper;

import org.mapstruct.Mapper;
import software.sigma.internship.dto.LocaleDto;
import software.sigma.internship.entity.Locale;

@Mapper(componentModel = "spring")
public interface LocaleMapper {
    LocaleDto toDto(Locale locale);
}
