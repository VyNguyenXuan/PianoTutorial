package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class WholeNotePaint {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();

        // Path data from the vector drawable
        notePath.moveTo(32.1f, 100.51f);
        notePath.cubicTo(26.45f, 98.81f, 22f, 94.16f, 22f, 89.98f);
        notePath.cubicTo(22f, 78.16f, 47.81f, 73.48f, 58.47f, 83.37f);
        notePath.cubicTo(70f, 94.07f, 51.19f, 106.29f, 32.1f, 100.51f);
        notePath.lineTo(32.1f, 100.51f);
        notePath.close();

        notePath.moveTo(49.31f, 97.54f);
        notePath.cubicTo(52.46f, 92.83f, 49.45f, 83.49f, 44.01f, 81.05f);
        notePath.cubicTo(36.03f, 77.47f, 31.13f, 83.57f, 34.46f, 92.96f);
        notePath.cubicTo(36.76f, 99.45f, 46.12f, 102.34f, 49.31f, 97.54f);
        notePath.close();

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(1.2f, 1.2f);
        notePath.transform(scaleMatrix);

        return notePath;
    }
}
