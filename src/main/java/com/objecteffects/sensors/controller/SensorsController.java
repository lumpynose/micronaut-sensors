package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.listener.MqttListener;
import com.objecteffects.sensors.listener.SensorValue;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/sensors")
class SensorsController {
    private static final Logger log =
            LoggerFactory.getLogger(SensorsController.class);

    private final MqttListener mqttListener;

    public SensorsController(MqttListener _mqttListener) {
        this.mqttListener = _mqttListener;
    }

    @View("sensors")
    @Get
    public HttpResponse<?> index() {
        final List<SensorValue> sensorValues = mqttListener.getSensorvalues();

        //        final SensorValues sensorValues =
        //                new SensorValues(mqttListener.getSensorvalues());

        log.info("sensorValues: {}", sensorValues);

        return HttpResponse.ok(
                CollectionUtils.mapOf("sensorvalues", sensorValues));
    }
}
