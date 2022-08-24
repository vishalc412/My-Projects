package com.callicoder.goparking.handler;

import static com.callicoder.goparking.utils.MessageConstants.*;

import com.callicoder.goparking.domain.Car;
import com.callicoder.goparking.domain.ParkingLot;
import com.callicoder.goparking.domain.ParkingSlot;
import com.callicoder.goparking.domain.Ticket;
import com.callicoder.goparking.exceptions.ParkingLotFullException;
import com.callicoder.goparking.exceptions.SlotNotFoundException;
import com.callicoder.goparking.utils.StringUtils;
import org.apache.commons.lang.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingLotCommandHandler {

    private ParkingLot parkingLot;

    public void createParkingLot(int numSlots) {
        if (isParkingLotCreated()) {
            System.out.println(PARKING_LOT_ALREADY_CREATED);
            return;
        }

        try {
            parkingLot = new ParkingLot(numSlots);
            System.out.println(
                String.format(PARKING_LOT_CREATED_MSG, parkingLot.getNumSlots())
            );
        } catch (IllegalArgumentException ex) {
            System.out.println("Bad input: " + ex.getMessage());
        }
    }

    public void park(String registrationNumber, String color) {
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        //VALIDATION FOR DUPLICATE VEHICLE
        ParkingSlot existingSlot = parkingLot.getOccupiedSlots().stream().filter(i->i.getCar().getRegistrationNumber().equals(registrationNumber))
                .findFirst().orElse(null);
        if(!ObjectUtils.notEqual(existingSlot,null)) {
            try {
                Car car = new Car(registrationNumber, color);
                Ticket ticket = parkingLot.reserveSlot(car);
                System.out.println(
                        String.format(
                                PARKING_SLOT_ALLOCATED_MSG,
                                ticket.getSlotNumber()
                        )
                );
            } catch (IllegalArgumentException ex) {
                System.out.println("Bad input: " + ex.getMessage());
            } catch (ParkingLotFullException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else{
            System.out.println(DUPLICATE_VEHICLE_MESSAGE);
            return;
        }
    }

    public void status() {
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }

        System.out.println(SLOT_NO + "    " + REGISTRATION_NO + "    " + Color);
        parkingLot
            .getOccupiedSlots()
            .forEach(
                parkingSlot -> {
                    System.out.println(
                        StringUtils.rightPadSpaces(
                            Integer.toString(parkingSlot.getSlotNumber()),
                            SLOT_NO.length()
                        ) +
                        "    " +
                        StringUtils.rightPadSpaces(
                            parkingSlot.getCar().getRegistrationNumber(),
                            REGISTRATION_NO.length()
                        ) +
                        "    " +
                        parkingSlot.getCar().getColor()
                    );
                }
            );
    }

    private boolean isParkingLotCreated() {
        if (parkingLot == null) {
            return false;
        }
        return true;
    }

    public void leaveParkingSlot(String slotNumber){
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        try {

            ParkingSlot slotToExit = parkingLot.leaveSlot(Integer.parseInt(slotNumber));

            if (ObjectUtils.notEqual(slotToExit, null)) {
                System.out.println(SLOT_NO + " " + slotToExit.getSlotNumber() + " is free");
            } else {
                System.out.println(PARKING_SLOT_INVALID);
            }
        }
        catch (SlotNotFoundException e){
            e.getMessage();
            System.out.println(e.getMessage());
        }
    }
    public void getRegistrationNumbersByColor(String color){
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        List<String> regNoList = parkingLot.getRegistrationNumbersByColor(color);
        if(!regNoList.isEmpty()){
            regNoList.stream().forEach(System.out::println);
        }
        else {
            System.out.println("No car present");
        }
    }

    public void getSlotNumberByRegistrationNumber(String regNo){
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        Optional<Integer> slotNumber = parkingLot.getSlotNumberByRegistrationNumber(regNo);

        try {
            if (slotNumber.isPresent()) {
                System.out.println(slotNumber.get());
            } else {
                System.out.println("not found");
            }
        }
        catch (NullPointerException e){
            System.out.println("not found");
        }
    }

    public void getSlotNumbersByColor(String color){
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        List<Integer> slotNumberList = parkingLot.getSlotNumbersByColor(color);

        try {
            if(!slotNumberList.isEmpty()){
                slotNumberList.stream().forEach(System.out::println);
            }
            else {
                System.out.println("No car present");
            }
        }
        catch (NullPointerException e){
            System.out.println("not found");
        }
    }
}
