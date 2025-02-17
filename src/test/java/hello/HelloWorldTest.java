package hello;

import io.micronaut.context.ApplicationContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MicronautTest
class HelloWorldTest {
    private static final Logger log =
            LoggerFactory.getLogger(hello.HelloWorldTest.class);

    @Inject
    ApplicationContext applicationContext;

    @Test
    void testItWorks() {
        log.info("testItWorks: {}",
                applicationContext.getEnvironment().getActiveNames());

        Assertions.assertTrue(applicationContext.isRunning());
    }
}
