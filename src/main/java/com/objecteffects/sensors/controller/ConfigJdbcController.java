package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.jdbc.Location;
import com.objecteffects.sensors.jdbc.LocationJdbcRepository;
import com.objecteffects.sensors.jdbc.Sensor;
import com.objecteffects.sensors.jdbc.SensorJdbcRepository;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.views.View;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/configjdbc")
public class ConfigJdbcController {
    private static final Logger log =
            LoggerFactory.getLogger(ConfigJdbcController.class);

    private final SensorJdbcRepository sensorJdbcRepository;
    private final LocationJdbcRepository locationJdbcRepository;

    public ConfigJdbcController(SensorJdbcRepository _sensorJdbcRepository, LocationJdbcRepository _locationJdbcRepository) {
        this.sensorJdbcRepository = _sensorJdbcRepository;
        this.locationJdbcRepository = _locationJdbcRepository;
    }

    @View("list")
    @Get("/list")
    public HttpResponse<?> list() {
        final List<Sensor> sensors = sensorJdbcRepository.findAll();
        Collections.sort(sensors);

        log.info("/list, sensors: {}", sensors);

        return HttpResponse.ok(CollectionUtils.mapOf("sensors", sensors));
    }

    @View("edit")
    @Get("/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<?> save(String sensorId, String channel) {
        log.info("sensorId: {}, channel: {}", sensorId, channel);

        final Sensor sensorDb = StringUtils.isNotBlank(channel) ?
                this.sensorJdbcRepository.findBySensorIdAndChannel(sensorId,
                        channel) :
                this.sensorJdbcRepository.findBySensorId(sensorId);

        log.info("sensorDb: {}", sensorDb);

        final List<Location> locations = this.locationJdbcRepository.findAll();

        log.info("locations: {}", locations);

        Map<String, Object> model = new HashMap<>();
        model.put("sensor", sensorDb);
        model.put("locations", this.locationJdbcRepository.findAll());

        return HttpResponse.ok(model);

    }

    @Post("/submit")
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
                this.sensorJdbcRepository.findBySensorIdAndChannel(sensorId,
                        channel) :
                this.sensorJdbcRepository.findBySensorId(sensorId);

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

        final Sensor sensorSaved = this.sensorJdbcRepository.update(sensorDb);

        log.info("sensorSaved: {}", sensorSaved);

        //        return HttpResponse.ok(CollectionUtils.mapOf("sensors",
        //                sensorJdbcRepository.findAll()));

        URI uri = new URI("/configjdbc/list");

        return HttpResponse.redirect(uri);
    }
}
