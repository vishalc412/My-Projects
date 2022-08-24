package com.callicoder.goparking.handler;

import static com.callicoder.goparking.utils.MessageConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.*;

public class ParkingLotCommandHandlerTests {

    private static PrintStream sysOut;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setupStreams() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void resetStream() {
        outContent.reset();
    }

    @Test
    public void testCreateParkingLotOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(10);

        assertEquals(
            String.format(PARKING_LOT_CREATED_MSG, 10) + System.lineSeparator(),
            outContent.toString()
        );
    }

    @Test
    public void testCreateMultipleParkingLotOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(10);
        parkingLotCommandHandler.createParkingLot(6);

        assertTrue(
            outContent
                .toString()
                .endsWith(PARKING_LOT_ALREADY_CREATED + System.lineSeparator())
        );
    }

    @Test
    public void testParkOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(6);
        parkingLotCommandHandler.park("KA-01-HH-3141", "Black");

        assertTrue(
            outContent
                .toString()
                .endsWith("Allocated slot number: 1" + System.lineSeparator())
        );
        assertEquals(
            String.format(PARKING_LOT_CREATED_MSG, 6) +
            System.lineSeparator() +
            String.format(PARKING_SLOT_ALLOCATED_MSG, 1) +
            System.lineSeparator(),
            outContent.toString()
        );
    }

    @Test
    public void testParkWithNoParkingLotOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.park("KA-01-HQ-4669", "White");
        assertTrue(
            outContent
                .toString()
                .endsWith(PARKING_LOT_NOT_CREATED + System.lineSeparator())
        );
    }

    @Test
    public void testStatusWithNoParkingLotOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.status();
        assertTrue(
            outContent
                .toString()
                .endsWith(PARKING_LOT_NOT_CREATED + System.lineSeparator())
        );
    }

    @Test
    public void testParkDuplicateVehicle() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(6);
        assertFalse(
            outContent
                .toString()
                .endsWith(PARKING_LOT_ALREADY_CREATED + System.lineSeparator())
        );
        parkingLotCommandHandler.park("KA-01-HH-3141", "Black");

        assertTrue(
            outContent
                .toString()
                .endsWith("Allocated slot number: 1" + System.lineSeparator())
        );
        parkingLotCommandHandler.park("KA-01-HH-3141", "White");
        assertTrue(
            outContent
                .toString()
                .endsWith(DUPLICATE_VEHICLE_MESSAGE + System.lineSeparator())
        );
    }

    @AfterAll
    public static void revertStreams() {
        System.setOut(sysOut);
    }
}
