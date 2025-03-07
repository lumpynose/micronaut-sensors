package com.objecteffects.sensors.db1;

import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

@Singleton
public class SensorMapperImpl implements SensorMapper {
    private final SqlSessionFactory sqlSessionFactory;

    public SensorMapperImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Sensor findById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getSensorMapper(sqlSession).findById(id);
        }
    }

    @Override
    public void saveZigbee(Sensor Sensor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getSensorMapper(sqlSession).saveZigbee(Sensor);
            sqlSession.commit();
        }
    }

    @Override
    public void saveRtl433(Sensor Sensor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getSensorMapper(sqlSession).saveRtl433(Sensor);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getSensorMapper(sqlSession).deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public void update(long id, String name) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getSensorMapper(sqlSession).update(id, name);
            sqlSession.commit();
        }
    }

    @Override
    public List<Sensor> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getSensorMapper(sqlSession).findAll();
        }
    }

    @Override
    public List<Sensor> findAllBySortAndOrder(
            @NotNull @Pattern(regexp = "id|name") String sort,
            @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getSensorMapper(sqlSession).findAllBySortAndOrder(sort,
                    order);
        }
    }

    @Override
    public List<Sensor> findAllByOffsetAndMaxAndSortAndOrder(
            @PositiveOrZero int offset,
            @Positive int max,
            @NotNull @Pattern(regexp = "id|name") String sort,
            @NotNull @Pattern(regexp = "asc|ASC|desc|DESC") String order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getSensorMapper(
                    sqlSession).findAllByOffsetAndMaxAndSortAndOrder(offset,
                    max, sort, order);
        }
    }

    @Override
    public List<Sensor> findAllByOffsetAndMax(
            @PositiveOrZero int offset, @Positive int max) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getSensorMapper(sqlSession).findAllByOffsetAndMax(offset,
                    max);
        }
    }

    private SensorMapper getSensorMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SensorMapper.class);
    }
}
