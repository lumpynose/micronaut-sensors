package com.objecteffects.sensors.listener;

public enum Protocol {
    ZIGBEE("zigbee"), MHZ433("433mhz"), ZWAVE("zwave");

    private final String value;

    Protocol(final String _value) {
        this.value = _value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
