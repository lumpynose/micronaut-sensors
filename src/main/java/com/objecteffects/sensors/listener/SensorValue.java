package com.objecteffects.sensors.listener;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Introspected
@Serdeable
public class SensorValue implements Comparable<SensorValue> {
    private String sensorId;
    private String timestamp;
    @JsonProperty("time")
    private String time;
    private String name;
    @JsonProperty("air_quality")
    private String airQuality;
    private Float battery;
    @JsonProperty("batterylow")
    private Boolean batteryLow;
    @JsonProperty("devicetemperature")
    private Float deviceTemperature;
    @JsonProperty("humidity")
    private Float humidity;
    @JsonProperty("temperature")
    private Float temperature; // Celsius
    @JsonProperty("temperature_F")
    private Float temperatureF; // Fahrenheit
    @JsonProperty("voc")
    private Float voc;
    @JsonProperty("linkquality")
    private Float linkQuality;
    private Boolean tamper;
    @JsonProperty("water_leak")
    private Boolean waterLeak;
    @JsonProperty("occupancy")
    private Boolean occupancy;
    private Float illuminance;
    @JsonProperty("channel")
    private String channel;
    //    private String protocol;

    private final DateTimeFormatter timestampFormatter =
            DateTimeFormatter.ofPattern("dd MMM HH:mm");

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 2025-04-09 14:55:19.231607
    // 2025-04-09 14:58:49.420648

    public String getChannel() {
        return channel;
    }

    public void setChannel(final String _channel) {
        channel = _channel;
    }

    public Float getTemperatureF() {
        return temperatureF;
    }

    public void setTemperatureF(final Float temperatureF) {
        this.temperatureF = temperatureF;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp.format(timestampFormatter);
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(final String airQuality) {
        this.airQuality = airQuality;
    }

    public Float getBattery() {
        return battery;
    }

    public void setBattery(final Float battery) {
        this.battery = battery;
    }

    public Boolean getBatteryLow() {
        return batteryLow;
    }

    public void setBatteryLow(final Boolean batteryLow) {
        this.batteryLow = batteryLow;
    }

    public Float getDeviceTemperature() {
        return deviceTemperature;
    }

    public void setDeviceTemperature(final Float deviceTemperature) {
        this.deviceTemperature = deviceTemperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(final Float humidity) {
        this.humidity = humidity;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(final Float _temperature) {
        this.temperature = _temperature;
    }

    public Float getVoc() {
        return voc;
    }

    public void setVoc(final Float voc) {
        this.voc = voc;
    }

    public Float getLinkQuality() {
        return linkQuality;
    }

    public void setLinkQuality(final Float linkQuality) {
        this.linkQuality = linkQuality;
    }

    public Boolean getTamper() {
        return tamper;
    }

    public void setTamper(final Boolean tamper) {
        this.tamper = tamper;
    }

    public Boolean getWaterLeak() {
        return waterLeak;
    }

    public void setWaterLeak(final Boolean waterLeak) {
        this.waterLeak = waterLeak;
    }

    public Boolean getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(final Boolean occupancy) {
        this.occupancy = occupancy;
    }

    public Float getIlluminance() {
        return illuminance;
    }

    public void setIlluminance(final Float illuminance) {
        this.illuminance = illuminance;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(final String _sensorId) {
        sensorId = _sensorId;
    }

    public void setTimestamp(final String _timestamp) {
        timestamp = _timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(final String _time) {
        time = _time;
    }

    public String getDateTime() {
        if (time == null) {
            return null;
        }

        String timeTrimmed;

        int dotIndex = time.indexOf('.');

        if (dotIndex != -1) {
            timeTrimmed = time.substring(0, dotIndex);
        }
        else {
            timeTrimmed = time;
        }

        LocalDateTime dateTime =
                LocalDateTime.parse(timeTrimmed, dateTimeFormatter);

        return dateTime.format(timestampFormatter);
    }

    public int compare(final SensorValue o1, final SensorValue o2) {
        if (o1.getName() != null) {
            if (o2.getName() != null) {
                return o1.getName().compareTo(o2.getName());
            }

            return o1.getName().compareTo(o2.getSensorId());
        }

        if (o2.getName() != null) {
            return o1.getSensorId().compareTo(o2.getName());
        }

        return o1.getSensorId().compareTo(o2.getSensorId());
    }

    @Override
    public int compareTo(final SensorValue o2) {
        if (this.getName() != null) {
            if (o2.getName() != null) {
                return this.getName().compareTo(o2.getName());
            }

            return this.getName().compareTo(o2.getSensorId());
        }

        if (o2.getName() != null) {
            return this.getSensorId().compareTo(o2.getName());
        }

        return this.getSensorId().compareTo(o2.getSensorId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SensorValue.class.getSimpleName() + "[",
                "]").add("sensorId='" + sensorId + "'")
                .add("timestamp='" + timestamp + "'").add("time='" + time + "'")
                .add("name='" + name + "'")
                .add("airQuality='" + airQuality + "'")
                .add("battery=" + battery).add("batteryLow=" + batteryLow)
                .add("deviceTemperature=" + deviceTemperature)
                .add("humidity=" + humidity).add("temperature=" + temperature)
                .add("temperatureF=" + temperatureF).add("voc=" + voc)
                .add("linkQuality=" + linkQuality).add("tamper=" + tamper)
                .add("waterLeak=" + waterLeak).add("occupancy=" + occupancy)
                .add("illuminance=" + illuminance)
                .add("channel='" + channel + "'")
                .add("dateTime=" + this.getDateTime()).toString();
    }
}
