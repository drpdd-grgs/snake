package ru.kaifkaby.snake.game;

import ru.kaifkaby.snake.util.Constants;

import java.util.Random;

public class Game {

    private final Snake snake;

    private final Random random;

    private Fruit fruit;

    private boolean gameOver;

    private int score;

    public Game() {
        random = new Random();
        SnakeBody.Direction[] directions = SnakeBody.Direction.values();
        snake = new Snake(new Point(Constants.SNAKE_START_X, Constants.SNAKE_START_Y), directions[random.nextInt(directions.length)]);
        fruit = makeFruit();
        gameOver = false;
        score = 0;
    }

    public void step() {
        if (checkCollisions()) {
            gameOver = true;
            return;
        }
        Point fruitPoint = fruit.point();
        SnakeBody.Direction direction = snake.getHead().getDirection();
        if (direction.getMoveFunction().apply(snake.getHead().getPoint()).equals(fruitPoint)) {
            score++;
            snake.grow(fruitPoint);
            fruit = makeFruit();
        } else {
            snake.move();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    public void setSnakeDirection(SnakeBody.Direction direction) {
        snake.setDirection(direction);
    }

    public Snake getSnake() {
        return snake;
    }

    public Fruit getFruit() {
        return fruit;
    }

    private boolean checkCollisions() {
        Point headPoint = snake.getHead().getPoint();
        return headPoint.x() < 0
                || headPoint.x() > Constants.FIELD_WIDTH
                || headPoint.y() < 0
                || headPoint.y() > Constants.FIELD_HEIGHT
                || snake.containsExceptHead(headPoint);
    }

    private Fruit makeFruit() {
        Point point;
        do {
            point = new Point(random.nextInt(Constants.FIELD_WIDTH), random.nextInt(Constants.FIELD_HEIGHT));
        } while (snake.contains(point));
        return new Fruit(point);
    }
}
