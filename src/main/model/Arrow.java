package model;

// Part of the following code may be referenced from:
// SpaceInvaders Lecture Lab
// http://zetcode.com/tutorials/javagamestutorial/movingsprites/

public class Arrow extends Sprite {
    private static final int arrowSpeed = 5;

    public Arrow(int x, int y) {
        super(x, y);

        loadSprite("./data/Arrow Sprite.png");
        getImageDimensions();
    }

    // MODIFIES: this
    // EFFECTS: move arrow arrow speed pixels to the right
    public void move() {
        posX += arrowSpeed;
    }

}
