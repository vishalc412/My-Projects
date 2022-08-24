package com.callicoder.goparking;

import com.callicoder.goparking.client.Client;
import com.callicoder.goparking.client.ClientFactory;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.CommandFactory;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GoParkingApplication {

    public static void main(String[] args) {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        CommandFactory commandFactory = CommandFactory.init(
            parkingLotCommandHandler
        );

        try {
            Client client = ClientFactory.buildClient(args, commandFactory);
            client.handleInput();
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry! the supplied input file was not found!");
        } catch (IOException ex) {
            System.out.println("Something went wrong. Please try again!");
        }
    }
}
