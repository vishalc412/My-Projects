package com.callicoder.goparking.exceptions;

public class SlotNotAvailableException extends RuntimeException {

    private int slotNumber;

    public SlotNotAvailableException(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String getMessage() {
        return "Slot number " + slotNumber + " is not available!";
    }
}
