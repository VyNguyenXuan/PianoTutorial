package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class FClefPaint {
    public static Paint create(String colorHex) {
        // Parse the color hex string into an integer
        int color = Color.parseColor(colorHex);
        Paint naturalSignPaint = new Paint();
        naturalSignPaint.setColor(color);
        naturalSignPaint.setStyle(Paint.Style.FILL);
        naturalSignPaint.setStrokeWidth(3);
        return naturalSignPaint;
    }


    public static Path createPath(float translateX) {
        float translateY = 128f;
        float scaleFactor = 1.7f;
        Path path = new Path();
        path.moveTo(63.88f, 66.41f);
        path.cubicTo(63.88f, 69.53f, 61.47f, 72.06f, 58.49f, 72.06f);
        path.cubicTo(55.51f, 72.06f, 53.1f, 69.53f, 53.1f, 66.41f);
        path.cubicTo(53.1f, 63.29f, 55.51f, 60.76f, 58.49f, 60.76f);
        path.cubicTo(61.47f, 60.76f, 63.88f, 63.29f, 63.88f, 66.41f);
        path.moveTo(63.51f, 47.31f);
        path.cubicTo(63.51f, 50.43f, 61.09f, 52.96f, 58.12f, 52.96f);
        path.cubicTo(55.14f, 52.96f, 52.72f, 50.43f, 52.72f, 47.31f);
        path.cubicTo(52.72f, 44.19f, 55.14f, 41.66f, 58.12f, 41.66f);
        path.cubicTo(61.09f, 41.66f, 63.51f, 44.19f, 63.51f, 47.31f);
        path.moveTo(0f, 106.61f);
        path.cubicTo(0f, 105.8f, 3.88f, 102.48f, 8.63f, 99.23f);
        path.cubicTo(18.83f, 92.24f, 26.2f, 84.59f, 30.49f, 76.54f);
        path.cubicTo(40.32f, 58.09f, 37.21f, 38.85f, 24.11f, 37.09f);
        path.cubicTo(16.86f, 36.12f, 7.69f, 41.41f, 7.69f, 46.55f);
        path.cubicTo(7.69f, 48.46f, 8.17f, 48.62f, 13.49f, 48.38f);
        path.cubicTo(19.99f, 48.09f, 24.38f, 54.42f, 20.87f, 59f);
        path.cubicTo(15.31f, 66.24f, 1.92f, 62.08f, 1.92f, 53.11f);
        path.cubicTo(1.92f, 45.26f, 6.7f, 39f, 15.12f, 35.85f);
        path.cubicTo(33.13f, 29.12f, 50.27f, 41.54f, 48.77f, 60.24f);
        path.cubicTo(47.54f, 75.57f, 34.2f, 89.88f, 8.29f, 103.66f);
        path.cubicTo(2.14f, 106.93f, 0f, 107.69f, 0f, 106.61f);
        path.close();
        Matrix matrix = new Matrix();
        matrix.setTranslate(translateX, translateY);
        matrix.preScale(scaleFactor, scaleFactor);

        path.transform(matrix);
        return path;
    }
}
