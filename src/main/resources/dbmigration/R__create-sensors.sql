drop schema if exists sensors_scm;
create schema if not exists sensors_scm;

drop table if exists sensor;
drop table if exists protocol;
drop table if exists location;

create table protocol
(
    id   uuid not null unique default gen_random_uuid() primary key,
    name text not null unique
);

create table location
(
    id   uuid not null unique default gen_random_uuid() primary key,
    name text not null unique
);

create table sensor
(
    id         uuid not null unique     default gen_random_uuid() primary key,
    name       text,
    -- zigbee id or 433mhz name
    sensor_id  text,
    ignore     boolean,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now(),
    channel    text,
    protocol   uuid,
    constraint fk_protocol foreign key (protocol)
        references protocol (id)
        on update restrict
        on delete restrict,
    location   uuid,
    constraint fk_location foreign key (location)
        references location (id)
        on update restrict
        on delete restrict
);

insert into protocol (name)
values ('zigbee'),
       ('433mhz'),
       ('zwave');

insert into location (name)
values ('local'),
       ('remote');
