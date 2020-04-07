package model.exceptions;

public class ColourException extends Exception {

    public ColourException(String colour) {
        super("Invalid Health Bar Colour: " + colour);
    }
}
