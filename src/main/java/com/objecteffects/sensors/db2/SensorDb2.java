package com.objecteffects.sensors.db2;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
@MappedEntity
public class SensorDb2 {
    String name;
    Long zigbeeId;
    String rtl433Id;
    Boolean ignored;
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

//    public SensorDb2(@NonNull @NotBlank String rtl433Id) {
//        this.rtl433Id = rtl433Id;
//    }
//
//    public SensorDb2(@NonNull @NotBlank Long zigbeeId) {
//        this.zigbeeId = zigbeeId;
//    }

    public Long getZigbeeId() {
        return zigbeeId;
    }

    public void setZigbeeId(final Long zigbeeId) {
        this.zigbeeId = zigbeeId;
    }

    public String getRtl433Id() {
        return rtl433Id;
    }

    public void setRtl433Id(final String rtl433Id) {
        this.rtl433Id = rtl433Id;
    }

    public Boolean getIgnored() {
        return ignored;
    }

    public void setIgnored(final Boolean ignored) {
        this.ignored = ignored;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final String sb =
                "Sensors{" + "id=" + id + ", zigbeeId='" + zigbeeId + '\'' +
                        ", rtl433Id='" + rtl433Id + '\'' + ", name='" + name +
                        '\'' + ", ignored=" + ignored + '}';
        return sb;
    }
}
