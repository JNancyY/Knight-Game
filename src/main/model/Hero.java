package model;

// Part of the following code may be referenced from:
// SpaceInvaders Lecture Lab
// http://zetcode.com/tutorials/javagamestutorial/movingsprites/

public class Hero extends Character {
    public static String userName;

    // EFFECTS: places Hero at position (posX, posY) with initial health hp
    public Hero(int hp, int x, int y) {
        super(hp, x, y);


        loadSprite("./data/Hero Sprite.png");
        getImageDimensions();
    }

    // MODIFIES: this
    // EFFECTS: Hero is constrained to remain within the left half of the game screen
    @Override
    public void handleHorzBoundary() {
        if (posX < 0) {
            posX = 0;
        } else if (posX + width > BossFightGame.width / 2) {
            posX = BossFightGame.width / 2 - width;
        }
    }

    // MODIFIES: this
    // EFFECTS: Hero is constrained to remain within the vertical bounds of the game screen
    @Override
    public void handleVertBoundary() {
        if (posY - hpSpace < 0) {
            posY = 0 + hpSpace;
        } else if (posY + height > BossFightGame.height) {
            posY = BossFightGame.height - height;
        }
    }

}
