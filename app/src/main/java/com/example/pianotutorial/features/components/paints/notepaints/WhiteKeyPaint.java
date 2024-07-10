package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class WhiteKeyPaint {
    public static Paint create() {
        Paint buttonPaint = new Paint();
        buttonPaint.setColor(0xFFFFFFFF); // White color
        buttonPaint.setStyle(Paint.Style.FILL);
        buttonPaint.setStrokeWidth(2);
        buttonPaint.setStyle(Paint.Style.STROKE); // Set to STROKE to apply the stroke
        return buttonPaint;
    }

    public static Path createPath() {
        Path buttonPath = new Path();
        buttonPath.moveTo(1f, 1f);
        buttonPath.lineTo(99f, 1f);
        buttonPath.lineTo(99f, 244f);
        buttonPath.cubicTo(99f, 252.28f, 92.28f, 259f, 84f, 259f);
        buttonPath.lineTo(16f, 259f);
        buttonPath.cubicTo(7.72f, 259f, 1f, 252.28f, 1f, 244f);
        buttonPath.lineTo(1f, 1f);
        buttonPath.close();

        return buttonPath;
    }
}
