package com.objecteffects.sensors.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/")
class HomeController {
    private static final Logger log =
            LoggerFactory.getLogger(HomeController.class);

    @View("home")
    public HttpResponse<?> index() {
        log.info("home");

        return HttpResponse.ok();
    }
}
