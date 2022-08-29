package software.sigma.internship.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.With;

import java.time.LocalDate;

@With
public record StatisticDataDto(
        long id,
        LocalDate warDate,
        int dayNumber,

        @JsonProperty("personnel_units")
        int personnelUnits,

        int tanks,

        @JsonProperty("armoured_fighting_vehicles")
        int armouredFightingVehicles,

        @JsonProperty("artillery_systems")
        int artillerySystems,
        int mlrs,

        @JsonProperty("aa_warfare_systems")
        int aaWarfareSystems,

        int planes,
        int helicopters,

        @JsonProperty("vehicles_fuel_tanks")
        int vehiclesFuelTanks,

        @JsonProperty("warships_cutters")
        int warshipsCutters,

        @JsonProperty("cruise_missiles")
        int cruiseMissiles,

        @JsonProperty("uav_systems")
        int uavSystems,

        @JsonProperty("special_military_equip")
        int specialMilitaryEquip,

        @JsonProperty("atgm_srbm_systems")
        int atgmSrbmSystems) {
}
