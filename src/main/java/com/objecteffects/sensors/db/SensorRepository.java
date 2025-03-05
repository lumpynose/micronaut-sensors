package com.objecteffects.sensors.db;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Optional;

public interface SensorRepository {
    @NonNull
    Optional<Sensor> findById(long id);

    @NonNull
    Sensor saveRtl433(@NonNull @NotBlank String rtl433Id);

    @NonNull
    Sensor saveZigbee(@NonNull @NotBlank String zigbeeId);

    void deleteById(long id);

    @NonNull
    List<Sensor> findAll();

    int update(long id, @NonNull @NotBlank String name);
}
