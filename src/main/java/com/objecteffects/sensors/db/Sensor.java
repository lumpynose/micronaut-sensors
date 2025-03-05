package com.objecteffects.sensors.db;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public class Sensor {
    Long zigbeeId;
    String rtl433Id;
    String name;
    Boolean ignore;
    @Nullable
    private Long id;

    public Sensor(@NonNull @NotBlank String rtl433Id) {
        this.rtl433Id = rtl433Id;
    }

    public Sensor(@NonNull @NotBlank Long zigbeeId) {
        this.zigbeeId = zigbeeId;
    }

    public Long getZigbeeId() {
        return zigbeeId;
    }

    public void setZigbeeId(@NonNull final Long zigbeeId) {
        this.zigbeeId = zigbeeId;
    }

    public String getRtl433Id() {
        return rtl433Id;
    }

    public void setRtl433Id(@NonNull final String rtl433Id) {
        this.rtl433Id = rtl433Id;
    }

    public Boolean getIgnore() {
        return ignore;
    }

    public void setIgnore(@NonNull final Boolean ignore) {
        this.ignore = ignore;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sensors{");
        sb.append("id=").append(id);
        sb.append(", zigbeeId='").append(zigbeeId).append('\'');
        sb.append(", rtl433Id='").append(rtl433Id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", ignore=").append(ignore);
        sb.append('}');
        return sb.toString();
    }
}
