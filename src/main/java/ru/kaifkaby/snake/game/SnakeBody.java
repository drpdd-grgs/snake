package ru.kaifkaby.snake.game;

import java.util.function.Function;

public class SnakeBody {

    private Point point;
    private SnakeBody next;
    private Direction direction;

    public SnakeBody(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public void move() {
        point = direction.moveFunction.apply(point);
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public SnakeBody getNext() {
        return next;
    }

    public void setNext(SnakeBody next) {
        this.next = next;
    }

    public enum Direction {

        UP(point -> new Point(point.x(), point.y() - 1)),
        DOWN(point -> new Point(point.x(), point.y() + 1)),
        RIGHT(point -> new Point(point.x() + 1, point.y())),
        LEFT(point -> new Point(point.x() - 1, point.y()));

        private final Function<Point, Point> moveFunction;

        Direction(Function<Point, Point> moveFunction) {
            this.moveFunction = moveFunction;
        }

        public Function<Point, Point> getMoveFunction() {
            return moveFunction;
        }

        public boolean isOpposite(Direction direction) {
            Direction opposite = switch (this) {
                case Direction.UP -> Direction.DOWN;
                case Direction.DOWN -> Direction.UP;
                case Direction.LEFT -> Direction.RIGHT;
                case Direction.RIGHT -> Direction.LEFT;
            };
            return opposite.equals(direction);
        }
    }
}
