package ru.kaifkaby.snake.game;

import java.util.function.IntFunction;

public class SnakeBody {

    private Integer x;
    private Integer y;
    private SnakeBody next;
    private Direction direction;

    public SnakeBody(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void move() {
        x = direction.getXSetter().apply(x);
        y = direction.getYSetter().apply(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SnakeBody getNext() {
        return next;
    }

    public void setNext(SnakeBody next) {
        this.next = next;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public enum Direction {
        UP(x -> x, y -> y + 1),
        DOWN(x -> x, y -> y - 1),
        RIGHT(x -> x + 1, y -> y),
        LEFT(x -> x - 1, y -> y);

        private final IntFunction<Integer> xSetter;
        private final IntFunction<Integer> ySetter;

        Direction(IntFunction<Integer> xSetter, IntFunction<Integer> ySetter) {
            this.xSetter = xSetter;
            this.ySetter = ySetter;
        }

        public IntFunction<Integer> getXSetter() {
            return xSetter;
        }

        public IntFunction<Integer> getYSetter() {
            return ySetter;
        }
    }
}
