package software.sigma.internship.mapper;

import org.mapstruct.Mapper;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.entity.StatisticData;

@Mapper(componentModel = "spring")
public interface StatisticDataMapper {

    StatisticDataDto toDto(StatisticData statisticData);

    StatisticData toEntity(StatisticDataDto statisticDataDto);
}
