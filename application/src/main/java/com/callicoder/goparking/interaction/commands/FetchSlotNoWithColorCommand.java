package com.callicoder.goparking.interaction.commands;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;

public class FetchSlotNoWithColorCommand implements Command {

    private ParkingLotCommandHandler parkingLotCommandHandler;

    public FetchSlotNoWithColorCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "slot_numbers_for_cars_with_colour <color>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length < 1) {
            throw new InvalidParameterException(
                    "Expected a parameter <color>"
            );
        }

        parkingLotCommandHandler.getSlotNumbersByColor(params[0]);
    }
}