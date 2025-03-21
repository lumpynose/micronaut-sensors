package com.objecteffects.sensors.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageReceivedEvent {
    private static final Logger log =
            LoggerFactory.getLogger(MessageReceivedEvent.class);

    private final SensorValue sensorValue;

    MessageReceivedEvent(SensorValue _sensorValue) {
        log.info("sensorValue: {}", _sensorValue.toString());

        this.sensorValue = _sensorValue;
    }

    public SensorValue getSensorValue() {
        return sensorValue;
    }
}
