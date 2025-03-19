package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.jdbc.Sensor;
import com.objecteffects.sensors.jdbc.SensorJdbcRepository;
import io.micronaut.context.ApplicationContext;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/configjdbc")
public class ConfigJdbcController {
    private static final Logger log =
            LoggerFactory.getLogger(ConfigJdbcController.class);

    private final SensorJdbcRepository sensorJdbcRepository;
    private final ApplicationContext applicationContext;

    public ConfigJdbcController(SensorJdbcRepository _sensorJdbcRepository, ApplicationContext _applicationContext) {
        this.sensorJdbcRepository = _sensorJdbcRepository;
        this.applicationContext = _applicationContext;
    }

    @View("configjdbc")
    @Get("/list")
    public HttpResponse<?> list() {
        return HttpResponse.ok(CollectionUtils.mapOf("sensors",
                sensorJdbcRepository.findAll()));
    }

    @View("edit")
    @Get("/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<?> save(String sensorId, String channel) {
        log.info("sensorId: {}, channel: {}", sensorId, channel);

        Sensor sensor = new Sensor(sensorId, channel);

        // new Sensor.Builder().sensorId(sensorId).channel(channel)
        //    .build();

        return HttpResponse.ok(CollectionUtils.mapOf("sensor", sensor));
    }

    @Post("/submit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> submit(String sensorId,
                                  @Nullable String name,
                                  @Nullable String channel,
                                  @Nullable Boolean ignore) {
        log.info("sensorId: {}, name: {}, channel: {}, ignore: {}", sensorId,
                name, channel, ignore);

        return HttpResponse.ok();
    }
}
