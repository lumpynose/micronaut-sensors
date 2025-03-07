package com.objecteffects.sensors.db1;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SensorMapper {
    @Select("select * from Sensor where id=#{id}")
    Sensor findById(long id);

    @Insert("insert into Sensor (zigbeeId) values(#{zigbeeId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveZigbee(Sensor Sensor);

    @Insert("insert into Sensor (rtl433Id) values(#{rtl433Id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveRtl433(Sensor Sensor);

    @Delete("delete from Sensor where id=#{id}")
    void deleteById(long id);

    @Update("update Sensor set name=#{name} where id=#{id}")
    void update(@Param("id") long id, @Param("name") String name);

    @Select("select * from Sensor")
    List<Sensor> findAll();

    @Select("select * from Sensor order by ${sort} ${order}")
    List<Sensor> findAllBySortAndOrder(
            @NotNull @Pattern(regexp = "id|name") String sort,
            @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order);

    @Select("select * from Sensor order by ${sort} ${order} limit ${offset}, ${max}")
    List<Sensor> findAllByOffsetAndMaxAndSortAndOrder(
            @PositiveOrZero int offset,
            @Positive int max,
            @NotNull @Pattern(regexp = "id|name") String sort,
            @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order);

    @Select("select * from Sensor limit ${offset}, ${max}")
    List<Sensor> findAllByOffsetAndMax(
            @PositiveOrZero int offset, @Positive int max);
}
