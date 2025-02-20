package com.objecteffects.sensors;

import io.micronaut.mqtt.annotation.Retained;
import io.micronaut.mqtt.annotation.Topic;
import io.micronaut.mqtt.annotation.v5.MqttPublisher;

@MqttPublisher
public interface ProductClient {
    @Topic(value = "test/product")
    @Retained(true)
    void send(byte[] data);
}
