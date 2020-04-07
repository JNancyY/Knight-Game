package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    private BossFightGame bfg;
    Character hero;
    Character boss;
    HealthBar hpBar;

    @BeforeEach
    public void runBefore() {
        bfg = new BossFightGame();
        hero = bfg.getHero();
        boss = bfg.getBoss();
        hpBar = hero.getHpBar();
    }

    @Test
    public void testInit() {
        assertEquals(BossFightGame.width / 4, hero.getX());
        assertEquals((BossFightGame.height / 3) + 100, hero.getY());
        assertEquals(100, hero.getHealth());

        assertEquals((3 * BossFightGame.width) / 4, boss.getX());
        assertEquals(BossFightGame.height / 3, boss.getY());
        assertEquals(100, boss.getHealth());

        List<FireBall> f = bfg.getFireBalls();
        assertEquals(0, f.size());

        List<Arrow> a = bfg.getArrows();
        assertEquals(0, a.size());

        assertFalse(bfg.isWon());
        assertFalse(bfg.isOver());
        assertEquals(0, bfg.getScore());
    }

    @Test
    public void testHeroControl() {
        bfg.keyPressed(KeyEvent.VK_LEFT);
        bfg.update();
        assertEquals(-4, hero.dx);

        bfg.keyReleased(KeyEvent.VK_LEFT);
        bfg.update();
        assertEquals(0, hero.dx);

        bfg.keyPressed(KeyEvent.VK_RIGHT);
        bfg.update();
        assertEquals(4, hero.dx);

        bfg.keyReleased(KeyEvent.VK_RIGHT);
        bfg.update();
        assertEquals(0, hero.dx);

        bfg.keyPressed(KeyEvent.VK_UP);
        bfg.update();
        assertEquals(-4, hero.dy);

        bfg.keyReleased(KeyEvent.VK_UP);
        bfg.update();
        assertEquals(0, hero.dy);

        bfg.keyPressed(KeyEvent.VK_DOWN);
        bfg.update();
        assertEquals(4, hero.dy);

        bfg.keyReleased(KeyEvent.VK_DOWN);
        bfg.update();
        assertEquals(0, hero.dy);

    }

    @Test
    public void testMoveBoss() {
        boss.setY(100);
        hero.setY(5);

        bfg.update();

        assertEquals(-2, boss.dy);

        boss.setY(50);
        hero.setY(150);

        bfg.update();

        assertEquals(0, boss.dy);

        boss.setY(50);
        hero.setY(200);

        bfg.update();

        assertEquals(2, boss.dy);
    }

    @Test
    public void testFireArrows() {
        List<Arrow> a = bfg.getArrows();
        assertEquals(0, a.size());

        bfg.keyPressed(KeyEvent.VK_SPACE);
        bfg.update();
        assertEquals(1, a.size());
        bfg.keyPressed(KeyEvent.VK_SPACE);
        bfg.update();
        assertEquals(2, a.size());
        bfg.keyPressed(KeyEvent.VK_SPACE);
        bfg.update();
        assertEquals(2, a.size());
    }

    @Test
    public void testKeyPressed() {
        bfg.keyPressed(KeyEvent.VK_R);
        bfg.update();
        testInit();
    }

    @Test
    public void testBossAttack() {
        List<FireBall> f = bfg.getFireBalls();
        assertEquals(0, f.size());

        bfg.update();

        assertEquals(0, f.size());
    }

    @Test
    public void testHitBoss() {
        List<Arrow> arrows = bfg.getArrows();
        arrows.add(new Arrow(boss.getX(), boss.getY()));

        bfg.update();

        assertEquals(boss.getMaxHealth() - 10, boss.getHealth());

    }

    @Test
    public void testHitHero() {
        List<FireBall> fireBalls = bfg.getFireBalls();
        fireBalls.add(new FireBall(hero.getX(), hero.getY()));

        bfg.update();

        assertEquals(hero.getMaxHealth() - 20, hero.getHealth());

    }

    @Test
    public void testChangeColour() {
        assertEquals("green", hpBar.getBarColourString());

        hero.takeDamage(100 / 2);
        bfg.update();
        assertEquals("yellow", hpBar.getBarColourString());

        hero.takeDamage(100 / 4);
        bfg.update();
        assertEquals("red", hpBar.getBarColourString());
    }

    @Test
   public void testGameOver() {
        bfg.update();

        assertFalse(bfg.isOver());

        hero.takeDamage(500);

        bfg.update();

        assertTrue(bfg.isOver());
    }

    @Test
    public void testGameWon() {
        bfg.update();

        assertFalse(bfg.isWon());

        boss.takeDamage(500);

        bfg.update();

        assertTrue(bfg.isWon());
    }
}
