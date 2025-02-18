package com.objecteffects.sensors;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@MicronautTest
public class ProductListenerTest {
    private static final Logger log =
            LoggerFactory.getLogger(ProductListenerTest.class);

    @Inject
    ProductListener client;

    @Test
    void testProductClient() {
        byte[] data = new byte[64];

        //client.receive(data);

        log.info("received {}", client.getRetval());
    }
}
