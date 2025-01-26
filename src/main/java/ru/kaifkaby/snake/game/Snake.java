package ru.kaifkaby.snake.game;

public class Snake {

    private SnakeBody head;

    public Snake(Integer x, Integer y, SnakeBody.Direction direction) {
        head = new SnakeBody(x, y, direction);
    }

    public void move() {
        head.move();
        SnakeBody next = head.getNext();
        SnakeBody.Direction prevDirection = head.getDirection();

        while (next != null) {
            next.move();
            next.setDirection(prevDirection);
            prevDirection = next.getDirection();
            next = next.getNext();
        }
    }
}
