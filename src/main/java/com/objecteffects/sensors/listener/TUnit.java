package com.objecteffects.sensors.listener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * the field temperature_F is set for rs433 devices while the field
 * temperature (Celsius) is set for zigbee devices. I'm assuming that a
 * sensor has one of the two temperature values.
 */
public enum TUnit {
    Celsius("C") {
        @Override
        public double convert(final Float celsius) {
            return (celsius - 32.0) * (5.0 / 9.0);
        }
    }, Fahrenheit("F") {
        @Override
        public double convert(final Float celsius) {
            return celsius * (9.0 / 5.0) + 32.0;
        }
    }, Kelvin("K") {
        @Override
        public double convert(final Float celsius) {
            return celsius + 273.15;
        }
    };

    private static final Map<String, TUnit> ENUM_MAP;

    static {
        final Map<String, TUnit> map = new HashMap<>();

        for (final TUnit tunit : TUnit.values()) {
            map.put(tunit.toString().toLowerCase(), tunit);
        }

        ENUM_MAP = Collections.unmodifiableMap(map);

        // Stream.of(TUnit.values()).collect(toMap(Enum::name, identity()));
    }

    private final String letter;

    TUnit(final String _letter) {
        this.letter = _letter;
    }

    public static TUnit get(final String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    @Override
    public String toString() {
        return this.letter;
    }

    public abstract double convert(final Float celsius);
}
