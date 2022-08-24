package com.callicoder.goparking.exceptions;

public class CommandNotFoundException extends Exception {

    private String name;

    public CommandNotFoundException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "Command " + name + " not found";
    }
}
