package com.callicoder.goparking.exceptions;

public class SlotNotFoundException extends RuntimeException {

    private Integer slotNumber;

    public SlotNotFoundException(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String getMessage() {
        return "Slot number " + slotNumber + " not found!";
    }
}
