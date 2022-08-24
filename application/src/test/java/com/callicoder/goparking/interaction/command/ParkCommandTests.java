package com.callicoder.goparking.interaction.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.commands.ParkCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ParkCommandTests {

    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static ParkCommand parkCommand;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkCommand = new ParkCommand(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(
            InvalidParameterException.class,
            () -> parkCommand.execute(params)
        );
    }

    @Test
    public void executeWithoutTwoArgs_shouldThrowError() {
        String[] params = { "Foo" };
        assertThrows(
            InvalidParameterException.class,
            () -> parkCommand.execute(params)
        );
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = { "KA-01-HQ-4669", "White" };
        Assertions.assertDoesNotThrow(() -> parkCommand.execute(params));
    }
}
