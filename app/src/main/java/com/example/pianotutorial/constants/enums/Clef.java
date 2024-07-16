package com.example.pianotutorial.constants.enums;

public enum Clef {
    GCLEF(0), FCLEF(1);
    private final int value;

    public int getValue() {
        return value;
    }

    Clef(int value) {
        this.value = value;
    }
}
