package com.objecteffects.sensors;

import com.objecteffects.sensors.listener.MqttListener;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MicronautTest
public class MqttListenerTest {
    private static final Logger log =
            LoggerFactory.getLogger(MqttListenerTest.class);

    @Inject
    MqttListener client;

    @Test
    void testZigbeeListener() throws InterruptedException {
        log.info("testZigbeeListener");

        log.info("received {}", client.getMessage());

        Thread.sleep(45000);
    }
}
