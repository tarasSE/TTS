package com.taras.serba.tts.exceptions;

public class UnknownItemException extends RuntimeException {

    public UnknownItemException() {
        super("The current value is not provided.");
    }
}
