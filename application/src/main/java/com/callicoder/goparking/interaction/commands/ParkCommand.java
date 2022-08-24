package com.callicoder.goparking.interaction.commands;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;

public class ParkCommand implements Command {

    private ParkingLotCommandHandler parkingLotCommandHandler;

    public ParkCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "park <registrationNumber> <color>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length < 2) {
            throw new InvalidParameterException(
                "Expected two parameters <registrationNumber> and <color>"
            );
        }

        parkingLotCommandHandler.park(params[0], params[1]);
    }
}
