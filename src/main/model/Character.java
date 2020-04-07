package model;

// Part of the following code may be referenced from:
// SpaceInvaders Lecture Lab
// http://zetcode.com/tutorials/javagamestutorial/movingsprites/

public abstract class Character extends Sprite {
    protected static final int hpSpace = 50;
    protected static final int maxHealth = 100;
    protected int health;
    protected int dx;
    protected int dy;
    protected HealthBar hpBar;

    //EFFECTS: creates character with 100 initial health
    public Character(int hp, int x, int y) {
        super(x,y);
        this.health = hp;
        hpBar = new HealthBar(health);
    }

    public HealthBar getHpBar() {
        return hpBar;
    }

    // MODIFIES: this
    // EFFECTS: Subtracts damage from this
    public void takeDamage(int damage) {
        if (damage > 0) {
            health = health - damage;
        }
    }

    public void move() {
        posX += dx;
        posY += dy;

        handleHorzBoundary();

        handleVertBoundary();
    }

    // EFFECTS: returns health
    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // MODIFIES: this
    // EFFECTS: Character is constrained to remain within their half of the game screen
    abstract void handleHorzBoundary();

    // MODIFIES: this
    // EFFECTS: Character is constrained to remain within their half of the game screen
    abstract void handleVertBoundary();
}
