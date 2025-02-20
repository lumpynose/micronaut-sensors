package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.listener.ZugListener;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.inject.Inject;

@Controller("/sensor")
class ViewsModelController {
    private static final Logger log
            = LoggerFactory.getLogger(ViewsModelController.class);

    @Inject
    ApplicationContext applicationContext;

    @Inject
    ZugListener zugListener;

    @View("sensor")
    @Get("/")
    public HttpResponse<?> index() {
        log.info("sensor: active names: {}",
                applicationContext.getEnvironment().getActiveNames());

        final String retVal = zugListener.getRetval();

        log.info("retVal: {}", retVal);

        return HttpResponse.ok(CollectionUtils.mapOf("sensor", retVal));
    }
}
