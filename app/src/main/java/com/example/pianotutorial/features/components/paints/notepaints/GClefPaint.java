package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class GClefPaint {
    public static Paint create() {
        Paint naturalSignPaint = new Paint();
        naturalSignPaint.setColor(0xFF000000); // Black color
        naturalSignPaint.setStyle(Paint.Style.FILL);
        naturalSignPaint.setStrokeWidth(3);

        return naturalSignPaint;
    }

    public static Path createPath(float translateX) {
        float translateY = 142f;
        float scaleFactor = 1.7f;
        Path path = new Path();
        path.moveTo(33.02f, 91.69f);
        path.cubicTo(30.44f, 92.54f, 28.32f, 93.97f, 26.64f, 95.98f);
        path.cubicTo(24.95f, 97.99f, 23.93f, 100.19f, 23.56f, 102.57f);
        path.cubicTo(23.19f, 104.95f, 23.5f, 107.31f, 24.47f, 109.64f);
        path.cubicTo(25.44f, 111.96f, 27.27f, 113.84f, 29.95f, 115.27f);
        path.lineTo(30.42f, 116.86f);
        path.cubicTo(27.84f, 116.33f, 25.59f, 115.25f, 23.64f, 113.61f);
        path.cubicTo(20.02f, 110.59f, 18.02f, 106.7f, 17.65f, 101.93f);
        path.cubicTo(17.44f, 99.55f, 17.67f, 97.25f, 18.32f, 95.03f);
        path.cubicTo(18.98f, 92.8f, 19.89f, 90.77f, 21.04f, 88.91f);
        path.cubicTo(22.46f, 86.96f, 24.14f, 85.26f, 26.08f, 83.83f);
        path.cubicTo(26.49f, 83.49f, 26.99f, 83.12f, 28.45f, 82.09f);
        path.lineTo(27.66f, 66.45f);
        path.cubicTo(25.09f, 68.62f, 22.54f, 71.01f, 20.02f, 73.63f);
        path.cubicTo(17.49f, 76.25f, 15.21f, 79.02f, 13.16f, 81.93f);
        path.cubicTo(11.11f, 84.84f, 9.47f, 87.92f, 8.24f, 91.18f);
        path.cubicTo(7f, 94.43f, 6.38f, 97.86f, 6.38f, 101.46f);
        path.cubicTo(6.38f, 104.79f, 7.08f, 107.93f, 8.47f, 110.87f);
        path.cubicTo(9.86f, 113.8f, 11.72f, 116.36f, 14.03f, 118.53f);
        path.cubicTo(16.34f, 120.7f, 19f, 122.4f, 22.03f, 123.65f);
        path.cubicTo(25.05f, 124.89f, 28.11f, 125.51f, 31.21f, 125.51f);
        path.lineTo(32.66f, 125.35f);
        path.lineTo(35.42f, 124.96f);
        path.cubicTo(36.39f, 124.8f, 37.29f, 124.63f, 38.1f, 124.44f);
        path.lineTo(38.85f, 121.62f);
        path.lineTo(33.02f, 91.69f);
        path.moveTo(35.78f, 91.38f);
        path.lineTo(42.24f, 122.89f);
        path.cubicTo(45.97f, 121.46f, 48.49f, 119.02f, 49.8f, 115.55f);
        path.cubicTo(51.12f, 112.08f, 51.42f, 108.56f, 50.71f, 104.99f);
        path.cubicTo(50f, 101.42f, 48.33f, 98.26f, 45.71f, 95.5f);
        path.cubicTo(43.08f, 92.75f, 39.77f, 91.38f, 35.78f, 91.38f);
        path.moveTo(27.5f, 48.9f);
        path.cubicTo(29.13f, 48.06f, 30.64f, 46.79f, 32.03f, 45.09f);
        path.cubicTo(33.43f, 43.4f, 34.71f, 41.59f, 35.9f, 39.65f);
        path.cubicTo(37.08f, 37.72f, 38.1f, 35.75f, 38.97f, 33.74f);
        path.cubicTo(39.84f, 31.73f, 40.53f, 29.9f, 41.06f, 28.26f);
        path.cubicTo(41.63f, 26.52f, 42.03f, 24.56f, 42.24f, 22.39f);
        path.lineTo(41.22f, 16.91f);
        path.lineTo(38.73f, 14.61f);
        path.lineTo(35.66f, 14.85f);
        path.lineTo(32.74f, 16.63f);
        path.lineTo(30.73f, 18.81f);
        path.cubicTo(29.58f, 20.88f, 28.57f, 23.18f, 27.7f, 25.72f);
        path.cubicTo(26.83f, 28.26f, 26.24f, 30.9f, 25.93f, 33.62f);
        path.cubicTo(25.61f, 36.35f, 25.57f, 38.97f, 25.81f, 41.48f);
        path.cubicTo(26.04f, 43.99f, 26.61f, 46.47f, 27.5f, 48.9f);
        path.moveTo(25.14f, 51.36f);
        path.cubicTo(24.25f, 47.87f, 23.46f, 44.44f, 22.77f, 41.08f);
        path.cubicTo(22.09f, 37.72f, 21.75f, 34.27f, 21.75f, 30.72f);
        path.lineTo(21.75f, 22.19f);
        path.lineTo(24.31f, 13.14f);
        path.lineTo(28.33f, 5.24f);
        path.lineTo(35.15f, 0.16f);
        path.lineTo(37.24f, 0.64f);
        path.lineTo(38.81f, 2.5f);
        path.lineTo(40.27f, 5.04f);
        path.cubicTo(42.63f, 9.53f, 44.33f, 15.44f, 45.47f, 24.53f);
        path.cubicTo(45.68f, 29.08f, 45.43f, 33.58f, 44.72f, 38.03f);
        path.cubicTo(44.01f, 42.47f, 42.58f, 46.81f, 40.43f, 51.05f);
        path.lineTo(35.46f, 58.67f);
        path.lineTo(30.42f, 64.07f);
        path.lineTo(33.65f, 79.94f);
        path.lineTo(35.27f, 79.94f);
        path.cubicTo(38.35f, 79.98f, 41.61f, 80.49f, 44.45f, 81.61f);
        path.cubicTo(47.18f, 82.88f, 49.53f, 84.63f, 51.5f, 86.85f);
        path.cubicTo(53.47f, 89.07f, 55.04f, 91.57f, 56.23f, 94.35f);
        path.cubicTo(57.41f, 97.13f, 58f, 99.95f, 58f, 102.81f);
        path.cubicTo(58f, 105.67f, 57.58f, 108.58f, 56.74f, 111.54f);
        path.cubicTo(54.58f, 117.15f, 51.14f, 121.31f, 46.42f, 124f);
        path.lineTo(44.17f, 125.08f);
        path.lineTo(43.03f, 126.94f);
        path.cubicTo(44.29f, 132.71f, 45.14f, 136.68f, 45.59f, 138.85f);
        path.lineTo(46.57f, 147.26f);
        path.lineTo(44.88f, 156.15f);
        path.lineTo(39.48f, 162.62f);
        path.cubicTo(37.22f, 164.3f, 35.16f, 165.27f, 33.29f, 165.57f);
        path.lineTo(29.47f, 166f);
        path.lineTo(22.54f, 164.65f);
        path.lineTo(15.52f, 159.81f);
        path.cubicTo(13.63f, 157.63f, 12.69f, 154.99f, 12.69f, 151.87f);
        path.lineTo(14.38f, 145.83f);
        path.lineTo(18.83f, 141.4f);
        path.cubicTo(20.88f, 140.33f, 22.74f, 140.04f, 24.39f, 140.51f);
        path.cubicTo(26.04f, 140.99f, 27.41f, 141.9f, 28.49f, 143.22f);
        path.lineTo(30.73f, 148.02f);
        path.lineTo(30.65f, 153.3f);
        path.lineTo(27.78f, 157.46f);
        path.cubicTo(26.33f, 158.61f, 24.32f, 159.12f, 21.75f, 159.02f);
        path.lineTo(26.16f, 162.62f);
        path.lineTo(31.99f, 162.74f);
        path.cubicTo(33.99f, 162.32f, 35.87f, 161.54f, 37.63f, 160.4f);
        path.lineTo(41.77f, 156.71f);
        path.lineTo(43.19f, 152.99f);
        path.lineTo(43.74f, 148.18f);
        path.lineTo(42.79f, 139.96f);
        path.lineTo(39.8f, 127.89f);
        path.lineTo(35.78f, 127.89f);
        path.lineTo(24.43f, 127.1f);
        path.lineTo(11.94f, 120.31f);
        path.lineTo(3.27f, 108.48f);
        path.lineTo(0f, 93.36f);
        path.lineTo(0f, 93.36f);
        path.lineTo(0f, 101.46f);
        path.lineTo(0f, 97.93f);
        path.lineTo(2.21f, 81.29f);
        path.lineTo(25.14f, 51.36f);
        path.close();

        Matrix matrix = new Matrix();
        matrix.setTranslate(translateX, translateY);
        matrix.preScale(scaleFactor, scaleFactor);

        path.transform(matrix);
        return path;
    }
}
