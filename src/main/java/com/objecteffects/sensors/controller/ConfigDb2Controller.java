package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.db2.SensorDb2;
import com.objecteffects.sensors.db2.SensorDb2Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.validation.Valid;

import java.util.List;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/configdb2")
public class ConfigDb2Controller {
    private final SensorDb2Repository sensorDb2Repository;

    public ConfigDb2Controller(SensorDb2Repository sensorDb2Repository) {
        this.sensorDb2Repository = sensorDb2Repository;
    }

    @Get("/list")
    public List<SensorDb2> list() {
        return sensorDb2Repository.findAll();
    }
}
