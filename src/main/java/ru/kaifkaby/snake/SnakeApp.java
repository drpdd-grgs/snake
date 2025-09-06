package ru.kaifkaby.snake;

import ru.kaifkaby.snake.view.MainFrame;
import ru.kaifkaby.snake.view.StartPanel;

import javax.swing.SwingUtilities;

public class SnakeApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            StartPanel startPanel = new StartPanel(mainFrame);
            mainFrame.getMainPanel().add(startPanel);
            mainFrame.setVisible(true);
        });
    }
}