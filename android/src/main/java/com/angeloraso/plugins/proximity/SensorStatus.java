package com.angeloraso.plugins.proximity;

public enum SensorStatus {
    STOPPED(0),
    STARTING(1),
    RUNNING(2),
    ERROR_FAILED_TO_START(3);

    private final int value;

    private SensorStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}