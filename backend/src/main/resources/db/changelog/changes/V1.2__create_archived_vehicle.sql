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
    archived_at timestamp not null,
    user_id bigint not null,
    constraint archived_vehicle_user_id_fk foreign key (user_id) references "user" (id) on delete set null on update set null
);

-- create archived_vehicle index
create index archived_vehicle_index on archived_vehicle(license_plate, type, diesel_injection_pump_type, battery_type, registered_at, archived_at);
