package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class HalfNotePaintFlipReverse {
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

        notePath.moveTo(2.58f, 96.81f);
        notePath.cubicTo(3.97f, 98.99f, 11.02f, 97.79f, 18.33f, 94.07f);
        notePath.lineTo(18.58f, 93.97f);
        notePath.lineTo(18.83f, 93.86f);
        notePath.cubicTo(26.02f, 90.14f, 30.68f, 85.45f, 29.17f, 83.37f);
        notePath.cubicTo(27.78f, 81.19f, 20.72f, 82.39f, 13.42f, 86.1f);
        notePath.lineTo(13.17f, 86.21f);
        notePath.lineTo(13.04f, 86.32f);
        notePath.cubicTo(5.86f, 89.92f, 1.2f, 94.62f, 2.58f, 96.81f);
        notePath.close();

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(1.2f, 1.2f);
        notePath.transform(scaleMatrix);

        return notePath;
    }
}
