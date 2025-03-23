drop schema if exists sensors_scm;

create schema if not exists sensors_scm;

drop table if exists sensor;

drop table if exists protocol;

create table protocol
(
    name text not null unique primary key
);

create table sensor
(
    id         uuid not null unique     default gen_random_uuid() primary key,
    name       text,
    sensor_id  text,
--     zigbee_id  bigint unique,
--     rtl433_id  text,
    ignore    boolean,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now(),
    channel    text
--     protocol   text,
--     constraint fk_protocol foreign key (protocol)
--         references protocol (name)
--         on update restrict
--         on delete restrict
);

insert into protocol (name)
values ('zigbee'),
       ('433mhz'),
       ('zwave');
