package ru.kaifkaby.snake.view;

import ru.kaifkaby.snake.game.Difficulty;
import ru.kaifkaby.snake.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartPanel extends JPanel {

    private final Difficulty[] difficulties;

    private int currentDifficulty;

    private Color welcomeTextColor = Color.WHITE;

    private final MainFrame mainFrame;

    private final Timer timer;

    public StartPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        timer = new Timer(Constants.WELCOME_TEXT_BLINK_DURATION, _ -> switchWelcomeTextColor());
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        initStartTextTimer();
        addKeyListener(new StartPanelKeyListener(this));
        difficulties = Difficulty.values();
        currentDifficulty = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(welcomeTextColor);
        g.drawString(Constants.WELCOME_TEXT, Constants.WELCOME_TEXT_X, Constants.WELCOME_TEXT_Y);
        g.setColor(Color.WHITE);
        g.drawString(String.format(Constants.DIFFICULTY_TEXT, difficulties[currentDifficulty].name()), Constants.DIFFICULTY_TEXT_X, Constants.DIFFICULTY_TEXT_Y);
    }

    private void initStartTextTimer() {
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    private void switchWelcomeTextColor() {
        welcomeTextColor = Color.WHITE.equals(welcomeTextColor) ? Color.BLACK : Color.WHITE;
        repaint();
    }

    record StartPanelKeyListener(StartPanel startPanel) implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                loadGame();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                startPanel.currentDifficulty--;
                if (startPanel.currentDifficulty == -1) {
                    startPanel.currentDifficulty = 3;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                startPanel.currentDifficulty++;
                if (startPanel.currentDifficulty == 4) {
                    startPanel.currentDifficulty = 0;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        private void loadGame() {
            MainFrame mainFrame = startPanel.mainFrame;
            startPanel.timer.stop();

            mainFrame.getMainPanel().removeAll();
            GamePanel gamePanel = new GamePanel(mainFrame, startPanel.difficulties[startPanel.currentDifficulty]);
            mainFrame.getMainPanel().add(gamePanel);
            gamePanel.requestFocusInWindow();
            mainFrame.setVisible(true);
        }
    }
}
