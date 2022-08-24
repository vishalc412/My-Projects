package com.callicoder.goparking.interaction.commands;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.commands.Command;

public class FetchSlotNoWithRegNoCommand implements Command {

    private ParkingLotCommandHandler parkingLotCommandHandler;

    public FetchSlotNoWithRegNoCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "slot_number_for_registration_number <registration Number>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length < 1) {
            throw new InvalidParameterException(
                    "Expected a parameter <registration Number>"
            );
        }

        parkingLotCommandHandler.getSlotNumberByRegistrationNumber(params[0]);
    }
}