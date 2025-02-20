package com.objecteffects.sensors;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MicronautTest
public class ProductListenerTest {
    private static final Logger log =
            LoggerFactory.getLogger(ProductListenerTest.class);

    @Inject
    ProductListener client;

    @Test
    void testProductListener() {
        log.info("testProductListener");

        log.info("received {}", client.getRetval());
    }
}
