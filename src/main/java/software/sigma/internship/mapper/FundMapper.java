package software.sigma.internship.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import software.sigma.internship.dto.FundDto;
import software.sigma.internship.entity.Fund;

@Mapper(componentModel = "spring")
public interface FundMapper {
    FundDto toDto(Fund fund);
    Fund toEntity(FundDto fund);
    @Mapping(target = "id", ignore = true)
    void updateEntity(FundDto fundDto, @MappingTarget Fund fund);
}
