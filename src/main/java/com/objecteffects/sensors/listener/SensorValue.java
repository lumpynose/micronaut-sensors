package com.objecteffects.sensors.listener;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;

@Serdeable
public class SensorValue {
    LocalDateTime timestamp;
    @JsonProperty("airquality")
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
        return "SensorValue{" + "timestamp=" + timestamp + ", airQuality='" +
                airQuality + '\'' + ", battery=" + battery + ", batteryLow=" +
                batteryLow + ", deviceTemperature=" + deviceTemperature +
                ", humidity=" + humidity + ", temperature=" + temperature +
                ", voc=" + voc + ", linkQuality=" + linkQuality + ", tamper=" +
                tamper + ", waterLeak=" + waterLeak + ", occupancy=" +
                occupancy + ", illuminance=" + illuminance + '}';
    }
}
