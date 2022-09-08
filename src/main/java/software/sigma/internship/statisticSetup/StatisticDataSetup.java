package software.sigma.internship.statisticSetup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.sigma.internship.dto.StatisticDataDto;
import software.sigma.internship.exception.WebException;
import software.sigma.internship.service.StatisticDataService;
import software.sigma.internship.service.WarAPIService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatisticDataSetup {
    private final WarAPIService warAPIService;
    private final StatisticDataService statisticDataService;

    @Value("${runAfterTests.retrievingStatData}")
    boolean runRetrieving;

    @PostConstruct
    public void onApplicationEvent() {
        if (runRetrieving) {
            List<StatisticDataDto> dtosFromWarAPI;
            StatisticDataDto latestDBDto = null;

            try {
                latestDBDto = statisticDataService.findLatest();
                dtosFromWarAPI = warAPIService.getStatisticsList(latestDBDto.dayNumber() - 1);
            } catch (WebException e) {
                e.printStackTrace();
                dtosFromWarAPI = warAPIService.getStatisticsList(0);
            }
            dtosFromWarAPI.add(0, latestDBDto);

            getIncreasingDataList(dtosFromWarAPI).forEach(statisticDataService::save);
        }
    }

    private List<StatisticDataDto> getIncreasingDataList(List<StatisticDataDto> dtosFromWarAPI) {
        List<StatisticDataDto> newStatisticDataDtoList = new ArrayList<>();
        StatisticDataDto iteratedDto, previousIteratedDro;

        for (int i = 1; i < dtosFromWarAPI.size(); i++) {
            iteratedDto = dtosFromWarAPI.get(i);
            previousIteratedDro = dtosFromWarAPI.get(i - 1);

            newStatisticDataDtoList.add(new StatisticDataDto(
                    iteratedDto.id(),
                    iteratedDto.warDate(),
                    iteratedDto.dayNumber(),
                    iteratedDto.personnelUnits() - previousIteratedDro.personnelUnits(),
                    iteratedDto.tanks() - previousIteratedDro.tanks(),
                    iteratedDto.armouredFightingVehicles() - previousIteratedDro.armouredFightingVehicles(),
                    iteratedDto.artillerySystems() - previousIteratedDro.artillerySystems(),
                    iteratedDto.mlrs() - previousIteratedDro.mlrs(),
                    iteratedDto.aaWarfareSystems() - previousIteratedDro.aaWarfareSystems(),
                    iteratedDto.planes() - previousIteratedDro.planes(),
                    iteratedDto.helicopters() - previousIteratedDro.helicopters(),
                    iteratedDto.vehiclesFuelTanks() - previousIteratedDro.vehiclesFuelTanks(),
                    iteratedDto.warshipsCutters() - previousIteratedDro.warshipsCutters(),
                    iteratedDto.cruiseMissiles() - previousIteratedDro.cruiseMissiles(),
                    iteratedDto.uavSystems() - previousIteratedDro.uavSystems(),
                    iteratedDto.specialMilitaryEquip() - previousIteratedDro.specialMilitaryEquip(),
                    iteratedDto.atgmSrbmSystems() - previousIteratedDro.atgmSrbmSystems())
            );
        }
        return newStatisticDataDtoList;
    }
}
