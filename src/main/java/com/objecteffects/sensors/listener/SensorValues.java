package com.objecteffects.sensors.listener;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Serdeable
public class SensorValues {
    String zigbeeId;
    String rtl433Id;
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

    public String getRtl433Id() {
        return rtl433Id;
    }

    public void setRtl433Id(final String rtl433Id) {
        this.rtl433Id = rtl433Id;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorValues{");
        sb.append("zigbeeId='").append(zigbeeId).append('\'');
        sb.append(", rtl433Id='").append(rtl433Id).append('\'');
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", airQuality='").append(airQuality).append('\'');
        sb.append(", battery=").append(battery);
        sb.append(", batteryLow=").append(batteryLow);
        sb.append(", deviceTemperature=").append(deviceTemperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", temperature=").append(temperature);
        sb.append(", temperatureF=").append(temperatureF);
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
