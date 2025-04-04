package com.objecteffects.sensors.controller;

import com.objecteffects.sensors.listener.SensorValue;
import io.micronaut.core.annotation.Introspected;

import java.util.Collection;
import java.util.StringJoiner;

/*
 * An experiment to see how a controller can return a pojo instead
 * of a Map.
 */
@Introspected
public class SensorValues {
    private final Collection<SensorValue> sensorValues;

    public SensorValues(Collection<SensorValue> sensorValues) {
        this.sensorValues = sensorValues;
    }

    public Collection<SensorValue> getSensorValues() {
        return sensorValues;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SensorValues.class.getSimpleName() + "[",
                "]").add("sensorValues=" + sensorValues).toString();
    }
}
