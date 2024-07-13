package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.pianotutorial.constants.GlobalVariables;

public class StaffDrawer {
    private final Paint staffPaint;

    public StaffDrawer(Paint staffPaint) {
        this.staffPaint = staffPaint;
    }

    public void draw(Canvas canvas, int width) {
        float staffHeight = (float) GlobalVariables.FIXED_HEIGHT;
        float lineSpacing = staffHeight / 8;

        for (int i = 0; i < 5; i++) {
            float y = staffHeight / 2 + i * lineSpacing;
            canvas.drawLine(0, y, width, y, staffPaint);
        }
    }
}

