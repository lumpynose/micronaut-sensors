package com.objecteffects.sensors.jdbc;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;

import java.util.StringJoiner;
import java.util.UUID;

@Serdeable
@MappedEntity("location")
public class Location {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private UUID id;

    @Nullable
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "sensor")
    private UUID sensor_id;

    @NonNull
    private final String name;

    public Location(@NonNull String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Location.class.getSimpleName() + "[",
                "]").add("id=" + id).add("name='" + name + "'")
                .add("sensor_id=" + sensor_id).toString();
    }
}
