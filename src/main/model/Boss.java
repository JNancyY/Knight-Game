package model;

// Part of the following code may be referenced from:
// SpaceInvaders Lecture Lab
// http://zetcode.com/tutorials/javagamestutorial/movingsprites/

public class Boss extends Character {
    private static final int hpSpace = 50;

    public Boss(int hp, int x, int y) {
        super(hp, x, y);

        loadSprite("./data/Skeleton Sprite.png");
        getImageDimensions();
    }


    // MODIFIES: this
    // EFFECTS: Boss is constrained to remain within the right half of the game screen
    @Override
    public void handleHorzBoundary() {
        if (posX < BossFightGame.width / 2) {
            posX = BossFightGame.width / 2;
        } else if (posX + width > BossFightGame.width) {
            posX = BossFightGame.width - width;
        }
    }

    // MODIFIES: this
    // EFFECTS: Boss is constrained to remain within the vertical bounds of the game screen
    @Override
    public void handleVertBoundary() {
        if (posY - hpSpace < 0) {
            posY = 0 + hpSpace;
        } else if (posY + height > BossFightGame.height) {
            posY = BossFightGame.height - height;
        }
    }

}
