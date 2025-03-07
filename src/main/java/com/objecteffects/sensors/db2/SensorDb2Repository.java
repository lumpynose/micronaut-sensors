package com.objecteffects.sensors.db2;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.repository.PageableRepository;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@JdbcRepository(dialect = Dialect.H2)
public interface SensorDb2Repository
        extends CrudRepository<SensorDb2, Long> {
    SensorDb2 save(Long zigbeeId, String rtl433Id, String name, Boolean ignored);

//    Long update(long id, @NonNull @NotBlank String name);

//    @NonNull
//    List<SensorDb2> findAll();

//    void deleteById(long id);

//    @NonNull
//    Optional<SensorDb2> findById(long id);

//    Long update(long id,
//                @NonNull @NotBlank String name,
//                @NonNull Boolean ignored, @NonNull Long zigbeeId);
//
//    Long update(long id,
//                @NonNull @NotBlank String name,
//                @NonNull Boolean ignored, @NonNull @NotBlank String rtl433Id);
}
