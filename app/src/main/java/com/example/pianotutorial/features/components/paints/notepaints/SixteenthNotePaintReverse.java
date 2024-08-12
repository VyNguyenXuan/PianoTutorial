package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class SixteenthNotePaintReverse {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        float value=16;

        notePath.moveTo(26f, 180f-value);
        notePath.lineTo(29f, 180f-value);
        notePath.cubicTo(33.03f, 171.27f-value, 38.85f, 164.91f-value, 43.88f, 159.41f-value);
        notePath.cubicTo(54.89f, 147.37f-value, 62.09f, 139.5f-value, 48.83f, 120f-value);
        notePath.cubicTo(58.12f, 144.45f-value, 49.7f, 149.29f-value, 32.73f, 159.05f-value);
        notePath.cubicTo(31.22f, 159.92f-value, 29.64f, 160.83f-value, 28f, 161.79f-value);
        notePath.lineTo(29f, 92f);
        notePath.lineTo(26f, 92f);
        notePath.lineTo(26f, 180f);

        // Second path
        notePath.moveTo(27.32f, 97.39f);
        notePath.cubicTo(23.8f, 92.15f, 27.58f, 84.61f, 35.64f, 80.57f);
        notePath.cubicTo(43.7f, 76.53f, 53.15f, 77.3f, 56.68f, 82.54f);
        notePath.cubicTo(60.2f, 87.78f, 56.42f, 95.32f, 48.36f, 99.47f);
        notePath.cubicTo(40.3f, 103.51f, 30.85f, 102.64f, 27.32f, 97.39f);
        notePath.close();

        notePath.moveTo(26f, 180f);
        notePath.lineTo(29f, 180f);
        notePath.cubicTo(33.03f, 171.27f, 38.85f, 164.91f, 43.88f, 159.41f);
        notePath.cubicTo(54.89f, 147.37f, 62.09f, 139.5f, 48.83f, 120f);
        notePath.cubicTo(58.12f, 144.45f, 49.7f, 149.29f, 32.73f, 159.05f);
        notePath.cubicTo(31.22f, 159.92f, 29.64f, 160.83f, 28f, 161.79f);
        notePath.lineTo(29f, 92f);
        notePath.lineTo(26f, 92f);
        notePath.lineTo(26f, 180f);

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(1.2f, 1.2f);
        notePath.transform(scaleMatrix);

        return notePath;
    }

}
