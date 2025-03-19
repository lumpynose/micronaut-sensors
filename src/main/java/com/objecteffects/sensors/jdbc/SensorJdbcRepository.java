package com.objecteffects.sensors.jdbc;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.UUID;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface SensorJdbcRepository extends CrudRepository<Sensor, UUID> {
//    Long update(long id, @NonNull @NotBlank String name);

//    @NonNull
//    List<SensorJdbc> findAll();

//    SensorJdbc saveZigbee(Long id);

//    void deleteById(long id);

//    SensorJdbc saveRtl433(String id);

//    @Nullable
//    List<Sensor> findBySensorId(String sensorId);

    @Nullable
    Sensor findBySensorId(String sensorId);

    @Nullable
    Sensor findBySensorIdAndChannel(String sensorId, String channel);

//    Sensor find(String sensorId);

//    @Nullable
//    Sensor findByZigbeeId(Long id);
//
//    @Nullable
//    Sensor findByRtl433Id(String id);

//    Long update(long id,
//                @NonNull @NotBlank String name,
//                @NonNull Boolean ignore, @NonNull Long zigbeeId);
//
//    Long update(long id,
//                @NonNull @NotBlank String name,
//                @NonNull Boolean ignore, @NonNull @NotBlank String rtl433Id);
}
