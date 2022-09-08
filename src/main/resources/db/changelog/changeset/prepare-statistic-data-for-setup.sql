-- liquibase formatted sql
-- changeset MrZhenShen:prepare-statistic-data-for-setup
DELETE FROM statistic_data;

INSERT INTO statistic_data(
	war_date, day_number,
	personnel_units, tanks, armoured_fighting_vehicles, artillery_systems, mlrs, aa_warfare_systems, planes, helicopters, vehicles_fuel_tanks, warships_cutters, cruise_missiles, uav_systems, special_military_equip, atgm_srbm_systems)
	VALUES
	('2022-02-25', 2,
	2800, 80, 516, 0, 0, 0, 10, 7, 0, 0, 0, 0, 0, 0);


