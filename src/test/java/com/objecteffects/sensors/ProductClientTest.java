package com.objecteffects.sensors;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MicronautTest
public class ProductClientTest {
    private static final Logger log =
            LoggerFactory.getLogger(ProductClientTest.class);

    @Inject
    ProductClient client;

    @Test
    void testProductClient() {
        client.send("micronaut2".getBytes());
    }
}
