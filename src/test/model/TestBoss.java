package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoss {
    private static final int hp = 100;
    private static final int locX = 4;
    private static final int locY = 4;
    private Boss boss;

    @BeforeEach
    public void runBefore() {
        boss = new Boss(hp, locX, locY);
    }

    @Test
    public void testGetX() {
        assertEquals(locX, boss.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(locY, boss.getY());
    }

    @Test
    public void testGetHealth() {
        assertEquals(100, boss.getHealth());
    }

    @Test
    public void testDealDamage() {
        assertEquals(100, boss.getHealth());

        boss.takeDamage(20);

        assertEquals(80, boss.getHealth());
    }

    @Test
    public void rightBoundary() {
        Boss rightBound = new Boss(100,BossFightGame.width + 1, 9);
        rightBound.handleHorzBoundary();
        assertEquals(BossFightGame.width - boss.width, rightBound.getX());
    }

    @Test
    public void leftBoundary() {
        Boss leftBound = new Boss(100,-BossFightGame.width - 1, 9);
        leftBound.handleHorzBoundary();
        assertEquals(BossFightGame.width / 2, leftBound.getX());
    }

    @Test
    public void upperBoundary() {
        Boss upBound = new Boss(100,9,  - 1);
        upBound.handleVertBoundary();
        assertEquals(50, upBound.getY());
    }

    @Test
    public void lowerBoundary() {
        Boss downBound = new Boss(100,9, BossFightGame.height + 1);
        downBound.handleVertBoundary();
        assertEquals(BossFightGame.height - boss.height, downBound.getY());
    }

}
