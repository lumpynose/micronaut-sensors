package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.jdbc.Sensor;
import com.objecteffects.sensors.jdbc.SensorRepository;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/list")
public class ListController {
    private static final Logger log =
            LoggerFactory.getLogger(ConfigController.class);

    private final SensorRepository sensorRepository;

    public ListController(SensorRepository _sensorRepository) {
        this.sensorRepository = _sensorRepository;
    }

    @View("list")
    @Get
    public HttpResponse<?> list() {
        final List<Sensor> sensors = sensorRepository.findAll();
        Collections.sort(sensors);

        log.info("/list, sensors: {}", sensors);

        return HttpResponse.ok(CollectionUtils.mapOf("sensors", sensors));
    }
}
