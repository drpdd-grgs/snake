package ru.kaifkaby.snake.view;

import ru.kaifkaby.snake.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartPanel extends JPanel {

    private Color welcomeTextColor = Color.WHITE;

    public StartPanel(MainFrame mainFrame) {
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        addKeyListener(new StartPanelKeyListener(mainFrame, initStartTextTimer()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(welcomeTextColor);
        g.drawString(Constants.WELCOME_TEXT, Constants.WELCOME_TEXT_X, Constants.WELCOME_TEXT_Y);
    }

    private Timer initStartTextTimer() {
        Timer timer = new Timer(Constants.WELCOME_TEXT_BLINK_DURATION, _ -> switchWelcomeTextColor());
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
        return timer;
    }

    private void switchWelcomeTextColor() {
        welcomeTextColor = Color.WHITE.equals(welcomeTextColor) ? Color.BLACK : Color.WHITE;
        repaint();
    }

    record StartPanelKeyListener(MainFrame mainFrame, Timer timer) implements KeyListener {

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
            timer.stop();
            mainFrame.getMainPanel().removeAll();
            GamePanel gamePanel = new GamePanel(mainFrame);
            mainFrame.getMainPanel().add(gamePanel);
            gamePanel.requestFocusInWindow();
            mainFrame.setVisible(true);
        }
    }
}
