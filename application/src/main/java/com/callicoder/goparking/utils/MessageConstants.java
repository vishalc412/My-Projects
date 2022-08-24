package com.callicoder.goparking.utils;

public interface MessageConstants {
    String PARKING_LOT_ALREADY_CREATED =
        "Oops! parking lot is already created.";
    String PARKING_LOT_NOT_CREATED = "Sorry, parking lot is not created yet.";

    String PARKING_LOT_CREATED_MSG = "Created a parking lot with %s slots";
    String PARKING_SLOT_ALLOCATED_MSG = "Allocated slot number: %s";
    String PARKING_LOT_FULL_MSG = "Sorry, parking lot is full";

    String SLOT_NO = "Slot No.";
    String REGISTRATION_NO = "Registration No";
    String Color = "Colour";
    String DUPLICATE_VEHICLE_MESSAGE =
        "A car already parked with this registration number";
    String PARKING_SLOT_INVALID =
            "Parking slot is unoccupied or invalid";
}
