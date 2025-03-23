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
import org.apache.commons.lang3.StringUtils;
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

//    private SensorValue sensorValue;

    public MqttListener(SensorJdbcRepository _sensorJdbcRepository, ApplicationEventPublisher<MessageReceivedEvent> _eventPublisher, JsonMapper _jsonmapper) {
        this.sensorJdbcRepository = _sensorJdbcRepository;
        this.eventPublisher = _eventPublisher;
        this.jsonMapper = _jsonmapper;
    }

//    @SuppressWarnings("unused")
//    @Topic(value = "sensors/zigbee/+")
//    public void receiveZigbee(@Nullable byte[] data, @Topic String topic)
//            throws IOException {
//        if (data == null) {
//            log.info("data is null");
//
//            return;
//        }
//
//        final String sensorId = topic.lastIndexOf('/') == -1 ? topic :
//                topic.substring(topic.lastIndexOf('/') + 1);
//
//        final Long base10Id = Long.parseLong(sensorId.substring(2), 16);
//
//        log.info("topic: {}, sensorId: {} ({})", topic, sensorId, base10Id);
//        log.info("data: {}", new String(data, StandardCharsets.UTF_8));
//
//        final SensorValue sensorValue =
//                jsonMapper.readValue(data, SensorValue.class);
//        sensorValue.setTimestamp(LocalDateTime.now());
//        sensorValue.setSensorId(sensorId);
//        sensorValue.setProtocol(Protocol.ZIGBEE.toString());
//        sensorValue.setTemperature((float) (TUnit.Fahrenheit.convert(
//                sensorValue.getTemperature())));
//
//        log.info("sensorValue: {}", sensorValue);
//
//        sensorValues.put(sensorId, sensorValue);
//
//        log.info("sensorValues: {}", sensorValues);
//
//        final Sensor sensorDb =
//                this.sensorJdbcRepository.findBySensorId(sensorId);
//
//        if (sensorDb == null) {
//            Sensor sensor = new Sensor.Builder().sensorId(sensorId)
//                    .protocol(Protocol.ZIGBEE.toString()).build();
//
//            final Sensor sensorSaved = this.sensorJdbcRepository.save(sensor);
//
//            // no name yet so no need to do a setName()
//
//            log.info("sensorSaved: {}", sensorSaved);
//        }
//        else {
//            sensorValue.setName(sensorDb.getName());
//        }
//
//        eventPublisher.publishEvent(new MessageReceivedEvent(sensorValue));
//    }

    @SuppressWarnings("unused")
    @Topic(value = "sensors/rtl_433/+")
    @Topic(value = "sensors/zigbee/+")
    public void receiveMessage(@Nullable byte[] data, @Topic String topic)
            throws IOException {
        if (data == null) {
            log.info("data is null");

            return;
        }

        final String sensorId = topic.lastIndexOf('/') == -1 ? topic :
                topic.substring(topic.lastIndexOf('/') + 1);

        log.info("topic: {}, sensorId: {}", topic, sensorId);
        log.info("data: {}", new String(data, StandardCharsets.UTF_8));

        final SensorValue sensorValue =
                jsonMapper.readValue(data, SensorValue.class);
        sensorValue.setTimestamp(LocalDateTime.now());
        sensorValue.setSensorId(sensorId);

        final String sensorIdChan;

        if (StringUtils.isNotBlank(sensorValue.getChannel())) {
//            sensorValue.setProtocol(Protocol.MHZ433.toString());
            sensorIdChan = StringUtils.isNotBlank(sensorValue.getChannel()) ?
                    sensorId + "-" + sensorValue.getChannel() : sensorId;

            sensorValue.setTemperature(sensorValue.getTemperatureF());
        }
        else {
            sensorIdChan = sensorId;

            // this is wrong; see comment below.
            // I need to parse the topic string and pull the protocol
            // from it.
//            sensorValue.setProtocol(Protocol.ZIGBEE.toString());

            // some 433mhz sensors have neither a channel nor a temperatureF.
            if (sensorValue.getTemperature() != null) {
                sensorValue.setTemperature((float) (TUnit.Fahrenheit.convert(
                        sensorValue.getTemperature())));
            }

        }

        /*
         * Don't add sensors that don't have a temperature.
         * Instead, could set ignore to true.  But it's in Sensor, not
         * SensorValue; maybe add it to SensorValue?  But only set ignore
         * if it's not set or null.
         */
        if (sensorValue.getTemperature() != null) {
            sensorValues.put(sensorIdChan, sensorValue);
        }

        log.info("channel: {}", sensorValue.getChannel());
        log.info("sensorValues: {}", sensorValues);

//        final Sensor sensorDb =
//                StringUtils.isNotBlank(sensorValue.getChannel()) ?
//                        this.sensorJdbcRepository.findBySensorIdAndChannel(
//                                sensorId, sensorValue.getChannel()) :
//                        this.sensorJdbcRepository.findBySensorId(sensorId);
        final Sensor sensorDb =
                this.sensorJdbcRepository.findBySensorId(sensorIdChan);

        if (sensorDb == null) {
            final Sensor sensor = new Sensor.Builder().sensorId(sensorIdChan)
//                    .protocol(Protocol.MHZ433.toString())
                    .channel(sensorValue.getChannel()).build();

            final Sensor sensorSaved = this.sensorJdbcRepository.save(sensor);

            // no name yet thus no need to do a setName()

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
        log.error("subscriber exception: {}", _ex, _ex);
    }
}
