package com.objecteffects.sensors.listener;

import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MessageReceivedEvent {
    private static final Logger log =
            LoggerFactory.getLogger(MessageReceivedEvent.class);

    @Inject
    ZigbeeListener zigbeeListener;

    MessageReceivedEvent(SensorValue sensorValue) {
        log.info("sensorValue: {}", sensorValue.toString());
    }

    public Map<String, SensorValue> getSensorValues() {
        log.debug("messages: {}", zigbeeListener.getMessages());

        return zigbeeListener.getMessages();
    }
}
