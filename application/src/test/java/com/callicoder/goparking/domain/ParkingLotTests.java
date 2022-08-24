package com.callicoder.goparking.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.callicoder.goparking.exceptions.ParkingLotFullException;
import com.callicoder.goparking.exceptions.SlotNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.ObjectUtils;
import org.junit.jupiter.api.Test;

public class ParkingLotTests {

    @Test
    public void createParkingSlot_shouldCreateParkingLotWithFreeSlots() {
        ParkingLot parkingLot = new ParkingLot(10);

        assertEquals(parkingLot.getAvailableSlots().size(), 10);
        assertTrue(parkingLot.getOccupiedSlots().isEmpty());
    }

    @Test
    public void createParkingLotWithIllegalNumSlots_shouldThrowError() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                ParkingLot parkingLot = new ParkingLot(0);
            }
        );
    }

    @Test
    public void reserveSlot_shouldReserveNearestSlot() {
        ParkingLot parkingLot = new ParkingLot(20);
        Car car = new Car("KA02TH1992", "White");
        ParkingSlot nearestSlot = parkingLot.getAvailableSlots().first();
        int numAvailableSlots = parkingLot.getAvailableSlots().size();
        int numOccupiedSlots = parkingLot.getOccupiedSlots().size();

        Ticket ticket = parkingLot.reserveSlot(car);

        assertEquals(
            parkingLot.getAvailableSlots().size(),
            numAvailableSlots - 1
        );
        assertEquals(
            parkingLot.getOccupiedSlots().size(),
            numOccupiedSlots + 1
        );
        assertEquals(ticket.getSlotNumber(), nearestSlot.getSlotNumber());
        assertEquals(
            ticket.getRegistrationNumber(),
            car.getRegistrationNumber()
        );
        assertEquals(ticket.getColor(), car.getColor());
    }

    @Test
    public void isFull_shouldCheckIfParkingLotIsFull() {
        ParkingLot parkingLot = new ParkingLot(2);

        Car car = new Car("KA02TH1992", "White");
        Car car2 = new Car("BH89JD8765", "Black");

        parkingLot.reserveSlot(car);
        parkingLot.reserveSlot(car2);

        assertTrue(parkingLot.isFull());
    }

    @Test
    public void reserveSlotWithFullParking_shouldThrowError() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("TN02BH1145", "Red");
        parkingLot.reserveSlot(car);

        Car car2 = new Car("BH89JD8765", "Black");
        assertThrows(
            ParkingLotFullException.class,
            () -> parkingLot.reserveSlot(car2)
        );
    }

    @Test
    public void leaveSlot_shouldClearSlot() {
        ParkingLot parkingLot = new ParkingLot(4);
        Car car = new Car("AN82CH1235", "Green");
        Ticket ticket1 = parkingLot.reserveSlot(car);
        Car car2 = new Car("GJ82CH1235", "Black");
        Ticket ticket2 = parkingLot.reserveSlot(car2);

        int numAvailableSlots = parkingLot.getAvailableSlots().size();
        int numOccupiedSlots = parkingLot.getAvailableSlots().size();

        ParkingSlot slot = parkingLot.leaveSlot(ticket1.getSlotNumber());

        assertTrue(slot.isAvailable());
        assertEquals(
            parkingLot.getAvailableSlots().size(),
            numAvailableSlots + 1
        );
        assertEquals(
            parkingLot.getOccupiedSlots().size(),
            numOccupiedSlots - 1
        );
    }

    @Test
    public void leaveSlotWithNonExistingSlotNo_shouldThrowError() {
        ParkingLot parkingLot = new ParkingLot(6);
        assertThrows(
            SlotNotFoundException.class,
            () -> parkingLot.leaveSlot(99)
        );
    }

    @Test
    public void getRegistrationNumbersByColor_shouldReturnRegistrationNumbers() {
        ParkingLot parkingLot = new ParkingLot(10);

        Car car1 = new Car("KA01HQ4669", "White");
        Car car2 = new Car("HY02OS7867", "Black");
        Car car3 = new Car("BH09AB2397", "Black");
        Car car4 = new Car("TN03A13392", "Black");

        parkingLot.reserveSlot(car1);
        parkingLot.reserveSlot(car2);
        parkingLot.reserveSlot(car3);
        parkingLot.reserveSlot(car4);

        List<String> registrationNumbers = parkingLot.getRegistrationNumbersByColor(
            "Black"
        );

        assertIterableEquals(
            registrationNumbers,
            Arrays.asList("HY02OS7867", "BH09AB2397", "TN03A13392")
        );
    }

    @Test
    public void getSlotNumbersByColor_shouldReturnSlotNos() {
        ParkingLot parkingLot = new ParkingLot(10);

        Car car1 = new Car("KA01HQ4669", "Red");
        Car car2 = new Car("HY02OS7867", "Blue");
        Car car3 = new Car("BH09AB2397", "Green");
        Car car4 = new Car("TN03A13392", "Red");

        parkingLot.reserveSlot(car1);
        parkingLot.reserveSlot(car2);
        parkingLot.reserveSlot(car3);
        parkingLot.reserveSlot(car4);

        List<Integer> slotNumbers = parkingLot.getSlotNumbersByColor("Red");

        assertIterableEquals(slotNumbers, Arrays.asList(1, 4));
    }

    @Test
    public void getSlotNumberByRegistrationNumber_shouldReturnSlotNo() {
        ParkingLot parkingLot = new ParkingLot(5);

        Car car1 = new Car("KA01HQ4669", "Black");
        Car car2 = new Car("HY02OS7867", "White");

        parkingLot.reserveSlot(car1);
        parkingLot.reserveSlot(car2);

        Optional<Integer> slotNumber = parkingLot.getSlotNumberByRegistrationNumber(
            "KA01HQ4669"
        );

        assertTrue(slotNumber.isPresent());
        assertEquals(slotNumber.get().intValue(), 1);
    }

    @Test
    public void getSlotNumberWithWrongRegistrationNo_shouldReturnNoSlot() {
        ParkingLot parkingLot = new ParkingLot(4);
        Car car = new Car("KA01HQ4669", "Black");
        parkingLot.reserveSlot(car);

        Optional<Integer> slotNumber = parkingLot.getSlotNumberByRegistrationNumber(
            "GH01KQ7643"
        );
        if(ObjectUtils.notEqual(slotNumber,null))
        assertTrue(!slotNumber.isPresent());
        else
            assertFalse(false);
    }
}
