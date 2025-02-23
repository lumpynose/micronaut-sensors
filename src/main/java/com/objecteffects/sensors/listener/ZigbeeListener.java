package com.objecteffects.sensors.listener;

import io.micronaut.json.JsonMapper;
import io.micronaut.mqtt.annotation.MqttSubscriber;
import io.micronaut.mqtt.annotation.Topic;
import io.micronaut.mqtt.exception.MqttSubscriberException;
import io.micronaut.mqtt.exception.MqttSubscriberExceptionHandler;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@MqttSubscriber
public class ZigbeeListener implements MqttSubscriberExceptionHandler {
    private static final Logger log =
            LoggerFactory.getLogger(ZigbeeListener.class);

    @Inject
    JsonMapper jsonMapper;

    SensorValue message;
    List<SensorValue> messages = Collections.synchronizedList(new ArrayList<>());

    @SuppressWarnings("unused")
    @Topic(value = "zigbee2mqtt/sensors/+")
    public void receive(@Nullable byte[] data, @Topic String topic) throws IOException {
        if (data == null) {
            return;
        }

        log.info("topic: {}", topic);
        log.info("data: {}", new String(data, StandardCharsets.UTF_8));

        message = jsonMapper.readValue(data, SensorValue.class);

        messages.add(message);

        log.info("message: {}", message);
    }

    @SuppressWarnings("unused")
    @Nullable
    public SensorValue getMessage() {
        return message;
    }

    @SuppressWarnings("unused")
    @Nullable
    public List<SensorValue> getMessages() {
        return messages;
    }

    @Override
    public void handle(final MqttSubscriberException ex) {
        log.error("subscriber exception: {}", ex);
    }
}
