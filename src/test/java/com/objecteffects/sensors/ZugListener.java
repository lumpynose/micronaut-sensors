package com.objecteffects.sensors;

import com.objecteffects.sensors.listener.MqttListener;
import io.micronaut.mqtt.annotation.Topic;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

//@MqttSubscriber
public class ZugListener {
    private static final Logger log =
            LoggerFactory.getLogger(MqttListener.class);
    String retval = null;

    @SuppressWarnings("unused")
    @Topic(value = "test/zug")
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
