package ru.kaifkaby.snake.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel mainPanel;

    public MainFrame() {
        init();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void init() {
        mainPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Snake");
        setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, ViewConstants.WINDOW_HEIGHT));
        setResizable(false);

        mainPanel.setMinimumSize(new Dimension(ViewConstants.WINDOW_WIDTH, ViewConstants.WINDOW_HEIGHT));
        mainPanel.setSize(new Dimension(ViewConstants.WINDOW_WIDTH, ViewConstants.WINDOW_HEIGHT));
        mainPanel.setLayout(new GridLayout(1, 0));

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        pack();
        setLocationRelativeTo(null);
    }
}
