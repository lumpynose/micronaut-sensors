package com.objecteffects.sensors.jdbc;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@Serdeable
@MappedEntity
public class Sensor {
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private UUID id;

    @DateCreated
    private LocalDateTime createdAt;
    @DateUpdated
    private LocalDateTime updatedAt;

    /*
     * sensorId is how the sensor identifies itself.  Hex number for zigbee,
     * string for 433mhz.
     */
    @NonNull
    private String sensorId;

//    @NonNull
//    private String protocol;

    @Nullable
    private String name;
    @Nullable
    private String channel;
    @Nullable
    private Boolean ignore;

    public Sensor(@NonNull String sensorId, @Nullable String channel) {
        this.sensorId = sensorId;
        this.channel = channel;
    }

    private Sensor(Builder builder) {
        this.sensorId = builder.sensorId;
        this.name = builder.name;
        this.channel = builder.channel;
        this.ignore = builder.ignore;
//        this.protocol = builder.protocol;
    }

    public static class Builder {
        private String sensorId;
        private String name;
        private String channel;
        private Boolean ignore;
//        private String protocol;

        public Sensor build() {
            return new Sensor(this);
        }

        public Builder sensorId(@NonNull String sensorId) {
            this.sensorId = sensorId;

            return this;
        }

        public Builder name(@NonNull String name) {
            this.name = name;

            return this;
        }

        public Builder channel(@NonNull String channel) {
            this.channel = channel;

            return this;
        }

        public Builder ignore(Boolean ignore) {
            this.ignore = Objects.requireNonNullElse(ignore, Boolean.FALSE);

            return this;
        }

//        public Builder protocol(@NonNull String protocol) {
//            this.protocol = protocol;
//
//            return this;
//        }
    }

    public Boolean getIgnore() {
        return ignore;
    }

    public void setIgnore(final Boolean ignore) {
        this.ignore = ignore;
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

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final LocalDateTime _updatedAt) {
        updatedAt = _updatedAt;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(final String sensorId) {
        this.sensorId = sensorId;
    }

//    @NonNull
//    public String getProtocol() {
//        return protocol;
//    }

    public @Nullable String getChannel() {
        return channel;
    }

    public void setChannel(@Nullable final String _channel) {
        channel = _channel;
    }

//    public void setProtocol(@NonNull final String _protocol) {
//        protocol = _protocol;
//    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Sensor.class.getSimpleName() + "[",
                "]").add("id=" + id).add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("sensorId='" + sensorId + "'").add("name='" + name + "'")
                .add("channel='" + channel + "'").add("ignore=" + ignore)
                .toString();
    }
}
