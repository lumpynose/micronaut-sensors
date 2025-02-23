package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.listener.SensorValue;
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

@Controller("/sensor")
class ViewsModelController {
    private static final Logger log =
            LoggerFactory.getLogger(ViewsModelController.class);

    @Inject
    ApplicationContext applicationContext;

    @Inject
    ZigbeeListener zugListener;

    @View("sensor")
    @Get("/")
    public HttpResponse<?> index() {
        log.info("sensor: active names: {}",
                applicationContext.getEnvironment().getActiveNames());

        final SensorValue message = zugListener.getMessage();

        log.info("message: {}", message);

        return HttpResponse.ok(CollectionUtils.mapOf("sensor", message));
    }
}
