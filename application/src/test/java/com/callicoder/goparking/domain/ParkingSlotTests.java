package com.callicoder.goparking.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.callicoder.goparking.exceptions.SlotNotAvailableException;
import org.junit.jupiter.api.Test;

public class ParkingSlotTests {

    @Test
    public void reserve_shouldReserveSlot() {
        ParkingSlot parkingSlot = new ParkingSlot(1, 1);
        Car car = new Car("KA01HQ4669", "Red");

        assertTrue(parkingSlot.reserve(car));
        assertFalse(parkingSlot.isAvailable());
    }

    @Test
    public void clear_shouldClearSlot() {
        ParkingSlot parkingSlot = new ParkingSlot(2, 1);
        Car car = new Car("KA01AS4567", "White");
        parkingSlot.reserve(car);

        assertTrue(parkingSlot.clear());
        assertTrue(parkingSlot.isAvailable());
    }

    @Test
    public void reserveUnavailableSlot_shouldThrowError() {
        ParkingSlot parkingSlot = new ParkingSlot(3, 1);
        Car car = new Car("TN02HAS9123", "Black");
        parkingSlot.reserve(car);

        assertThrows(
            SlotNotAvailableException.class,
            () -> parkingSlot.reserve(car)
        );
    }

    @Test
    public void reserveSlotWithMissingCar_shouldThrowError() {
        ParkingSlot parkingSlot = new ParkingSlot(5, 1);
        assertThrows(
            IllegalArgumentException.class,
            () -> parkingSlot.reserve(null)
        );
    }

    @Test
    public void compareTo_shouldCompareSlotsBasedOnSlotNumber() {
        ParkingSlot parkingSlot1 = new ParkingSlot(12, 1);
        ParkingSlot parkingSlot2 = new ParkingSlot(15, 2);

        assertTrue(parkingSlot1.compareTo(parkingSlot2) < 0);
    }

    @Test
    public void equals_shouldCheckEqualityBasedOnSlotNumber() {
        ParkingSlot parkingSlot1 = new ParkingSlot(10, 3);
        ParkingSlot parkingSlot2 = new ParkingSlot(10, 3);

        assertTrue(parkingSlot1.equals(parkingSlot2));
    }
}
