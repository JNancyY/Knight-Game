package model;

import javax.swing.*;
import java.awt.*;

// Part of the following code may be referenced from:
// SpaceInvaders Lecture Lab
// http://zetcode.com/tutorials/javagamestutorial/movingsprites/

public abstract class Sprite {
    protected int posX;
    protected int posY;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    public Sprite(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    // MODIFIES: this
    // EFFECTS: loads png image, sets png as this.image
    protected void loadSprite(String img) {
        ImageIcon ii = new ImageIcon(img);
        image = ii.getImage();
    }

    // MODIFIES: this
    // EFFECTS: gets width and height of image
    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    // EFFECTS: returns image
    public Image getImage() {
        return image;
    }

    // EFFECTS: returns posX
    public int getX() {
        return posX;
    }

    // EFFECTS: returns posY
    public int getY() {
        return posY;
    }

    // MODIFIES: this
    // EFFECTS: sets posY as given y
    public void setY(int y) {
        posY = y;
    }

    // EFFECTS: returns the middle y position of the image
    public int getMidY() {
        int midY = ((height + posY) / 2);

        return midY;
    }

    // EFFECTS: returns the rectangular bounds of the image
    public Rectangle getBounds() {
        return new Rectangle(posX, posY, width, height);
    }
}
