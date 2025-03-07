package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.listener.MqttListener;
import com.objecteffects.sensors.listener.SensorValues;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Controller("/sensors2")
class Sensors2Controller {
    private static final Logger log =
            LoggerFactory.getLogger(Sensors2Controller.class);

    @Inject
    private ApplicationContext applicationContext;

    @Inject
    private MqttListener mqttListener;

    @View("sensors2")
    @Get("/")
    public HttpResponse<?> index() {
        log.info("sensor: active names: {}",
                applicationContext.getEnvironment().getActiveNames());

        final Map<String, SensorValues> messages = mqttListener.getMessages();

        log.info("messages: {}", messages.size());

        return HttpResponse.ok(CollectionUtils.mapOf("sensors", messages));
    }
}
