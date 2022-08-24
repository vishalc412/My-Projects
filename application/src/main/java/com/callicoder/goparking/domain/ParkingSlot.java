package com.callicoder.goparking.domain;

import com.callicoder.goparking.exceptions.SlotNotAvailableException;
import java.util.Objects;

public class ParkingSlot implements Comparable<ParkingSlot> {

    private final int slotNumber;
    private final int floorNumber;
    private Car car;

    public ParkingSlot(int slotNumber, int floorNumber) {
        this.floorNumber = floorNumber;
        this.slotNumber = slotNumber;
    }

    public boolean isAvailable() {
        return car == null;
    }

    public boolean reserve(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car must not be null");
        }

        if (!isAvailable()) {
            throw new SlotNotAvailableException(this.slotNumber);
        }

        this.car = car;
        return true;
    }

    public boolean clear() {
        this.car = null;
        return true;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Car getCar() {
        return car;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return slotNumber == that.slotNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber);
    }

    @Override
    public int compareTo(ParkingSlot that) {
        return this.slotNumber - that.slotNumber;
    }
}
