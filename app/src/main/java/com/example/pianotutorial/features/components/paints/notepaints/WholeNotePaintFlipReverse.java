package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class WholeNotePaintFlipReverse {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        notePath.moveTo(29.9f, 79.49f);
        notePath.cubicTo(35.55f, 81.19f, 40.0f, 85.84f, 40.0f, 90.02f);
        notePath.cubicTo(40.0f, 101.84f, 14.19f, 106.52f, 3.53f, 96.63f);
        notePath.cubicTo(-8.0f, 85.93f, 10.81f, 73.7f, 29.9f, 79.49f);
        notePath.close();

        notePath.moveTo(12.69f, 82.46f);
        notePath.cubicTo(9.54f, 87.17f, 12.55f, 96.51f, 17.99f, 98.95f);
        notePath.cubicTo(25.98f, 102.53f, 30.87f, 96.43f, 27.54f, 87.04f);
        notePath.cubicTo(25.24f, 80.55f, 15.88f, 77.66f, 12.69f, 82.46f);
        notePath.close();

        return notePath;
    }
}
