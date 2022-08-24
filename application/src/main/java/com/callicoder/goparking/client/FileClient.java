package com.callicoder.goparking.client;

import com.callicoder.goparking.interaction.CommandFactory;
import java.io.BufferedReader;

public class FileClient extends Client {

    public FileClient(
        BufferedReader inputReader,
        CommandFactory commandFactory
    ) {
        super(inputReader, commandFactory);
    }
}
