package ru.kaifkaby.snake.game;

import java.util.Iterator;

public class Snake implements Iterable<SnakeBody> {

    private final SnakeBody tail;

    private SnakeBody head;

    public Snake(Point point, SnakeBody.Direction direction) {
        head = new SnakeBody(point, direction);
        tail = head;
    }

    public void setDirection(SnakeBody.Direction direction) {
        if (head.equals(tail) || !head.getDirection().isOpposite(direction)) {
            head.setDirection(direction);
        }
    }

    public void grow(Point point) {
        SnakeBody newHead = new SnakeBody(point, head.getDirection());
        head.setNext(newHead);
        head = newHead;
    }

    public void move() {
        for (SnakeBody snakeBody : this) {
            snakeBody.move();
            if (!snakeBody.equals(head)) {
                snakeBody.setDirection(snakeBody.getNext().getDirection());
            }
        }
    }

    public boolean contains(Point point) {
        for (SnakeBody snakeBody : this) {
            if (point.equals(snakeBody.getPoint())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsExceptHead(Point point) {
        for (SnakeBody snakeBody : this) {
            if (!snakeBody.equals(head) && point.equals(snakeBody.getPoint())) {
                return true;
            }
        }
        return false;
    }

    public SnakeBody getHead() {
        return head;
    }

    @Override
    public Iterator<SnakeBody> iterator() {
        return new BodyBackwardsIterator(tail);
    }

    static class BodyBackwardsIterator implements Iterator<SnakeBody> {

        private SnakeBody next;

        BodyBackwardsIterator(SnakeBody tail) {
            next = tail;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public SnakeBody next() {
            SnakeBody cur = next;
            next = next.getNext();
            return cur;
        }
    }
}
