package com.example.pianotutorial.features.components.effects;

import android.graphics.Color;
import android.graphics.Paint;

public class PulseEffect {
    private static final long PULSE_DURATION = 1000; // 1 second pulse duration
    private static final int PULSE_REPEAT_COUNT = 2; // Number of pulse repeats

    private long startTime;
    private int originalColor;
    private Paint paint;

    public PulseEffect(Paint paint) {
        this.paint = paint;
        this.originalColor = paint.getColor();
    }

    public void startPulse() {
        startTime = System.currentTimeMillis();
    }

    public void update() {
        if (startTime == 0) return;

        long elapsed = System.currentTimeMillis() - startTime;
        float progress = (float) elapsed / PULSE_DURATION;

        if (progress > PULSE_REPEAT_COUNT) {
            progress = 1; // End of pulse effect
            startTime = 0; // Reset pulse effect
        }

        // Animate color or other properties
        int pulseColor = interpolateColor(originalColor, Color.RED, Math.min(progress, 1));
        paint.setColor(pulseColor);
    }

    private int interpolateColor(int startColor, int endColor, float ratio) {
        int startA = Color.alpha(startColor);
        int startR = Color.red(startColor);
        int startG = Color.green(startColor);
        int startB = Color.blue(startColor);

        int endA = Color.alpha(endColor);
        int endR = Color.red(endColor);
        int endG = Color.green(endColor);
        int endB = Color.blue(endColor);

        int a = (int) (startA + (endA - startA) * ratio);
        int r = (int) (startR + (endR - startR) * ratio);
        int g = (int) (startG + (endG - startG) * ratio);
        int b = (int) (startB + (endB - startB) * ratio);

        return Color.argb(a, r, g, b);
    }
}

