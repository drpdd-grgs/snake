package ru.kaifkaby.snake.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final MainFrame mainFrame;

    public GamePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
    }
}
