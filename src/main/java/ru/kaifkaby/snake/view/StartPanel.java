package ru.kaifkaby.snake.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartPanel extends JPanel {

    private static final String welcomeText = "PRESS ENTER";
    private static final int welcomeTextBlinkDuration = 500;

    private Color welcomeTextColor = Color.WHITE;

    public StartPanel(MainFrame mainFrame) {
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        initStartTextTimer();
        addKeyListener(new StartPanelKeyListener(mainFrame));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(welcomeTextColor);
        g.drawString(welcomeText,
                ViewConstants.WINDOW_WIDTH / 2 - g.getFontMetrics().stringWidth(welcomeText) / 2,
                ViewConstants.WINDOW_HEIGHT / 2 - g.getFontMetrics().getHeight());
    }

    private void initStartTextTimer() {
        Timer timer = new Timer(welcomeTextBlinkDuration, _ -> switchWelcomeTextColor());
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    private void switchWelcomeTextColor() {
        welcomeTextColor = welcomeTextColor == Color.WHITE ? Color.BLACK : Color.WHITE;
        repaint();
    }

    static class StartPanelKeyListener implements KeyListener {

        private final MainFrame mainFrame;

        private StartPanelKeyListener(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                loadGame();
            }
        }

        private void loadGame() {
            mainFrame.getMainPanel().removeAll();
            GamePanel gamePanel = new GamePanel(mainFrame);
            mainFrame.getMainPanel().add(gamePanel);
            gamePanel.requestFocusInWindow();
            mainFrame.setVisible(true);
        }
    }
}
