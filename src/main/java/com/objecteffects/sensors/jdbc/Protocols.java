package com.objecteffects.sensors.jdbc;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity("protocols")
public class Protocols {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    private String name;

    @Nullable
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "sensors")
    private Long sensor_id;
}
