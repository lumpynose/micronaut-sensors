package com.objecteffects.sensors;

import io.micronaut.mqtt.annotation.Topic;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

//@MqttSubscriber
public class ZigbeeListenerBak {
    private static final Logger log =
            LoggerFactory.getLogger(ZigbeeListenerBak.class);
    String retval = null;

    @SuppressWarnings("unused")
    @Topic(value = "zigbee2mqtt/sensors/#")
    public void receive(@Nullable byte[] data) {
        if (data == null) {
            return;
        }

        retval = new String(data, StandardCharsets.UTF_8);
        log.info("receive: {}", retval);
    }

    @SuppressWarnings("unused")
    @Nullable
    public String getRetval() {
        return retval;
    }
}
