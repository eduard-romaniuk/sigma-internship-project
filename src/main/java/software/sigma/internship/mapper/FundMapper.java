package software.sigma.internship.mapper;

import org.mapstruct.Mapper;
import software.sigma.internship.dto.FundDto;
import software.sigma.internship.entity.Fund;

@Mapper(componentModel = "spring")
public interface FundMapper {
    FundDto toDto(Fund fund);
}
