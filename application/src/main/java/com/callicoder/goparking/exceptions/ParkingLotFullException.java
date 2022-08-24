package com.callicoder.goparking.exceptions;

import static com.callicoder.goparking.utils.MessageConstants.PARKING_LOT_FULL_MSG;

public class ParkingLotFullException extends RuntimeException {

    @Override
    public String getMessage() {
        return PARKING_LOT_FULL_MSG;
    }
}
