package com.taras.serba.tts.exceptions;

public class TextTooLongException  extends RuntimeException{

    public TextTooLongException() {
        super("Text length must be lower than 90.");
    }
}
