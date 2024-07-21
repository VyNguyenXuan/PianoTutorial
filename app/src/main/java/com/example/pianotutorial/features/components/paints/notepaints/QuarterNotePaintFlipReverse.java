package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class QuarterNotePaintFlipReverse {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        notePath.moveTo(30.68f, 82.61f);
        notePath.cubicTo(34.2f, 87.85f, 30.42f, 95.39f, 22.36f, 99.43f);
        notePath.cubicTo(14.3f, 103.47f, 4.85f, 102.7f, 1.32f, 97.46f);
        notePath.cubicTo(-2.2f, 92.22f, 1.58f, 84.68f, 9.64f, 80.53f);
        notePath.cubicTo(17.7f, 76.49f, 27.15f, 77.36f, 30.68f, 82.61f);
        notePath.close();

        return notePath;
    }
}
