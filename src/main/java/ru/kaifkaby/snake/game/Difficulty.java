package ru.kaifkaby.snake.game;

public enum Difficulty {

    EASY(100),
    MEDIUM(50),
    HARD(25),
    IMPOSSIBLE(10);

    private final int tickrate;

    Difficulty(int tickrate) {
        this.tickrate = tickrate;
    }

    public int getTickrate() {
        return tickrate;
    }
}
