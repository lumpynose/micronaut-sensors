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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@MqttSubscriber
public class MqttListener implements MqttSubscriberExceptionHandler {
    private static final Logger log =
            LoggerFactory.getLogger(MqttListener.class);

    private final Map<String, SensorValues> messages =
            Collections.synchronizedMap(new HashMap<>());

    private final SensorJdbcRepository sensorJdbcRepository;
    private final ApplicationEventPublisher<MessageReceivedEvent>
            eventPublisher;
    private final JsonMapper jsonMapper;

    private SensorValues message;

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

        message = jsonMapper.readValue(data, SensorValues.class);
        message.setTimestamp(LocalDateTime.now());
        message.setZigbeeId(zigbeeId);
        message.setTemperature(
                (float) (TUnit.Fahrenheit.convert(message.temperature)));

        log.info("message: {}", message);

        messages.put(zigbeeId, message);

        log.info("messages: {}", messages);


        Sensor sensors = this.sensorJdbcRepository.findBySensorId(zigbeeId);

        if (sensors == null) {
            Sensor sensor = new Sensor.Builder().sensorId(zigbeeId)
                    .protocol(Protocol.ZIGBEE.toString()).build();

            Sensor sensorSaved = this.sensorJdbcRepository.save(sensor);

//        List<Sensor> sensors =
//                this.sensorJdbcRepository.findBySensorId(zigbeeId);
//
//        if (sensors.isEmpty()) {
//            Sensor sensor = this.sensorJdbcRepository.save(
//                    new Sensor(zigbeeId, Protocol.ZIGBEE.toString()));

            log.info("sensor: {}", sensorSaved);
        }

        eventPublisher.publishEvent(new MessageReceivedEvent(message));
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

        message = jsonMapper.readValue(data, SensorValues.class);
        message.setTimestamp(LocalDateTime.now());
        message.setRtl433Id(rtl433Id);
        message.setTemperature(message.temperatureF);

        messages.put(rtl433Id, message);

        log.info("messages: {}", messages);

//        Sensor sensors = this.sensorJdbcRepository.findBySensorId(rtl433Id);

        Sensor sensors = message.getChannel() != null ?
                this.sensorJdbcRepository.findBySensorIdAndChannel(rtl433Id,
                        message.channel) :
                this.sensorJdbcRepository.findBySensorId(rtl433Id);

        if (sensors == null) {
            Sensor sensor = new Sensor.Builder().sensorId(rtl433Id)
                    .protocol(Protocol.MHZ433.toString())
                    .channel(message.getChannel()).build();

            Sensor sensorSaved = this.sensorJdbcRepository.save(sensor);

            log.info("sensor: {}", sensorSaved);
        }

//        List<Sensor> sensors =
//                this.sensorJdbcRepository.findBySensorId(rtl433Id);
//
//        if (sensors.isEmpty()) {
//            Sensor sensor = this.sensorJdbcRepository.save(
//                    new Sensor(rtl433Id, Protocol.MHZ433.toString(),
//                            message.getChannel()));
//
//            log.info("sensor: {}", sensor);
//        }

        eventPublisher.publishEvent(new MessageReceivedEvent(message));
    }

    @SuppressWarnings("unused")
    @Nullable
    public SensorValues getMessage() {
        return message;
    }

    @SuppressWarnings("unused")
    public Map<String, SensorValues> getMessages() {
        return messages;
    }

    @Override
    public void handle(final MqttSubscriberException ex) {
        log.error("subscriber exception: {}", ex);
    }
}
