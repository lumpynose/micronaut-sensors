package com.objecteffects.sensors.listener;

import java.util.Comparator;

public class SensorValueComparator implements Comparator<SensorValue> {
    @Override
    public int compare(final SensorValue o1, final SensorValue o2) {
        if (o1.getName() != null) {
            if (o2.getName() != null) {
                return o1.getName().compareTo(o2.getName());
            }
            else {
                return o1.getName().compareTo(o2.getSensorId());
            }
        }
        else {
            if (o2.getName() != null) {
                return o1.getSensorId().compareTo(o2.getName());
            }
            else {
                return o1.getSensorId().compareTo(o2.getSensorId());
            }
        }
    }
}
