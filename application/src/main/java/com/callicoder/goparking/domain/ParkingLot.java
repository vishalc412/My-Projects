package com.callicoder.goparking.domain;

import com.callicoder.goparking.exceptions.ParkingLotFullException;
import com.callicoder.goparking.exceptions.SlotNotFoundException;
import org.apache.commons.lang.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLot {

    private final int numSlots;
    private final int numFloors;
    private SortedSet<ParkingSlot> availableSlots = new TreeSet<>();
    private Set<ParkingSlot> occupiedSlots = new HashSet<>();
    private Map<Car,ParkingSlot> parkingSlotMap = new HashMap<>();

    public ParkingLot(int numSlots) {
        if (numSlots <= 0) {
            throw new IllegalArgumentException(
                "Number of slots in the Parking Lot must be greater than zero."
            );
        }

        // Assuming Single floor since only numSlots are specified in the input.
        this.numSlots = numSlots;
        this.numFloors = 1;

        for (int i = 0; i < numSlots; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i + 1, 1);
            this.availableSlots.add(parkingSlot);
        }
    }

    public synchronized Ticket reserveSlot(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car must not be null");
        }

        if (this.isFull()) {
            throw new ParkingLotFullException();
        }

        ParkingSlot nearestSlot = this.availableSlots.first();

        nearestSlot.reserve(car);
        this.availableSlots.remove(nearestSlot);
        this.occupiedSlots.add(nearestSlot);
        this.parkingSlotMap.put(car,nearestSlot);
        return new Ticket(
            nearestSlot.getSlotNumber(),
            car.getRegistrationNumber(),
            car.getColor()
        );
    }
//Adding it to available slot
    public ParkingSlot leaveSlot(int slotNumber) {
        //implement leave
        Optional<ParkingSlot> slotToLeave = occupiedSlots.stream().filter(i->i.getSlotNumber()==slotNumber).findFirst();
        if(slotToLeave.isPresent()){
             occupiedSlots.remove(slotToLeave.get());
             availableSlots.add(slotToLeave.get());
        }
        else {
            throw new SlotNotFoundException(slotNumber);
        }
        return slotToLeave.get();
    }

    public boolean isFull() {
        return this.availableSlots.isEmpty();
    }

    public List<String> getRegistrationNumbersByColor(String color) {
        //implement getRegistrationNumbersByColor
        return occupiedSlots.stream().filter(i->i.getCar().getColor().equals(color)).map(i->i.getCar().getRegistrationNumber()).collect(Collectors.toList());

//        if(!filterRegNobyColor.isEmpty()){
//           return filterRegNobyColor.stream().map(i->i.getCar().getRegistrationNumber()).collect(Collectors.toList());
//        }
//        else {
//            return null;
//        }
    }

    public List<Integer> getSlotNumbersByColor(String color) {
        //implement getSlotNumbersByColor
        List<ParkingSlot> filterLotbyColor= occupiedSlots.stream().filter(i->i.getCar().getColor().equals(color)).collect(Collectors.toList());

        if(!filterLotbyColor.isEmpty()){
            return filterLotbyColor.stream().map(i->i.getSlotNumber()).collect(Collectors.toList());
        }
        else {
            return null;
        }
    }

    public Optional<Integer> getSlotNumberByRegistrationNumber(
        String registrationNumber
    ) {
        //implement getSlotNumberByRegistrationNumber

        List<ParkingSlot> filterLotByRegNo= occupiedSlots.stream().filter(i->i.getCar().getRegistrationNumber().equals(registrationNumber)).collect(Collectors.toList());

        if(!filterLotByRegNo.isEmpty()){
            return filterLotByRegNo.stream().map(i->i.getSlotNumber()).findFirst();
        }
        else {
            return null;
        }

    }

    public int getNumSlots() {
        return numSlots;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public SortedSet<ParkingSlot> getAvailableSlots() {
        return availableSlots;
    }

    public Set<ParkingSlot> getOccupiedSlots() {
        return occupiedSlots;
    }
}
