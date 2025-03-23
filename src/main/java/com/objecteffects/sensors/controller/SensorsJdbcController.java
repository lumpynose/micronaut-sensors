package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.listener.MqttListener;
import com.objecteffects.sensors.listener.SensorValue;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Controller("/sensorsjdbc")
class SensorsJdbcController {
    private static final Logger log =
            LoggerFactory.getLogger(SensorsJdbcController.class);

    private final ApplicationContext applicationContext;
    private final MqttListener mqttListener;

    public SensorsJdbcController(ApplicationContext _applicationContext, MqttListener _mqttListener) {
        this.applicationContext = _applicationContext;
        this.mqttListener = _mqttListener;
    }

    @View("sensors")
    @Get("/")
    public HttpResponse<?> index() {
//        log.info("sensor: active names: {}",
//                applicationContext.getEnvironment().getActiveNames());

        final Collection<SensorValue> sensorValues =
                mqttListener.getSensorvalues();

        log.info("sensorValues: {}", sensorValues);

        return HttpResponse.ok(
                CollectionUtils.mapOf("sensorvalues", sensorValues));
    }
}
