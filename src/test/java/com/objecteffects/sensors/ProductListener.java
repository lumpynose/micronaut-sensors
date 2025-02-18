package com.objecteffects.sensors;

import io.micronaut.mqtt.annotation.MqttSubscriber;
import io.micronaut.mqtt.annotation.Topic;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@MqttSubscriber // (1)
public class ProductListener {
    private static final Logger log =
            LoggerFactory.getLogger(ProductListener.class);
    String retval = null;

    @Topic("test/product") // (2)
    public void receive(byte[] data) { // (3)
        retval = new String(data, StandardCharsets.UTF_8);
        log.info("receive: {}", retval);
    }

    @Nullable
    public String getRetval() {
        return retval;
    }
}
