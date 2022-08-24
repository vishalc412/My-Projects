package com.callicoder.goparking.client;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.CommandFactory;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClientFactoryTests {

    private static CommandFactory commandFactory;

    @BeforeAll
    public static void commandFactory() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        commandFactory = CommandFactory.init(parkingLotCommandHandler);
    }

    @Test
    public void buildClientWithNoArg_shouldCreateCLIClient()
        throws FileNotFoundException {
        String[] args = {};
        Client client = ClientFactory.buildClient(args, commandFactory);

        assertTrue(client instanceof CLIClient);
    }

    @Test
    public void buildClientWithValidFilePath_shouldCreateFileClient()
        throws FileNotFoundException {
        String[] args = { "file_input.txt" };
        Client client = ClientFactory.buildClient(args, commandFactory);

        assertTrue(client instanceof FileClient);
    }

    @Test
    public void buildClientWithInvalidFilePath_shouldThrowError()
        throws FileNotFoundException {
        String[] args = { "invalid_file_path.txt" };
        assertThrows(
            FileNotFoundException.class,
            () -> ClientFactory.buildClient(args, commandFactory)
        );
    }
}
