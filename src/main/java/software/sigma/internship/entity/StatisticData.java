package software.sigma.internship.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StatisticData {

    @Id
    @Column(name = "id",
            nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_current",
            nullable = false)
    private Date dateCurrent;

    @Column(name = "day_current",
            nullable = false)
    private Date dayCurrent;

    @Column(name = "personnel_units",
            nullable = false)
    private Integer personnelUnits;

    @Column(name = "tanks",
            nullable = false)
    private Integer tanks;

    @Column(name = "armoured_fighting_vehicles",
            nullable = false)
    private Integer armouredFightingVehicles;

    @Column(name = "artillery_systems",
            nullable = false)
    private Integer artillerySystems;

    @Column(name = "mlrs",
            nullable = false)
    private Integer mlrs;

    @Column(name = "aa_warfare_systems",
            nullable = false)
    private Integer aaWarfareSystems;

    @Column(name = "planes",
            nullable = false)
    private Integer planes;

    @Column(name = "helicopters",
            nullable = false)
    private Integer helicopters;

    @Column(name = "vehicles_fuel_tanks",
            nullable = false)
    private Integer vehiclesFuelTanks;

    @Column(name = "warships_cutters",
            nullable = false)
    private Integer warshipsCutters;

    @Column(name = "cruise_missiles",
            nullable = false)
    private Integer cruiseMissiles;

    @Column(name = "uav_systems",
            nullable = false)
    private Integer uavSystems;

    @Column(name = "special_military_equip",
            nullable = false)
    private Integer specialMilitaryEquip;

    @Column(name = "atgm_srbm_systems",
            nullable = false)
    private Integer atgmSrbmSystems;
}
