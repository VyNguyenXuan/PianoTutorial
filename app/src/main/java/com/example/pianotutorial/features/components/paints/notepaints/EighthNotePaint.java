package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class EighthNotePaint {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();

        // Path for the eighth note shape
        notePath.moveTo(55, 0);
        notePath.lineTo(58, 0);
        notePath.cubicTo(63.03f, 8.73f, 68.85f, 15.09f, 73.88f, 20.59f);
        notePath.cubicTo(84.89f, 32.63f, 92.09f, 40.5f, 78.83f, 60f);
        notePath.cubicTo(88.12f, 35.55f, 79.7f, 30.71f, 62.73f, 20.95f);
        notePath.cubicTo(61.22f, 20.08f, 59.64f, 19.17f, 58f, 18.21f);
        notePath.lineTo(58, 88);
        notePath.lineTo(55, 88);
        notePath.lineTo(55, 0);
        notePath.close();

        // Path for the dot on the eighth note shape
        notePath.moveTo(27.32f, 97.39f);
        notePath.cubicTo(23.8f, 92.15f, 27.58f, 84.61f, 35.64f, 80.57f);
        notePath.cubicTo(43.7f, 76.53f, 53.15f, 77.3f, 56.68f, 82.54f);
        notePath.cubicTo(60.2f, 87.78f, 56.42f, 95.32f, 48.36f, 99.47f);
        notePath.cubicTo(40.3f, 103.51f, 30.85f, 102.64f, 27.32f, 97.39f);
        notePath.close();

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(1.2f, 1.2f);
        notePath.transform(scaleMatrix);
        return notePath;
    }
}