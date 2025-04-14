package com.objecteffects.sensors.jdbc;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
@MappedEntity("protocol")
public class Protocol {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private UUID id;

    @Nullable
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "sensor")
    private UUID sensor_id;

    @NonNull
    private final String name;

    public Protocol(@NonNull String _name) {
        this.name = _name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
