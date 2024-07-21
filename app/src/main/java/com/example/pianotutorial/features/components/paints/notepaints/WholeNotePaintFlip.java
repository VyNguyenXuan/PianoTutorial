package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class WholeNotePaintFlip {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        notePath.moveTo(56.1f, 100.51f);
        notePath.cubicTo(50.45f, 98.81f, 46.0f, 94.16f, 46.0f, 89.98f);
        notePath.cubicTo(46.0f, 78.16f, 71.81f, 73.48f, 82.47f, 83.37f);
        notePath.cubicTo(94.0f, 94.07f, 75.19f, 106.29f, 56.1f, 100.51f);
        notePath.close();

        notePath.moveTo(73.31f, 97.54f);
        notePath.cubicTo(76.46f, 92.83f, 73.45f, 83.49f, 68.01f, 81.05f);
        notePath.cubicTo(60.03f, 77.47f, 55.13f, 83.57f, 58.46f, 92.96f);
        notePath.cubicTo(60.76f, 99.45f, 70.12f, 102.34f, 73.31f, 97.54f);
        notePath.close();

        return notePath;
    }
}
