-- create vehicle table
create table if not exists vehicle (
    vin bigint not null constraint vehicle_pk primary key,
    license_plate varchar(30) not null unique,
    type varchar(20),
    details json,
    created_at timestamp not null,
    updated_at timestamp not null
);
