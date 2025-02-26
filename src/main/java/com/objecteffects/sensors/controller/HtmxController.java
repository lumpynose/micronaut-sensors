package com.objecteffects.sensors.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/htmx")
class HtmxController {
    private static final Logger log =
            LoggerFactory.getLogger(HtmxController.class);

    @View("htmx")
    @Get("/")
    public HttpResponse<?> htmx() {
        log.info("htmx");

        return HttpResponse.ok();
    }

    @Get("/clicked")
    @Produces(MediaType.TEXT_HTML)
    public HttpResponse<?> clicked() {
        log.info("htmx clicked");

        return HttpResponse.ok("<div>replacement</div>");
    }
}
