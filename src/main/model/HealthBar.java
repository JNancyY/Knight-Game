package model;

import model.exceptions.ColourException;

import java.awt.*;

public class HealthBar {
    private static final int barHeight = 5;
    private String barColourString;
    private Color barColour;
    private int barWidth;
    private Color green = new Color(18, 190, 44, 223);
    private Color yellow = new Color(255, 230, 56);
    private Color red = new Color(255, 61, 51);

    public HealthBar(int width) {
        barColourString = "green";
        barColour = green;
        barWidth = width;
    }

    // MODIFIES: this
    // EFFECTS: decreases the barWidth by damage amount
    public void decreaseBarWidth(int damageTaken) {
        if (damageTaken >= barWidth) {
            damageTaken = barWidth;
        }
        barWidth = barWidth - damageTaken;
    }

    // EFFECTS: returns barWidth
    public int getBarWidth() {
        return barWidth;
    }

    // MODIFIES: this
    // EFFECTS: if !(colour.equals("red")
    //				or colour.equals("yellow")
    // 			or colour.equals("green"))
    // 			then ColourException is thrown
    //             otherwise, car colour is set to colour
    public void checkColour(String colour) throws ColourException {
        if (!barColourString.equals(colour)) {
            if (!isValidColour(colour)) {
                throw new ColourException(colour);
            }
            barColourString = colour;
            setBarColour(barColourString);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets bar Colour to given colour
    private void setBarColour(String colour) {

        if (colour.equals("green")) {
            barColour = green;
        }

        if (colour.equals("yellow")) {
            barColour = yellow;
        }

        if (colour.equals("red")) {
            barColour = red;
        }
    }

    // EFFECTS: Returns current bar colour
    public Color getColour() {
        return barColour;
    }

    // EFFECTS: returns bar colour string
    public String getBarColourString() {
        return barColourString;
    }

    // EFFECTS: returns true if colour is valid, false otherwise
    private boolean isValidColour(String colour) {
        return (colour.equals("green") || colour.equals("yellow")
                || colour.equals("red"));
    }

}
