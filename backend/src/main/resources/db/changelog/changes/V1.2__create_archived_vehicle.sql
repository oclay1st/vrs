-- create archived_vehicle table
create table if not exists archived_vehicle (
    id bigserial not null constraint archived_vehicle_pk primary key,
    vin bigint not null,
    license_plate varchar(30) not null,
    type varchar(20) not null,
    diesel_injection_pump_type varchar(20),
    gas_fuel_types varchar(20),
    battery_type varchar(15),
    battery_voltage int,
    battery_amperage int,
    registered_at timestamp not null,
    archived_at timestamp not null
);
