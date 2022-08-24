package com.callicoder.goparking.domain;

public class Ticket {

    private int slotNumber;
    private String registrationNumber;
    private String color;

    public Ticket(int slotNumber, String registrationNumber, String color) {
        this.slotNumber = slotNumber;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }
}
