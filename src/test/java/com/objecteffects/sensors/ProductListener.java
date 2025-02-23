package com.objecteffects.sensors;

import io.micronaut.mqtt.annotation.MqttSubscriber;
import io.micronaut.mqtt.annotation.Topic;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

//@MqttSubscriber
public class ProductListener {
    private static final Logger log =
            LoggerFactory.getLogger(ProductListener.class);
    String retval = null;

    @SuppressWarnings("unused")
    @Topic(value = "test/#")
    public void receive(byte[] data) {
        retval = new String(data, StandardCharsets.UTF_8);
        log.info("receive: {}", retval);
    }

    @SuppressWarnings("unused")
    @Nullable
    public String getRetval() {
        return retval;
    }
}
