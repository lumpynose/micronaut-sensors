package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.listener.MqttListener;
import com.objecteffects.sensors.listener.SensorValue;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

        log.info("sensorValues: {}", sensorValues);

        final Map<String, Object> model =
                Map.of("sensorvalues", sensorValues, "localdatetime",
                        LocalDateTime.now());

        return HttpResponse.ok(model);
        //                CollectionUtils.mapOf("sensorvalues", sensorValues));
    }
}
