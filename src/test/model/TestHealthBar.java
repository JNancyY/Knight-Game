package model;

import model.exceptions.ColourException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestHealthBar {
    HealthBar hp;
    int width = 100;

    @BeforeEach
    public void runBefore() {
        hp = new HealthBar(width);
    }

    @Test
    public void testChangeWidth() {
      assertEquals(width, hp.getBarWidth());
      hp.decreaseBarWidth(10);
      assertEquals(width - 10, hp.getBarWidth());
    }

    @Test
    public void testChangeWidthLargeDamage() {
        assertEquals(width, hp.getBarWidth());
        hp.decreaseBarWidth(width + 1);
        assertEquals(0, hp.getBarWidth());
    }

    @Test
    public void testSetColourNothingThrown() {
        try {
            hp.checkColour("green");
            assertEquals("green", hp.getBarColourString());

            hp.checkColour("yellow");
            assertEquals("yellow", hp.getBarColourString());

            hp.checkColour("red");
            assertEquals("red", hp.getBarColourString());
        } catch (ColourException c) {
            fail("Colour Exception should not be caught");
        }
    }

    @Test
    public void testSetColourColourExceptionThrown() {
        try {
            hp.checkColour("blue");
            fail("Did not catch Colour Exception");
        } catch (ColourException c) {

        }
    }

}
