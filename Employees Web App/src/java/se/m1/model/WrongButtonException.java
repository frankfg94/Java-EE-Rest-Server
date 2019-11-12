package se.m1.model;

//Class for the exception when the wrong button is pressed
public class WrongButtonException extends Exception {

    public WrongButtonException(String message) {
        super(message);
    }
}
