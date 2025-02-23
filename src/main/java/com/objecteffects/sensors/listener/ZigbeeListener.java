package com.objecteffects.sensors.listener;

import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.json.JsonMapper;
import io.micronaut.mqtt.annotation.MqttSubscriber;
import io.micronaut.mqtt.annotation.Topic;
import io.micronaut.mqtt.exception.MqttSubscriberException;
import io.micronaut.mqtt.exception.MqttSubscriberExceptionHandler;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@MqttSubscriber
public class ZigbeeListener implements MqttSubscriberExceptionHandler {
    private static final Logger log =
            LoggerFactory.getLogger(ZigbeeListener.class);

    @Inject
    JsonMapper jsonMapper;

    SensorValue message;
    Map<String, SensorValue> messages =
            Collections.synchronizedMap(new HashMap<>());

    @Inject
    ApplicationEventPublisher<MessageReceivedEvent> eventPublisher;

    @SuppressWarnings("unused")
    @Topic(value = "zigbee2mqtt/sensors/+")
    public void receive(@Nullable byte[] data, @Topic String topic)
            throws IOException {
        if (data == null) {
            log.info("data is null");

            return;
        }

        String sensorName = topic.lastIndexOf('/') == -1 ? topic :
                topic.substring(topic.lastIndexOf('/') + 1);

        log.info("topic: {}, sensorName: {}", topic, sensorName);
        log.info("data: {}", new String(data, StandardCharsets.UTF_8));

        message = jsonMapper.readValue(data, SensorValue.class);

        messages.put(sensorName, message);

        log.info("message: {}", message);

        eventPublisher.publishEvent(new MessageReceivedEvent(message));
    }

    @SuppressWarnings("unused")
    @Nullable
    public SensorValue getMessage() {
        return message;
    }

    @SuppressWarnings("unused")
    @Nullable
    public Map<String, SensorValue> getMessages() {
        return messages;
    }

    @Override
    public void handle(final MqttSubscriberException ex) {
        log.error("subscriber exception: {}", ex);
    }
}
