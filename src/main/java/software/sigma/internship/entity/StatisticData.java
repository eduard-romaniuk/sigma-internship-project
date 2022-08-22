package software.sigma.internship.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "statistic_data")
@Table(name = "statistic_data")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StatisticData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "war_date", nullable = false)
    private LocalDate warDate;

    @Column(name = "day_number", nullable = false)
    private int dayNumber;

    @Column(name = "personnel_units", nullable = false)
    private int personnelUnits;

    @Column(name = "tanks", nullable = false)
    private int tanks;

    @Column(name = "armoured_fighting_vehicles", nullable = false)
    private int armouredFightingVehicles;

    @Column(name = "artillery_systems", nullable = false)
    private int artillerySystems;

    @Column(name = "mlrs", nullable = false)
    private int mlrs;

    @Column(name = "aa_warfare_systems", nullable = false)
    private int aaWarfareSystems;

    @Column(name = "planes", nullable = false)
    private int planes;

    @Column(name = "helicopters", nullable = false)
    private int helicopters;

    @Column(name = "vehicles_fuel_tanks", nullable = false)
    private int vehiclesFuelTanks;

    @Column(name = "warships_cutters", nullable = false)
    private int warshipsCutters;

    @Column(name = "cruise_missiles", nullable = false)
    private int cruiseMissiles;

    @Column(name = "uav_systems", nullable = false)
    private int uavSystems;

    @Column(name = "special_military_equip", nullable = false)
    private int specialMilitaryEquip;

    @Column(name = "atgm_srbm_systems", nullable = false)
    private int atgmSrbmSystems;
}
