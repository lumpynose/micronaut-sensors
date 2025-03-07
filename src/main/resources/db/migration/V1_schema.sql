drop table if exists sensordb2;

create table sensordb2 (
    id bigint not null auto_increment unique primary key,
    name varchar(128),
    zigbeeid bigint,
    rtl433id varchar(128),
    ignored boolean
);
