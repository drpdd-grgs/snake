package ru.kaifkaby.snake.view;

import ru.kaifkaby.snake.game.*;
import ru.kaifkaby.snake.game.Point;
import ru.kaifkaby.snake.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class GamePanel extends JPanel {

    private final Game game;

    private final Difficulty difficulty;

    private final MainFrame mainFrame;

    private final ConcurrentLinkedQueue<Consumer<Game>> inputQueue;

    private final Timer gameTimer;

    private final Timer graphicsTimer;

    public GamePanel(MainFrame mainFrame, Difficulty difficulty) {
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        this.game = new Game();
        this.mainFrame = mainFrame;
        this.difficulty = difficulty;
        this.inputQueue = new ConcurrentLinkedQueue<>();
        this.gameTimer = new Timer(difficulty.getTickrate(), getGameActionListener());
        this.graphicsTimer = new Timer(1, getGraphicsActionListener());
        addKeyListener(new GameKeyListener(this));
        initGameTimer();
        initGraphicsTimer();
    }

    private void initGameTimer() {
        gameTimer.setRepeats(true);
        gameTimer.setCoalesce(true);
        gameTimer.start();
    }

    private void initGraphicsTimer() {
        graphicsTimer.setRepeats(true);
        graphicsTimer.setCoalesce(true);
        graphicsTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScore(g);
        drawDifficulty(g);
        drawField(g);
        drawSnake(g);
        drawFruit(g);
        if (game.isGameOver()) {
            drawGameOver(g);
        }
    }

    public Game getGame() {
        return game;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDirection(SnakeBody.Direction direction) {
        inputQueue.add(game -> game.setSnakeDirection(direction));
    }

    private ActionListener getGameActionListener() {
        return _ -> {
            Consumer<Game> input;
            if (!game.isGameOver()) {
                input = inputQueue.poll();
                if (input != null) {
                    input.accept(game);
                }
                game.step();
            }
        };
    }

    private ActionListener getGraphicsActionListener() {
        return _ -> repaint();
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);


        g.drawString(String.format(Constants.SCORE_TEXT, game.getScore()),
                Constants.SCORE_TEXT_X,
                Constants.SCORE_TEXT_Y);
    }

    private void drawDifficulty(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString(String.format(Constants.DIFFICULTY_GAME_TEXT, difficulty),
                Constants.DIFFICULTY_GAME_TEXT_X,
                Constants.DIFFICULTY_GAME_TEXT_Y);
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
                    getProjectX(point.x()),
                    getProjectY(point.y()),
                    Constants.ITEM_SIZE,
                    Constants.ITEM_SIZE);
            if (snakeBody.equals(head)) {
                g.setColor(Color.RED);
                g.fillOval(
                        getProjectX(point.x()),
                        getProjectY(point.y()),
                        Constants.SNAKE_EYES_SIZE,
                        Constants.SNAKE_EYES_SIZE);
                g.fillOval(
                        getProjectX(point.x()) + Constants.SNAKE_EYES_DISTANCE,
                        getProjectY(point.y()),
                        Constants.SNAKE_EYES_SIZE,
                        Constants.SNAKE_EYES_SIZE);
            }
        }
    }

    private void drawFruit(Graphics g) {
        g.setColor(Color.RED);
        Point point = game.getFruit().point();
        g.fillOval(Constants.GAME_WIDTH_OFFSET + point.x() * Constants.ITEM_SIZE,
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

    private int getProjectX(int x) {
        return Constants.GAME_WIDTH_OFFSET + x * Constants.ITEM_SIZE;
    }

    private int getProjectY(int y) {
        return Constants.GAME_WIDTH_OFFSET + y * Constants.ITEM_SIZE;
    }

    record GameKeyListener(GamePanel gamePanel) implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            MainFrame mainFrame = gamePanel.getMainFrame();
            if (gamePanel.getGame().isGameOver() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                mainFrame.getMainPanel().removeAll();
                GamePanel newGamePanel = new GamePanel(mainFrame, gamePanel.getDifficulty());
                mainFrame.getMainPanel().add(newGamePanel);
                newGamePanel.requestFocusInWindow();
                mainFrame.setVisible(true);
                mainFrame.requestFocusInWindow();
            } else {
                SnakeBody.Direction direction = switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> SnakeBody.Direction.LEFT;
                    case KeyEvent.VK_RIGHT -> SnakeBody.Direction.RIGHT;
                    case KeyEvent.VK_UP -> SnakeBody.Direction.UP;
                    case KeyEvent.VK_DOWN -> SnakeBody.Direction.DOWN;
                    default -> null;
                };
                if (direction != null) {
                    gamePanel.setDirection(direction);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
