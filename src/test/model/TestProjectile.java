package model;

import model.Arrow;
import model.FireBall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProjectile {
    Arrow arrow;
    FireBall fireBall;

    @BeforeEach
    public void runBefore() {
        arrow = new Arrow(0,0);
        fireBall = new FireBall(0, 0);
    }

    @Test
    public void testMoveArrow() {
        assertEquals(0, arrow.getX());
        assertEquals(0, arrow.getY());

        arrow.move();

        assertEquals(5, arrow.getX());
        assertEquals(0, arrow.getY());
    }

    @Test
    public void testMoveFireBall() {
        assertEquals(0, fireBall.getX());
        assertEquals(0, fireBall.getY());

        fireBall.move();

        assertEquals(-2, fireBall.getX());
        assertEquals(0, fireBall.getY());
    }
}
