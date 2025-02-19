package com.objecteffects.sensors;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@MicronautTest
public class ZigbeeListenerTest {
    private static final Logger log =
            LoggerFactory.getLogger(ZigbeeListenerTest.class);

    @Inject
    ZigbeeListener client;

    @Test
    void testZigbeeListener() {
        log.info("received {}", client.getRetval());
    }
}
