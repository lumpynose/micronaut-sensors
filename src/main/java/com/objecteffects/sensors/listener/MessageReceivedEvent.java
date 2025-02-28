package com.objecteffects.sensors.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageReceivedEvent {
    private static final Logger log =
            LoggerFactory.getLogger(MessageReceivedEvent.class);

    private final SensorValues sensorValues;

    MessageReceivedEvent(SensorValues _sensorValues) {
        log.info("sensorValue: {}", _sensorValues.toString());

        this.sensorValues = _sensorValues;
    }

    public SensorValues getSensorValue() {
        return sensorValues;
    }
}
