package model;

// Part of the following code may be referenced from:
// SpaceInvaders Lecture Lab
// http://zetcode.com/tutorials/javagamestutorial/movingsprites/

public class FireBall extends Sprite {
    public static final int fireBallSpeed = -2;

    public FireBall(int x, int y) {
        super(x, y);

        loadSprite("./data/FireBall Sprite.png");
        getImageDimensions();
    }

    // MODIFIES: this
    // EFFECTS: moves fireball fire ball speed pixels to the right
    public void move() {
        posX += fireBallSpeed;
    }

}
