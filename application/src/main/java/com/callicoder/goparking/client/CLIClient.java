package com.callicoder.goparking.client;

import com.callicoder.goparking.interaction.CommandFactory;
import java.io.BufferedReader;

public class CLIClient extends Client {

    public CLIClient(
        BufferedReader inputReader,
        CommandFactory commandFactory
    ) {
        super(inputReader, commandFactory);
    }
}
