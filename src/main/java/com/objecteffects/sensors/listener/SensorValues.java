package com.objecteffects.sensors.listener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Serdeable
public class SensorValues {
    String zigbeeId;
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
    Float voc;
    @JsonProperty("linkquality")
    Float linkQuality;
    Boolean tamper;
    @JsonProperty("waterleak")
    Boolean waterLeak;
    Boolean occupancy;
    Float illuminance;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getZigbeeId() {
        return zigbeeId;
    }

    public void setZigbeeId(final String zigbeeId) {
        this.zigbeeId = zigbeeId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM HH:mm");

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorValue{");
        sb.append("zigbeeId='").append(zigbeeId).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append(", name='").append(name).append('\'');
        sb.append(", airQuality='").append(airQuality).append('\'');
        sb.append(", battery=").append(battery);
        sb.append(", batteryLow=").append(batteryLow);
        sb.append(", deviceTemperature=").append(deviceTemperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", temperature=").append(temperature);
        sb.append(", voc=").append(voc);
        sb.append(", linkQuality=").append(linkQuality);
        sb.append(", tamper=").append(tamper);
        sb.append(", waterLeak=").append(waterLeak);
        sb.append(", occupancy=").append(occupancy);
        sb.append(", illuminance=").append(illuminance);
        sb.append('}');
        return sb.toString();
    }
}
