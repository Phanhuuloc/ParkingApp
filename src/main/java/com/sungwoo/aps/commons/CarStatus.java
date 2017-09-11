package com.sungwoo.aps.commons;

/**
 * @author phloc
 */
public enum CarStatus {
    MOVING(0, "Car is moving"),
    WAITING(1, "Car waiting"),
    PARKING_STARTING(2, "Parking starting"),
    PARKING(3, "Parking complete: %s area"),
    CALL_STARTING(4, "Call starting"),
    ARRIVED(5, "Car arrived at your location");

    private final int value;
    private final String reasonPhrase;

    CarStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return value;
    }

    public String reasonPhrase() {
        return reasonPhrase;
    }

}
