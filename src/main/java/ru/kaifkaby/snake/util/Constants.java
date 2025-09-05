package ru.kaifkaby.snake.util;

public class Constants {

    public static final String WELCOME_TEXT = "PRESS ENTER";
    public static final int WELCOME_TEXT_BLINK_DURATION = 500;
    public static final int WELCOME_TEXT_X = 470;
    public static final int WELCOME_TEXT_Y = 368;

    public static final String SCORE_TEXT = "SCORE: %s";
    public static final int SCORE_TEXT_WIDTH_OFFSET = 700;
    public static final int SCORE_TEXT_HEIGHT_OFFSET = 200;

    public static final String GAME_OVER_TEXT = "GAME OVER. PRESS ENTER";
    public static final int GAME_OVER_TEXT_WIDTH_OFFSET = 700;
    public static final int GAME_OVER_TEXT_HEIGHT_OFFSET = 300;

    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;

    public static final int SNAKE_START_X = 25;
    public static final int SNAKE_START_Y = 25;

    public static final int FIELD_WIDTH = 50;
    public static final int FIELD_HEIGHT = 50;

    public static final int TICK_RATE = 100;

    public static final int ITEM_SIZE = 10;

    public static final int GAME_WIDTH_OFFSET = 80;
    public static final int GAME_HEIGHT_OFFSET = 80;

    public static final int UI_FIELD_X = 81;
    public static final int UI_FIELD_Y = 81;
    public static final int UI_FIELD_WIDTH = 510;
    public static final int UI_FIELD_HEIGHT = 510;

    private Constants() {
        throw new RuntimeException("Can't be initialized");
    }
}
