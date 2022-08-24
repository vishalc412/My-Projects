package com.callicoder.goparking.interaction.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.commands.CreateLotCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreateLotCommandTests {

    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static CreateLotCommand createLotCommand;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        createLotCommand = new CreateLotCommand(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(
            InvalidParameterException.class,
            () -> createLotCommand.execute(params)
        );
    }

    @Test
    public void executeWithInvalidNumSlot_shouldThrowError() {
        String[] params = { "RandomString" };
        assertThrows(
            InvalidParameterException.class,
            () -> createLotCommand.execute(params)
        );
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = { "6" };
        assertDoesNotThrow(() -> createLotCommand.execute(params));
    }
}
