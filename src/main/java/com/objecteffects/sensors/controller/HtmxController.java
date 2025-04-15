package com.objecteffects.sensors.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.views.View;
import io.micronaut.views.htmx.http.HtmxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/htmx")
class HtmxController {
    private static final Logger log =
            LoggerFactory.getLogger(HtmxController.class);

    @View("htmx")
    @Get
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

    @Get("/htmx-sse")
    @View("htmx-sse")
    public HttpResponse<?> sse() {
        log.info("htmx-sse");

        return HttpResponse.ok();
    }

    @Get("/sse-connect")
    @Produces(MediaType.TEXT_EVENT_STREAM)
    public HtmxResponse<?> htmxSseConnect() {
        log.info("htmx-sse-connect");

        return HtmxResponse.builder().build();
        //        "event: sseEvent \n data: <div>new stuff</div>");
    }

    //    @Get("/clicked")
    @Produces(MediaType.TEXT_EVENT_STREAM)
    public HttpResponse<?> sseEvent() {
        log.info("htmx-sseEvent");

        return HttpResponse.ok("event: sseEvent data: <div>replacement</div>");
    }
}
