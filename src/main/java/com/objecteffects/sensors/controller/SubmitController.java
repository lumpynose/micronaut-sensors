package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.jdbc.Sensor;
import com.objecteffects.sensors.jdbc.SensorRepository;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/submit")
public class SubmitController {
    private static final Logger log =
            LoggerFactory.getLogger(SubmitController.class);

    private final SensorRepository sensorRepository;

    public SubmitController(SensorRepository _sensorRepository) {
        this.sensorRepository = _sensorRepository;
    }

    @Post
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> submit(String sensorId,
                                  @Nullable String name,
                                  @Nullable String channel,
                                  @Nullable String location,
                                  @Nullable Boolean ignore)
            throws URISyntaxException {
        log.info(
                "sensorId: {}, name: {}, channel: {}, location: {}, ignore: {}",
                sensorId, name, channel, location, ignore);

        final Sensor sensorDb = StringUtils.isNotBlank(channel) ?
                this.sensorRepository.findBySensorIdAndChannel(sensorId,
                        channel) :
                this.sensorRepository.findBySensorId(sensorId);

        log.info("sensorDb: {}", sensorDb);

        if (StringUtils.isNotBlank(name)) {
            sensorDb.setName(name);
        }

        //        if (StringUtils.isNotBlank(location)) {
        //            sensorDb.setLocation(location);
        //        }

        if (ignore != null) {
            sensorDb.setIgnore(ignore);
        }

        final Sensor sensorSaved = this.sensorRepository.update(sensorDb);

        log.info("sensorSaved: {}", sensorSaved);

        //        return HttpResponse.ok(CollectionUtils.mapOf("sensors",
        //                sensorRepository.findAll()));

        URI uri = new URI("/list");

        return HttpResponse.redirect(uri);
    }
}
