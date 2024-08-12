package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class HalfNotePaint {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        notePath.moveTo(27.32f, 97.39f);
        notePath.cubicTo(23.8f, 92.15f, 27.58f, 84.61f, 35.64f, 80.57f);
        notePath.cubicTo(43.7f, 76.53f, 53.15f, 77.3f, 56.68f, 82.54f);
        notePath.cubicTo(60.2f, 87.78f, 56.42f, 95.32f, 48.36f, 99.47f);
        notePath.cubicTo(40.3f, 103.51f, 30.85f, 102.64f, 27.32f, 97.39f);

        notePath.moveTo(55.42f, 83.19f);
        notePath.cubicTo(54.03f, 81.01f, 46.98f, 82.21f, 39.67f, 85.93f);
        notePath.cubicTo(39.54f, 85.93f, 39.54f, 86.03f, 39.42f, 86.03f);
        notePath.cubicTo(39.29f, 86.03f, 39.17f, 86.14f, 39.17f, 86.14f);
        notePath.cubicTo(31.98f, 89.86f, 27.32f, 94.55f, 28.83f, 96.63f);
        notePath.cubicTo(30.22f, 98.81f, 37.28f, 97.61f, 44.58f, 93.9f);
        notePath.cubicTo(44.71f, 93.9f, 44.71f, 93.79f, 44.83f, 93.79f);
        notePath.lineTo(44.96f, 93.68f);
        notePath.cubicTo(52.14f, 90.08f, 56.8f, 85.38f, 55.42f, 83.19f);

        notePath.moveTo(58f, 0f);
        notePath.lineTo(55f, 0f);
        notePath.lineTo(55f, 88f);
        notePath.lineTo(58f, 88f);
        notePath.lineTo(58f, 0f);

        notePath.moveTo(27.32f, 97.39f);
        notePath.cubicTo(23.8f, 92.15f, 27.58f, 84.61f, 35.64f, 80.57f);
        notePath.cubicTo(43.7f, 76.53f, 53.15f, 77.3f, 56.68f, 82.54f);
        notePath.cubicTo(60.2f, 87.78f, 56.42f, 95.32f, 48.36f, 99.47f);
        notePath.cubicTo(40.3f, 103.51f, 30.85f, 102.64f, 27.32f, 97.39f);

        notePath.moveTo(55.42f, 83.19f);
        notePath.cubicTo(54.03f, 81.01f, 46.98f, 82.21f, 39.67f, 85.93f);
        notePath.cubicTo(39.54f, 85.93f, 39.54f, 86.03f, 39.42f, 86.03f);
        notePath.cubicTo(39.29f, 86.03f, 39.17f, 86.14f, 39.17f, 86.14f);
        notePath.cubicTo(31.98f, 89.86f, 27.32f, 94.55f, 28.83f, 96.63f);
        notePath.cubicTo(30.22f, 98.81f, 37.28f, 97.61f, 44.58f, 93.9f);
        notePath.cubicTo(44.71f, 93.9f, 44.71f, 93.79f, 44.83f, 93.79f);
        notePath.lineTo(44.96f, 93.68f);
        notePath.cubicTo(52.14f, 90.08f, 56.8f, 85.38f, 55.42f, 83.19f);

        notePath.close();

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(1.2f, 1.2f);
        notePath.transform(scaleMatrix);


        return notePath;
    }
}