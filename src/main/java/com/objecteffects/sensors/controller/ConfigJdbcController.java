package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.jdbc.Sensor;
import com.objecteffects.sensors.jdbc.SensorJdbcRepository;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.views.View;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/configjdbc")
public class ConfigJdbcController {
    private static final Logger log =
            LoggerFactory.getLogger(ConfigJdbcController.class);

    private final SensorJdbcRepository sensorJdbcRepository;

    public ConfigJdbcController(SensorJdbcRepository _sensorJdbcRepository) {
        this.sensorJdbcRepository = _sensorJdbcRepository;
    }

    @View("list")
    @Get("/list")
    public HttpResponse<?> list() {
        List<Sensor> sensors = sensorJdbcRepository.findAll();

        log.info("/list, sensors: {}", sensors);

        return HttpResponse.ok(CollectionUtils.mapOf("sensors",
                sensorJdbcRepository.findAll()));
    }

    @View("edit")
    @Get("/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<?> save(String sensorId, String channel) {
        log.info("sensorId: {}, channel: {}", sensorId, channel);

        Sensor sensorDb = StringUtils.isNotBlank(channel) ?
                this.sensorJdbcRepository.findBySensorIdAndChannel(sensorId,
                        channel) :
                this.sensorJdbcRepository.findBySensorId(sensorId);

        log.info("sensorDb: {}", sensorDb);

//        Sensor sensor = new Sensor(sensorId, channel);

        // new Sensor.Builder().sensorId(sensorId).channel(channel)
        //    .build();

        return HttpResponse.ok(CollectionUtils.mapOf("sensor", sensorDb));
    }

    @Post("/submit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> submit(String sensorId,
                                  @Nullable String name,
                                  @Nullable String channel,
                                  @Nullable Boolean ignore) {
        log.info("sensorId: {}, name: {}, channel: {}, ignore: {}", sensorId,
                name, channel, ignore);

        Sensor sensorDb = StringUtils.isNotBlank(channel) ?
                this.sensorJdbcRepository.findBySensorIdAndChannel(sensorId,
                        channel) :
                this.sensorJdbcRepository.findBySensorId(sensorId);

        log.info("sensorDb: {}", sensorDb);

        sensorDb.setName(name);
        sensorDb.setIgnore(ignore);

        final Sensor sensorSaved = this.sensorJdbcRepository.update(sensorDb);

        log.info("sensorSaved: {}", sensorSaved);

        return HttpResponse.ok(sensorSaved);
    }
}
