-- create user table
create table if not exists "user" (
    id bigserial not null constraint user_pk primary key,
    username varchar(20) not null,
    password varchar(255) not null,
    name varchar(20) not null,
    last_name varchar(40) not null,
    active boolean,
    created_at timestamp not null,
    updated_at timestamp not null
);

-- create user index
create index user_index on "user"(username, name, last_name);
