package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class BlackKeyPaint {
    public static Paint create() {
        Paint buttonPaint = new Paint();
        buttonPaint.setColor(0xFF000000); // Black color
        buttonPaint.setStyle(Paint.Style.FILL);

        return buttonPaint;
    }

    public static Path createPath() {
        Path buttonPath = new Path();
        buttonPath.moveTo(0f, 0f);
        buttonPath.lineTo(72f, 0f);
        buttonPath.lineTo(72f, 148f);
        buttonPath.cubicTo(72f, 154.63f, 66.63f, 160f, 60f, 160f);
        buttonPath.lineTo(12f, 160f);
        buttonPath.cubicTo(5.37f, 160f, 0f, 154.63f, 0f, 148f);
        buttonPath.lineTo(0f, 0f);
        buttonPath.close();

        return buttonPath;
    }
}
