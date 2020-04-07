package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Part of the following code may be referenced from:
// SpaceInvaders Lecture Lab
// http://zetcode.com/tutorials/javagamestutorial/movingsprites/

public class GamePanel extends JPanel implements ActionListener {
    private static final String over = "Game Over...";
    private static final String replay = "R to replay";
    private static final String congrats = "Congratulations! You have beaten the Boss!";
    private static final String score = "Score : ";
    private static final String exit = "Esc to Exit";
    private BossFightGame game;
    private Timer timer;
    private final int delay = 10;

    public GamePanel(BossFightGame g) {
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(BossFightGame.width, BossFightGame.height));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        this.game = g;

        timer = new Timer(delay, this);
        timer.start();
    }

    // MODIFIES: this
    // EFFECTS: draws each component of the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        Toolkit.getDefaultToolkit().sync();

        if (game.isOver()) {
            gameOver(g);
        }

        if (game.isWon()) {
            gameWon(g);
        }
    }

    // MODIFIES: this
    // EFFECTS: draws the game
    private void drawGame(Graphics g) {
        drawHero(g);
        drawBoss(g);
        drawArrows(g);
        drawFireBalls(g);

    }

    private void drawHero(Graphics g) {
        Hero h = game.getHero();

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(h.getImage(), h.getX(), h.getY(), this);

        drawHeroHp(g,h);
    }

    private void drawHeroHp(Graphics g, Hero h) {
        HealthBar hp = h.getHpBar();
        Color savedCol = g.getColor();
        g.setColor(hp.getColour());
        g.fillRect(h.getX(), h.getY() - 30, h.getHealth(), h.getHeight() / 5);
        g.setColor(savedCol);
    }

    private void drawBoss(Graphics g) {
        Boss b = game.getBoss();
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(b.getImage(), b.getX(), b.getY(), this);

        drawBossHp(g, b);
    }

    private void drawBossHp(Graphics g, Boss b) {
        HealthBar hp = b.getHpBar();
        Color savedCol = g.getColor();
        g.setColor(hp.getColour());
        g.fillRect(b.getX() + 50, b.getY() - 30, b.getHealth(), b.getHeight() / 10);
        g.setColor(savedCol);
    }


    private void drawArrow(Graphics g, Arrow a) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
    }

    private void drawArrows(Graphics g) {
        for (Arrow next : game.getArrows()) {
            drawArrow(g, next);
        }
    }

    private void drawFireBall(Graphics g, FireBall b) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(b.getImage(), b.getX(), b.getY(), this);
    }

    private void drawFireBalls(Graphics g) {
        for (FireBall next : game.getFireBalls()) {
            drawFireBall(g, next);
        }
    }

    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(over, g, fm, BossFightGame.height / 2);
        centreString(replay, g,fm,BossFightGame.height / 2 + 50);
        centreString(exit, g,fm,BossFightGame.height / 2 + 100);
        g.setColor(saved);
    }

    private void gameWon(Graphics g) {

        Color saved = g.getColor();
        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(congrats, g, fm, BossFightGame.height / 2);
        centreString(score + game.getScore(), g,fm,BossFightGame.height / 2 + 50);
        centreString(replay, g,fm,BossFightGame.height / 2 + 100);
        centreString(exit, g,fm,BossFightGame.height / 2 + 150);
        g.setColor(saved);

    }


    private void centreString(String str, Graphics g, FontMetrics fm, int posY) {
        int width = fm.stringWidth(str);
        g.drawString(str, (BossFightGame.width - width) / 2, posY);
    }

    // MODIFIES: this
    // EFFECTS: updates game at each interval of the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        // MODIFIES: this
        // EFFECTS: respond to key released events
        @Override
        public void keyReleased(KeyEvent e) {
            game.keyReleased(e.getKeyCode());
        }

        // MODIFIES: this
        // EFFECTS: respond to key pressed events
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }


}
