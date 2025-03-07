package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.db1.Sensor;
import com.objecteffects.sensors.db1.SensorRepository;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//@Controller("/config")
class ConfigController {
    private static final Logger log =
            LoggerFactory.getLogger(ConfigController.class);
    private final SensorRepository sensorRepository;
    @Inject
    private ApplicationContext applicationContext;

    public ConfigController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    //    @View("sensors")
    @Get("/list")
    public HttpResponse<?> index() {
        log.info("sensor: active names: {}",
                applicationContext.getEnvironment().getActiveNames());

        List<Sensor> sensors = sensorRepository.findAll();

        return HttpResponse.ok(CollectionUtils.mapOf("sensors", sensors));
    }
}
