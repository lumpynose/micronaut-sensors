package com.objecteffects.sensors.listener;

import com.objecteffects.sensors.jdbc.Sensor;
import com.objecteffects.sensors.jdbc.SensorJdbcRepository;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.json.JsonMapper;
import io.micronaut.mqtt.annotation.MqttSubscriber;
import io.micronaut.mqtt.annotation.Topic;
import io.micronaut.mqtt.exception.MqttSubscriberException;
import io.micronaut.mqtt.exception.MqttSubscriberExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@MqttSubscriber
public class MqttListener implements MqttSubscriberExceptionHandler {
    private static final Logger log =
            LoggerFactory.getLogger(MqttListener.class);

    private final Map<String, SensorValue> sensorValues =
            Collections.synchronizedMap(new HashMap<>());

    private final SensorJdbcRepository sensorJdbcRepository;
    private final ApplicationEventPublisher<MessageReceivedEvent>
            eventPublisher;
    private final JsonMapper jsonMapper;

    private SensorValue sensorValue;

    public MqttListener(SensorJdbcRepository _sensorJdbcRepository, ApplicationEventPublisher<MessageReceivedEvent> _eventPublisher, JsonMapper _jsonmapper) {
        this.sensorJdbcRepository = _sensorJdbcRepository;
        this.eventPublisher = _eventPublisher;
        this.jsonMapper = _jsonmapper;
    }

    @SuppressWarnings("unused")
    @Topic(value = "sensors/zigbee/+")
    public void receiveZigbee(@Nullable byte[] data, @Topic String topic)
            throws IOException {
        if (data == null) {
            log.info("data is null");

            return;
        }

        String zigbeeId = topic.lastIndexOf('/') == -1 ? topic :
                topic.substring(topic.lastIndexOf('/') + 1);

        Long base10Id = Long.parseLong(zigbeeId.substring(2), 16);

        log.info("topic: {}, zigbeeId: {} ({})", topic, zigbeeId, base10Id);
        log.info("data: {}", new String(data, StandardCharsets.UTF_8));

        sensorValue = jsonMapper.readValue(data, SensorValue.class);
        sensorValue.setTimestamp(LocalDateTime.now());
        sensorValue.setSensorId(zigbeeId);
        sensorValue.setProtocol(Protocol.ZIGBEE.toString());
        sensorValue.setTemperature(
                (float) (TUnit.Fahrenheit.convert(sensorValue.temperature)));

        log.info("sensorValue: {}", sensorValue);

        sensorValues.put(zigbeeId, sensorValue);

        log.info("sensorValues: {}", sensorValues);

        Sensor sensorDb = this.sensorJdbcRepository.findBySensorId(zigbeeId);

        if (sensorDb == null) {
            Sensor sensor = new Sensor.Builder().sensorId(zigbeeId)
                    .protocol(Protocol.ZIGBEE.toString()).build();

            Sensor sensorSaved = this.sensorJdbcRepository.save(sensor);

            log.info("sensorSaved: {}", sensorSaved);
        }
        else {
            sensorValue.setName(sensorDb.getName());
        }

        eventPublisher.publishEvent(new MessageReceivedEvent(sensorValue));
    }

    @SuppressWarnings("unused")
    @Topic(value = "sensors/rtl_433/+")
    public void receiveRtl433(@Nullable byte[] data, @Topic String topic)
            throws IOException {
        if (data == null) {
            log.info("data is null");

            return;
        }

        String rtl433Id = topic.lastIndexOf('/') == -1 ? topic :
                topic.substring(topic.lastIndexOf('/') + 1);

        log.info("topic: {}, rtl433Id: {}", topic, rtl433Id);
        log.info("data: {}", new String(data, StandardCharsets.UTF_8));

        sensorValue = jsonMapper.readValue(data, SensorValue.class);
        sensorValue.setTimestamp(LocalDateTime.now());
        sensorValue.setProtocol(Protocol.MHZ433.toString());
        sensorValue.setSensorId(rtl433Id);
        sensorValue.setTemperature(sensorValue.temperatureF);

        sensorValues.put(rtl433Id, sensorValue);

        log.info("channel: {}", sensorValue.getChannel());
        log.info("sensorValues: {}", sensorValues);

        Sensor sensorDb = sensorValue.getChannel() != null ?
                this.sensorJdbcRepository.findBySensorIdAndChannel(rtl433Id,
                        sensorValue.channel) :
                this.sensorJdbcRepository.findBySensorId(rtl433Id);

        if (sensorDb == null) {
            final Sensor sensor = new Sensor.Builder().sensorId(rtl433Id)
                    .protocol(Protocol.MHZ433.toString())
                    .channel(sensorValue.getChannel()).build();

            final Sensor sensorSaved = this.sensorJdbcRepository.save(sensor);

            log.info("sensorSaved: {}", sensorSaved);
        }
        else {
            sensorValue.setName(sensorDb.getName());
        }

        eventPublisher.publishEvent(new MessageReceivedEvent(sensorValue));
    }

    @SuppressWarnings("unused")
    public Collection<SensorValue> getSensorvalues() {
        return sensorValues.values();
    }

    @Override
    public void handle(final MqttSubscriberException _ex) {
        log.error("subscriber exception: {}", _ex);
    }
}
