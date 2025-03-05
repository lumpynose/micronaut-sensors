package com.objecteffects.sensors.db;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Optional;

@Singleton
public class SensorRepositoryImpl implements SensorRepository {
    private final SensorMapper SensorMapper;

    public SensorRepositoryImpl(SensorMapper SensorMapper) {
        this.SensorMapper = SensorMapper;
    }

    @Override
    @NonNull
    public Optional<Sensor> findById(long id) {
        return Optional.ofNullable(SensorMapper.findById(id));
    }

    @Override
    @NonNull
    public Sensor saveRtl433(@NonNull @NotBlank String rtl433Id) {
        Sensor Sensor = new Sensor(rtl433Id);
        SensorMapper.saveRtl433(Sensor);

        return Sensor;
    }

    @Override
    @NonNull
    public Sensor saveZigbee(@NonNull @NotBlank String zigbeeId) {
        Long id = Long.parseLong(zigbeeId, 16);

        Sensor Sensor = new Sensor(id);
        SensorMapper.saveZigbee(Sensor);

        return Sensor;
    }

    @Override
    public void deleteById(long id) {
        findById(id).ifPresent(Sensor -> SensorMapper.deleteById(id));
    }

    @NonNull
    public List<Sensor> findAll() {
        return SensorMapper.findAll();
    }

    @Override
    public int update(long id, @NonNull @NotBlank String name) {
        SensorMapper.update(id, name);

        return -1;
    }
}
