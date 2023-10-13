package game;

import enums.Color;

public class Player {
    private final Color color;

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}