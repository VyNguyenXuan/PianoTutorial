package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.Paint;

public class StaffDrawer {
    private static final int FIXED_HEIGHT = 400;
    private Paint staffPaint;

    public StaffDrawer(Paint staffPaint) {
        this.staffPaint = staffPaint;
    }

    public void draw(Canvas canvas, int width, int height) {
        float staffHeight = (float) FIXED_HEIGHT;
        float lineSpacing = staffHeight / 8;

        for (int i = 0; i < 5; i++) {
            float y = staffHeight / 2 + i * lineSpacing;
            canvas.drawLine(0, y, width, y, staffPaint);
        }
    }
}

