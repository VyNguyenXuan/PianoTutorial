package com.example.pianotutorial.features.components.paints.models;

public class Note {
    private float initialX;
    private float y;
    private long time;
    private float speed;

    public Note(float initialX, float y, long time, float speed) {
        this.initialX = initialX;
        this.y = y;
        this.time = time;
        this.speed = speed;
    }

    public float getInitialX() {
        return initialX;
    }

    public float getY() {
        return y;
    }

    public long getTime() {
        return time;
    }

    public float getSpeed() {
        return speed;
    }
}

