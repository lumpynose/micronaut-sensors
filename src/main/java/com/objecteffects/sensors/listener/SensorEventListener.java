package com.objecteffects.sensors.listener;

import io.micronaut.context.event.ShutdownEvent;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class SensorEventListener {
    private static final Logger log =
            LoggerFactory.getLogger(SensorEventListener.class);

    @EventListener
    public void onStartupEvent(StartupEvent event) {
        log.info("onStartupEvent: {}", event);
    }

    @EventListener
    public void onShutdownEvent(ShutdownEvent event) {
        log.info("onShutdownEvent: {}", event);
    }

    @EventListener
    public void onMessageReceivedEvent(@NotNull MessageReceivedEvent event) {
        log.info("onMessageReceivedEvent: event: {}", event.getSensorValue());
    }
}
