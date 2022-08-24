package software.sigma.internship.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.entity.StatisticData;
import software.sigma.internship.exception.WebException;
import software.sigma.internship.mapper.StatisticDataMapper;
import software.sigma.internship.repository.StatisticDataRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class StatisticDataServiceImpl implements StatisticDataService {
    private final StatisticDataRepository statisticDataRepository;
    private final StatisticDataMapper statisticDataMapper;

    @Override
    public StatisticDataDto findLatest() {
        return statisticDataRepository
                .findFirstByOrderByDayNumberDesc()
                .map(statisticDataMapper::toDto)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "Latest statistic data not found"));
    }

    @Override
    public void save(StatisticDataDto statisticDataDto) {
        statisticDataRepository.save(statisticDataMapper.toEntity(statisticDataDto));
    }

    @Override
    public Map<LocalDate, Integer> getLossDataset(String lossType) {
        List<StatisticData> statisticDataList = statisticDataRepository.findAll();
        if (statisticDataList.size() > 0) {
            List<Integer> lossValuesList = getLossValuesList(statisticDataList, lossType);
            List<LocalDate> dateList = extractValuesByLossType(statisticDataList, StatisticData::getWarDate);

            return IntStream.range(0, dateList.size())
                    .boxed()
                    .collect(Collectors.toMap(dateList::get, lossValuesList::get));
        } else {
            throw new WebException(HttpStatus.NO_CONTENT, "Losses not found");
        }
    }

    private List<Integer> getLossValuesList(List<StatisticData> statisticDataList, String lossType) {
        List<Integer> lossValuesList;
        switch (lossType) {
            case "personnel_units" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getPersonnelUnits);
            case "tanks" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getTanks);
            case "armoured_fighting_vehicles" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getArmouredFightingVehicles);
            case "artillery_systems" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getArtillerySystems);
            case "mlrs" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getMlrs);
            case "aa_warfare_systems" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getAaWarfareSystems);
            case "planes" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getPlanes);
            case "helicopters" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getHelicopters);
            case "vehicles_fuel_tanks" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getVehiclesFuelTanks);
            case "warships_cutters" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getWarshipsCutters);
            case "cruise_missiles" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getCruiseMissiles);
            case "uav_systems" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getUavSystems);
            case "special_military_equip" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getSpecialMilitaryEquip);
            case "atgm_srbm_systems" ->
                    lossValuesList = extractValuesByLossType(statisticDataList, StatisticData::getAtgmSrbmSystems);
            default -> throw new WebException(HttpStatus.BAD_REQUEST, "Invalid type of looses parameter");
        }
        return lossValuesList;
    }

    private <T, U> List<U> extractValuesByLossType(List<T> list, Function<T, U> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }
}
