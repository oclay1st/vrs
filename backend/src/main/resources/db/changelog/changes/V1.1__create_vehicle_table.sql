-- create vehicle table
create table if not exists vehicle (
    vin bigint not null constraint vehicle_pk primary key,
    license_plate varchar(30) not null unique,
    type varchar(20) not null,
    diesel_injection_pump_type varchar(20),
    gas_fuel_types varchar(20),
    battery_type varchar(15),
    battery_voltage int,
    battery_amperage int,
    convertible boolean,
    convertible_license_plate varchar(30),
    convertible_gas_fuel_types varchar(20),
    created_at timestamp not null,
    updated_at timestamp not null
);
