package com.objecteffects.sensors.listener;

import com.objecteffects.sensors.jdbc.Sensor;
import com.objecteffects.sensors.jdbc.SensorRepository;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MqttSubscriber
public class MqttListener implements MqttSubscriberExceptionHandler {
    private static final Logger log =
            LoggerFactory.getLogger(MqttListener.class);

    private final Map<String, SensorValue> sensorValues =
            Collections.synchronizedMap(new HashMap<>());

    private final SensorRepository sensorRepository;
    private final ApplicationEventPublisher<MessageReceivedEvent>
            eventPublisher;
    private final JsonMapper jsonMapper;

    //    private SensorValue sensorValue;

    public MqttListener(SensorRepository _sensorRepository, ApplicationEventPublisher<MessageReceivedEvent> _eventPublisher, JsonMapper _jsonmapper) {
        this.sensorRepository = _sensorRepository;
        this.eventPublisher = _eventPublisher;
        this.jsonMapper = _jsonmapper;
    }

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

        log.info("sensorValue: {}", sensorValue);

        final Map<String, Object> sensorValueMap =
                jsonMapper.readValue(data, HashMap.class);
        sensorValueMap.put("sensorId", sensorId);

        log.info("sensorValueMap: {}", sensorValueMap);

        // the key for the sensorValues map
        final String sensorIdChan;

        // 433mhz sensors have a channel while zigbee sensors do not.
        // but not all 433mhz sensors have a channel.
        if (StringUtils.isNotBlank(sensorValue.getChannel())) {
            sensorIdChan = sensorId + "-" + sensorValue.getChannel();
        }
        else {
            sensorIdChan = sensorId;
        }

        /*
         * 433mhz sensors report in Fahrenheit, which is stored in
         * temperatureF while zigbee sensors report temperature in Celsius,
         * which is stored in temperature.
         */


        // zigbee
        if (sensorValue.getTemperature() != null) {
            sensorValue.setTemperature((float) (TUnit.Fahrenheit.convert(
                    sensorValue.getTemperature())));
        }
        else {
            // temperatureF may be null (a sensor that doesn't report
            // the temperature.
            sensorValue.setTemperature(sensorValue.getTemperatureF());
        }

        /*
         * Don't add sensors that don't have a temperature.
         *
         * Instead of this code, could set ignore to true.  But it's in Sensor,
         * not SensorValue; maybe add it to SensorValue?  But only set ignore
         * if it's not set or null.
         */
        if (sensorValue.getTemperature() != null) {
            sensorValues.put(sensorIdChan, sensorValue);
        }

        log.info("channel: {}", sensorValue.getChannel());
        log.info("sensorValues: {}", sensorValues);

        //        final Sensor sensorDb =
        //                this.sensorRepository.findBySensorId(sensorIdChan);
        final Sensor sensorDb;

        if (StringUtils.isNotBlank(sensorValue.getChannel())) {
            sensorDb = this.sensorRepository.findBySensorIdAndChannel(sensorId,
                    sensorValue.getChannel());
        }
        else {
            sensorDb = this.sensorRepository.findBySensorId(sensorId);
        }

        if (sensorDb == null) {
            //            final Sensor sensor = new Sensor.Builder().sensorId(sensorIdChan)
            final Sensor sensor = new Sensor.Builder().sensorId(sensorId)
                    .channel(sensorValue.getChannel()).build();

            final Sensor sensorSaved = this.sensorRepository.save(sensor);

            // no name yet thus no need to do a setName()

            log.info("sensorSaved: {}", sensorSaved);
        }
        else {
            sensorValue.setName(sensorDb.getName());
        }

        eventPublisher.publishEvent(new MessageReceivedEvent(sensorValue));
    }

    public List<SensorValue> getSensorvalues() {
        List<SensorValue> sorted = new ArrayList<>(sensorValues.values());
        Collections.sort(sorted);

        return sorted;
    }

    @Override
    public void handle(final MqttSubscriberException _ex) {
        log.error("subscriber exception: {}", _ex, _ex);
    }
}
