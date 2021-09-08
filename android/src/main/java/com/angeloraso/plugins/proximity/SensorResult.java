package com.angeloraso.plugins.proximity;

public enum SensorResult {
    FAR(0),
    NEAR(1);

    private final int value;

    private SensorResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}