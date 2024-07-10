package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.Paint;

public class LeftLineDrawer {
    private Paint leftLinePaint;

    public LeftLineDrawer() {
        leftLinePaint = new Paint();
        leftLinePaint.setColor(0xFF000000);
        leftLinePaint.setStrokeWidth(5);
    }

    public void draw(Canvas canvas, float xPosition, float topY, float bottomY) {
        canvas.drawLine(xPosition, topY, xPosition, bottomY, leftLinePaint);
    }
}
