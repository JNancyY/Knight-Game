package model;

import model.exceptions.ColourException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Part of the following code may be referenced from:
// SpaceInvaders Lecture Lab
// http://zetcode.com/tutorials/javagamestutorial/movingsprites/

public class BossFightGame {
    public static final int width = 800;
    public static final int height = 800;
    private static final int maxArrows = 2;
    private static final int arrowDamage = 10;
    private static final int fireBallDamage = 20;
    private static final Random rnd = new Random();
    private static final int bossAttack = 50;
    private static final int heroSpeed = 4;
    private static final int bossSpeed = 2;

    private List<Arrow> arrows;
    private List<FireBall> fireBalls;
    private boolean isGameOver;
    private boolean isGameWon;

    private Hero hero;
    private Boss boss;

    private int scoreMultiplier = 1;
    private int score = 0;

    public BossFightGame() {
        arrows = new ArrayList<Arrow>();
        fireBalls = new ArrayList<FireBall>();
        setUp();
    }

    // MODIFIES: this
    // EFFECTS:  clears list of arrows and fireballs, initializes hero and boss
    public void setUp() {
        arrows.clear();
        fireBalls.clear();
        hero = new Hero(100, width / 4, (height / 3) + 100);
        boss = new Boss(100, (3 * width) / 4,  height / 3);
        isGameOver = false;
        isGameWon = false;
        score = 0;
        scoreMultiplier = 1;
    }

    // MODIFIES: this
    // EFFECTS:  updates tank, arrows and fireballs
    public void update() {
        bossAttack();
        moveBoss();
        moveArrows();
        moveFireBalls();
        hero.move();
        boss.move();

        checkProjectileBounds();
        checkCollisions();
        changeColour(hero);
        changeColour(boss);
        checkGameOver();
        checkGameWon();
    }

    //EFFECTS: returns true if the game is over
    public boolean isOver() {
        return isGameOver;
    }

    //EFFECTS: returns true if the game is won
    public boolean isWon() {
        return isGameWon;
    }

    // EFFECTS: returns fireballs
    public List<FireBall> getFireBalls() {
        return fireBalls;
    }

    // EFFECTS: returns arrow
    public List<Arrow> getArrows() {
        return arrows;
    }

    //EFFECTS: returns hero
    public Hero getHero() {
        return hero;
    }

    // EFFECTS: returns boss
    public Boss getBoss() {
        return boss;
    }

    // EFFECTS: returns score
    public int getScore() {
        return score;
    }

    // MODIFIES: this
    // EFFECTS:  fires an arrow if max number of arrows in play has
    //           not been exceeded, otherwise silently returns
    private void fireArrow() {
        if (arrows.size() < maxArrows) {
            Arrow a = new Arrow(hero.getX() + hero.getWidth(), hero.getY() + hero.getHeight() / 2);
            arrows.add(a);
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the arrows
    private void moveArrows() {
        for (Arrow next : arrows) {
            next.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes any arrows that have travelled out of bounds
    private void checkArrows() {
        List<Arrow> arrowsToRemove = new ArrayList<>();

        for (Arrow next : arrows) {
            if (next.getX() > width) {
                arrowsToRemove.add(next);
            }
        }

        arrows.removeAll(arrowsToRemove);
    }

    // MODIFIES: this
    // EFFECTS: boss fires a new fireball on average every 250 updates
    private void bossAttack() {
        if (rnd.nextInt(bossAttack) < 1) {
            FireBall f = new FireBall(boss.getX(), boss.getY() + boss.getHeight() / 3);
            fireBalls.add(f);
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the fireballs
    private void moveFireBalls() {
        for (FireBall next : fireBalls) {
            next.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes any fireball that have travelled out of bounds
    private void checkFireBalls() {
        List<FireBall> fireBallsToRemove = new ArrayList<>();

        for (FireBall next : fireBalls) {
            if (next.getX() < 0) {
                fireBallsToRemove.add(next);
            }
        }

        fireBalls.removeAll(fireBallsToRemove);
    }

    // EFFECTS: checks to see if projectiles are within bounds
    private void checkProjectileBounds() {
        checkArrows();
        checkFireBalls();
    }

    // EFFECTS: checks for collisions between models
    private void checkCollisions() {
        checkHitHero();
        checkHitBoss();
        checkCollidingProjectiles();
    }

    // MODIFIES: this, score, scoreMultiplier
    // EFFECTS: removes any arrows that collide with the boss, and adds to score and score multiplier
    private void checkHitBoss() {
        Rectangle bossBound = boss.getBounds();
        List<Arrow> arrowsToRemove = new ArrayList<>();

        for (Arrow arrow : arrows) {
            Rectangle arrowBound = arrow.getBounds();

            if (bossBound.intersects(arrowBound)) {
                arrowsToRemove.add(arrow);
                boss.takeDamage(arrowDamage);
                score += (10 * scoreMultiplier);
                scoreMultiplier++;
            }
        }

        arrows.removeAll(arrowsToRemove);
    }

    // MODIFIES: this
    // EFFECTS: removes any projectiles that collide with each other
    private void checkCollidingProjectiles() {
        List<Arrow> arrowsToRemove = new ArrayList<>();
        List<FireBall> fireBallsToRemove = new ArrayList<>();

        for (FireBall fireBall : fireBalls) {
            if (checkFireBallHit(fireBall, arrowsToRemove)) {
                fireBallsToRemove.add(fireBall);
            }
        }

        arrows.removeAll(arrowsToRemove);
        fireBalls.removeAll(fireBallsToRemove);
    }

    // EFFECTS: returns true if fireball has collided with an arrow, false otherwise
    private boolean checkFireBallHit(FireBall fireBall, List<Arrow> arrowsToRemove) {
        Rectangle targetBound = fireBall.getBounds();

        for (Arrow next : arrows) {
            Rectangle arrowBound = next.getBounds();

            if (targetBound.intersects(arrowBound)) {
                arrowsToRemove.add(next);
                return true;
            }
        }

        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes any fireballs that collide with the hero, resets score multiplier
    private void checkHitHero() {
        Rectangle heroBound = hero.getBounds();
        List<FireBall> fireBallsToRemove = new ArrayList<>();

        for (FireBall fireBall : fireBalls) {
            Rectangle fireBallBound = fireBall.getBounds();

            if (heroBound.intersects(fireBallBound)) {
                fireBallsToRemove.add(fireBall);
                hero.takeDamage(fireBallDamage);
                scoreMultiplier = 1;
            }
        }

        fireBalls.removeAll(fireBallsToRemove);
    }

    // MODIFIES: this
    // EFFECTS: fires arrows, resets game, closes game, and moves characters
    //          given key pressed code
    public void keyPressed(int key) {

        if (key == KeyEvent.VK_SPACE) {
            fireArrow();
        } else if (key == KeyEvent.VK_R && (isGameOver || isGameWon)) {
            setUp();
        } else if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else {
            heroControl(key);
        }

    }

    // MODIFIES: this
    // EFFECTS: moves hero given key pressed code
    //          boss moves to get close to hero
    private void heroControl(int key) {
        if (key == KeyEvent.VK_LEFT) {
            hero.dx = -heroSpeed;
        } else if (key == KeyEvent.VK_RIGHT) {
            hero.dx = heroSpeed;
        } else if (key == KeyEvent.VK_UP) {
            hero.dy = -heroSpeed;
        } else if (key == KeyEvent.VK_DOWN) {
            hero.dy = heroSpeed;
        }
    }

    // MODIFIES: this
    // EFFECTS: moves boss down if its position is above the hero
    //          moves boss up if its position is below the hero
    private void moveBoss() {
        if (boss.getMidY() < hero.getMidY()) {
            boss.dy = bossSpeed;
        } else if (boss.getMidY() > hero.getMidY()) {
            boss.dy = -bossSpeed;
        } else if (boss.getMidY() == hero.getMidY()) {
            boss.dy = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets hero's speed to 0, when no key is being pressed
    public void keyReleased(int key) {

        if (key == KeyEvent.VK_LEFT) {
            hero.dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            hero.dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            hero.dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            hero.dy = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes colour of health bar based on the
    //          amount of health the hero currently has
    private void changeColour(Character character) {
        HealthBar hpBar = character.getHpBar();

        try {
            if (character.getHealth() <= character.getMaxHealth() / 4) {
                hpBar.checkColour("red");
            } else if (character.getHealth() <= 2 * character.getMaxHealth() / 3) {
                hpBar.checkColour("yellow");
            } else if (character.getHealth() > character.getMaxHealth() / 2) {
                hpBar.checkColour("green");
            }
        } catch (ColourException e) {
            e.printStackTrace(); // for debugging
        }

    }

    // MODIFIES: this
    // EFFECTS:  if the hero's health is <= 0, the game is over
    //           list of arrows and fireballs is cleared
    private void checkGameOver() {
        if (hero.getHealth() <= 0) {
            isGameOver = true;
        }

        if (isGameOver) {
            arrows.clear();
            fireBalls.clear();
        }
    }

    // MODIFIES: this
    // EFFECTS:  if the Boss's health is <= 0, the game is won
    //           list of arrows and fireballs is cleared
    private void checkGameWon() {
        if (boss.getHealth() <= 0) {
            isGameWon = true;
        }

        if (isGameWon) {
            arrows.clear();
            fireBalls.clear();
        }
    }
}

