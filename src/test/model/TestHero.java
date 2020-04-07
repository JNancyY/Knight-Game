package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHero {
    private static final int hp = 100;
    private static final int locX = 4;
    private static final int locY = 4;
    private Hero hero;

    @BeforeEach
    public void runBefore() {
        hero = new Hero(hp, locX, locY);
    }

    @Test
    public void testGetX() {
        assertEquals(locX, hero.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(locY, hero.getY());
    }

    @Test
    public void testGetHealth() {
        assertEquals(100, hero.getHealth());
    }

    @Test
    public void testDealDamage() {
        assertEquals(100, hero.getHealth());

        hero.takeDamage(20);

        assertEquals(80, hero.getHealth());
    }

    @Test
    public void rightBoundary() {
        Hero rightBound = new Hero(hp, BossFightGame.width + 1, 9);
        rightBound.handleHorzBoundary();
        assertEquals(BossFightGame.width / 2 - hero.width, rightBound.getX());
    }

    @Test
    public void leftBoundary() {
        Hero leftBound = new Hero(hp, -BossFightGame.width - 1, 9);
        leftBound.handleHorzBoundary();
        assertEquals(0, leftBound.getX());
    }

    @Test
    public void upperBoundary() {
        Hero upBound = new Hero(hp, 9, -BossFightGame.height - 1);
        upBound.handleVertBoundary();
        assertEquals(50, upBound.getY());
    }

    @Test
    public void lowerBoundary() {
        Hero downBound = new Hero(hp, 9, BossFightGame.height + 1);
        downBound.handleVertBoundary();
        assertEquals(BossFightGame.height - hero.height, downBound.getY());
    }

}
