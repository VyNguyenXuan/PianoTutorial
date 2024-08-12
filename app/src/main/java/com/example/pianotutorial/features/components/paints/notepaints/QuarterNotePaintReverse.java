package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class QuarterNotePaintReverse {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        notePath.moveTo(29f, 180f);
        notePath.lineTo(26f, 180f);
        notePath.lineTo(26f, 92f);
        notePath.lineTo(29f, 92f);
        notePath.lineTo(29f, 180f);

        // Second path
        notePath.moveTo(27.32f, 97.39f);
        notePath.cubicTo(23.8f, 92.15f, 27.58f, 84.61f, 35.64f, 80.57f);
        notePath.cubicTo(43.7f, 76.53f, 53.15f, 77.3f, 56.68f, 82.54f);
        notePath.cubicTo(60.2f, 87.78f, 56.42f, 95.32f, 48.36f, 99.47f);
        notePath.cubicTo(40.3f, 103.51f, 30.85f, 102.64f, 27.32f, 97.39f);

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(1.2f, 1.2f);
        notePath.transform(scaleMatrix);

        return notePath;
    }
}
