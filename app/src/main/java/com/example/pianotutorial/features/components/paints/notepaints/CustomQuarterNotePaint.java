package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class CustomQuarterNotePaint {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath(float customY) {
        Path notePath = new Path();
        notePath.moveTo(27.32f, 97.39f);
        notePath.cubicTo(23.8f, 92.15f, 27.58f, 84.61f, 35.64f, 80.57f);
        notePath.cubicTo(43.7f, 76.53f, 53.15f, 77.3f, 56.68f, 82.54f);
        notePath.cubicTo(60.2f, 87.78f, 56.42f, 95.32f, 48.36f, 99.47f);
        notePath.cubicTo(40.3f, 103.51f, 30.85f, 102.64f, 27.32f, 97.39f);
        notePath.moveTo(58, 0 + customY);
        notePath.lineTo(56, 0 + customY);
        notePath.lineTo(56, 88);
        notePath.lineTo(58, 88);
        notePath.moveTo(27.32f, 97.39f);
        notePath.cubicTo(23.8f, 92.15f, 27.58f, 84.61f, 35.64f, 80.57f);
        notePath.cubicTo(43.7f, 76.53f, 53.15f, 77.3f, 56.68f, 82.54f);
        notePath.cubicTo(60.2f, 87.78f, 56.42f, 95.32f, 48.36f, 99.47f);
        notePath.cubicTo(40.3f, 103.51f, 30.85f, 102.64f, 27.32f, 97.39f);
        notePath.close();

        return notePath;
    }
}