package com.objecteffects.sensors.listener;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Serdeable
public class SensorValue {
    //    String zigbeeId;
    //    String rtl433Id;
    String sensorId;
    String timestamp;
    String name;
    @JsonProperty("air_quality")
    String airQuality;
    Float battery;
    @JsonProperty("batterylow")
    Boolean batteryLow;
    @JsonProperty("devicetemperature")
    Float deviceTemperature;
    Float humidity;
    Float temperature;
    @JsonProperty("temperature_F")
    Float temperatureF;
    Float voc;
    @JsonProperty("linkquality")
    Float linkQuality;
    Boolean tamper;
    @JsonProperty("water_leak")
    Boolean waterLeak;
    @JsonProperty("occupancy")
    Boolean occupancy;
    Float illuminance;
    @JsonProperty("channel")
    String channel;
    String protocol;

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

//    public String getRtl433Id() {
//        return rtl433Id;
//    }

//    public void setRtl433Id(final String rtl433Id) {
//        this.rtl433Id = rtl433Id;
//    }

//    public String getZigbeeId() {
//        return zigbeeId;
//    }

//    public void setZigbeeId(final String zigbeeId) {
//        this.zigbeeId = zigbeeId;
//    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd MMM HH:mm");

        this.timestamp = timestamp.format(formatter);
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

    public void setTemperature(final Float temperature) {
        this.temperature = temperature;
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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(final String _protocol) {
        protocol = _protocol;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SensorValue.class.getSimpleName() + "[",
                "]").add("sensorId='" + sensorId + "'")
                .add("timestamp='" + timestamp + "'").add("name='" + name + "'")
                .add("airQuality='" + airQuality + "'")
                .add("battery=" + battery).add("batteryLow=" + batteryLow)
                .add("deviceTemperature=" + deviceTemperature)
                .add("humidity=" + humidity).add("temperature=" + temperature)
                .add("temperatureF=" + temperatureF).add("voc=" + voc)
                .add("linkQuality=" + linkQuality).add("tamper=" + tamper)
                .add("waterLeak=" + waterLeak).add("occupancy=" + occupancy)
                .add("illuminance=" + illuminance)
                .add("channel='" + channel + "'").toString();
    }
}
