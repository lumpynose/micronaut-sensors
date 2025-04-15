package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.jdbc.Location;
import com.objecteffects.sensors.jdbc.LocationRepository;
import com.objecteffects.sensors.jdbc.Sensor;
import com.objecteffects.sensors.jdbc.SensorRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.views.View;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/edit")
public class EditController {
    private static final Logger log =
            LoggerFactory.getLogger(EditController.class);

    private final SensorRepository sensorRepository;
    private final LocationRepository locationRepository;

    public EditController(SensorRepository _sensorRepository, LocationRepository _locationRepository) {
        this.sensorRepository = _sensorRepository;
        this.locationRepository = _locationRepository;
    }

    @View("edit")
    @Get
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    HttpResponse<?> save(String sensorId, String channel) {
        log.info("sensorId: {}, channel: {}", sensorId, channel);

        final Sensor sensorDb = StringUtils.isNotBlank(channel) ?
                this.sensorRepository.findBySensorIdAndChannel(sensorId,
                        channel) :
                this.sensorRepository.findBySensorId(sensorId);

        log.info("sensorDb: {}", sensorDb);

        final List<Location> locations = this.locationRepository.findAll();

        log.info("locations: {}", locations);

        Map<String, Object> model = new HashMap<>();
        model.put("sensor", sensorDb);
        model.put("locations", this.locationRepository.findAll());

        return HttpResponse.ok(model);
    }
}
