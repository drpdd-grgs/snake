package ru.kaifkaby.snake.view;

import ru.kaifkaby.snake.game.*;
import ru.kaifkaby.snake.game.Point;
import ru.kaifkaby.snake.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    private final Game game;

    public GamePanel(MainFrame mainFrame) {
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        game = new Game();
        addKeyListener(new GameKeyListener(game, mainFrame));
        initGameTimer();
    }

    private void initGameTimer() {
        Timer timer = new Timer(Constants.TICK_RATE, _ -> {
            if (!game.isGameOver()) {
                game.step();
            }
            repaint();
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScore(g);
        drawField(g);
        drawSnake(g);
        drawFruit(g);
        if (game.isGameOver()) {
            drawGameOver(g);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString(String.format(Constants.SCORE_TEXT, game.getScore()),
                Constants.SCORE_TEXT_WIDTH_OFFSET,
                Constants.SCORE_TEXT_HEIGHT_OFFSET);
    }

    private void drawField(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(
                Constants.UI_FIELD_X,
                Constants.UI_FIELD_Y,
                Constants.UI_FIELD_WIDTH,
                Constants.UI_FIELD_HEIGHT);
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.WHITE);
        Snake snake = game.getSnake();
        SnakeBody head = snake.getHead();
        Point point;
        for (SnakeBody snakeBody : snake) {
            point = snakeBody.getPoint();
            if (snakeBody.equals(head)) {
                g.setColor(Color.GREEN);
            }
            g.fillRect(
                    Constants.GAME_WIDTH_OFFSET + point.x() * Constants.ITEM_SIZE,
                    Constants.GAME_HEIGHT_OFFSET + point.y() * Constants.ITEM_SIZE,
                    Constants.ITEM_SIZE,
                    Constants.ITEM_SIZE);
        }
    }

    private void drawFruit(Graphics g) {
        g.setColor(Color.RED);
        Point point = game.getFruit().point();
        g.fillRect(
                Constants.GAME_WIDTH_OFFSET + point.x() * Constants.ITEM_SIZE,
                Constants.GAME_HEIGHT_OFFSET + point.y() * Constants.ITEM_SIZE,
                Constants.ITEM_SIZE,
                Constants.ITEM_SIZE);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.drawString(Constants.GAME_OVER_TEXT,
                Constants.GAME_OVER_TEXT_WIDTH_OFFSET,
                Constants.GAME_OVER_TEXT_HEIGHT_OFFSET);
    }

    record GameKeyListener(Game game, MainFrame mainFrame) implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (game.isGameOver() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                mainFrame.getMainPanel().removeAll();
                GamePanel gamePanel = new GamePanel(mainFrame);
                mainFrame.getMainPanel().add(gamePanel);
                gamePanel.requestFocusInWindow();
                mainFrame.setVisible(true);
            } else {
                SnakeBody.Direction direction = switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> SnakeBody.Direction.LEFT;
                    case KeyEvent.VK_RIGHT -> SnakeBody.Direction.RIGHT;
                    case KeyEvent.VK_UP -> SnakeBody.Direction.UP;
                    case KeyEvent.VK_DOWN -> SnakeBody.Direction.DOWN;
                    default -> null;
                };
                if (direction != null) {
                    game.setSnakeDirection(direction);
                }
            }
        }
    }
}
