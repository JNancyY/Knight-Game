package ui;

import model.BossFightGame;
import scores.HighScoreManager;

import javax.swing.*;
import java.awt.*;

// Part of the following code may be referenced from:
// SpaceInvaders Lecture Lab
// http://zetcode.com/tutorials/javagamestutorial/movingsprites/

public class BossFightFrame extends JFrame {
    private Dialogue preamble;
    private BossFightGame game;
    private GamePanel gp;
    private String testFile = "./data/gameTestFiles";

    public BossFightFrame() {
        super("Generic Boss Fight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        HighScoreManager hs = new HighScoreManager();
//        preamble = new Dialogue();
        game = new BossFightGame();
        gp = new GamePanel(game);
        add(gp);
        pack();
        centreOnScreen();
        setVisible(true);
//        hs.addScore(Hero.userName, 5, testFile);
//        hs.print();
    }

    // MODIFIES: this
    // EFFECTS: location of the frame is centered on the desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            BossFightFrame bff = new BossFightFrame();
            bff.setVisible(true);
        });
    }
}
