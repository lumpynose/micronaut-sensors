package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.listener.SensorValues;
import com.objecteffects.sensors.listener.ZigbeeListener;
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

@Controller("/sensors")
class SensorsController {
    private static final Logger log =
            LoggerFactory.getLogger(SensorsController.class);

    @Inject
    private ApplicationContext applicationContext;

    @Inject
    private ZigbeeListener zigbeeListener;

    @View("sensors")
    @Get("/")
    public HttpResponse<?> index() {
        log.info("sensor: active names: {}",
                applicationContext.getEnvironment().getActiveNames());

        final Map<String, SensorValues> messages = zigbeeListener.getMessages();

        log.info("messages: {}", messages.size());

        return HttpResponse.ok(CollectionUtils.mapOf("sensors", messages));
    }
}
