package com.callicoder.goparking.interaction.commands;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;

public class FetchRegNoWithCarColorCommand implements Command {

    private ParkingLotCommandHandler parkingLotCommandHandler;

    public FetchRegNoWithCarColorCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "registration_numbers_for_cars_with_colour <color>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length < 1) {
            throw new InvalidParameterException(
                    "Expected a parameter <color>"
            );
        }

        parkingLotCommandHandler.getRegistrationNumbersByColor(params[0]);
    }
}