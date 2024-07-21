package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class QuarterNotePaintFlip {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        notePath.moveTo(55.32f, 97.39f);
        notePath.cubicTo(51.8f, 92.15f, 55.58f, 84.61f, 63.64f, 80.57f);
        notePath.cubicTo(71.7f, 76.53f, 81.15f, 77.3f, 84.68f, 82.54f);
        notePath.cubicTo(88.2f, 87.78f, 84.42f, 95.32f, 76.36f, 99.47f);
        notePath.cubicTo(68.3f, 103.51f, 58.85f, 102.64f, 55.32f, 97.39f);
        notePath.close();

        return notePath;
    }
}
