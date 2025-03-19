drop schema if exists sensors;

create schema if not exists sensors;

drop table if exists sensors.sensor;

create table sensors.sensor
(
    id         uuid not null auto_increment unique primary key,
    name       varchar_ignorecase(128),
    sensor_id  varchar_ignorecase(128),
    zigbee_id  bigint unique,
    rtl433_id  varchar_ignorecase(128),
    ignore     boolean,
    protocol   varchar_ignorecase(128) references sensors.protocol (name)
        on update restrict
        on delete restrict,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

drop table if exists sensors.protocol;

create table sensors.protocol
(
    name varchar_ignorecase(128) not null unique primary key
);

insert into sensors.protocol (name)
values ('zigbee'),
       ('433mhz'),
       ('zwave');
