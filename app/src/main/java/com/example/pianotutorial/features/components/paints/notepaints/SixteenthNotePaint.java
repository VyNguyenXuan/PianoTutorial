package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class SixteenthNotePaint {
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
        notePath.close();
        // Path 1
        notePath.moveTo(58f, 0f);
        notePath.lineTo(56f, 0f);
        notePath.lineTo(56f, 88f);
        notePath.lineTo(58f, 88f);
        notePath.lineTo(58f, 34.21f);
        notePath.cubicTo(59.64f, 35.17f, 61.22f, 36.08f, 62.73f, 36.95f);
        notePath.cubicTo(79.7f, 46.71f, 88.12f, 51.55f, 78.83f, 76f);
        notePath.cubicTo(88.63f, 61.59f, 87.26f, 53.53f, 81.43f, 45.44f);
        notePath.cubicTo(84.06f, 30.82f, 77.15f, 23.91f, 69.94f, 16.7f);
        notePath.cubicTo(65.24f, 12f, 60.41f, 7.17f, 58f, 0f);
        notePath.close();

        notePath.moveTo(80.12f, 43.72f);
        notePath.cubicTo(80.1f, 43.56f, 80.09f, 43.4f, 80.07f, 43.24f);
        notePath.cubicTo(79.39f, 36.4f, 78.44f, 26.75f, 58.34f, 16.58f);
        notePath.cubicTo(63.29f, 25.01f, 68.96f, 31.22f, 73.88f, 36.59f);
        notePath.cubicTo(76.17f, 39.1f, 78.3f, 41.42f, 80.12f, 43.72f);
        notePath.close();

        notePath.moveTo(27.32f, 97.39f);
        notePath.cubicTo(23.8f, 92.15f, 27.58f, 84.61f, 35.64f, 80.57f);
        notePath.cubicTo(43.7f, 76.53f, 53.15f, 77.3f, 56.68f, 82.54f);
        notePath.cubicTo(60.2f, 87.78f, 56.42f, 95.32f, 48.36f, 99.47f);
        notePath.cubicTo(40.3f, 103.51f, 30.85f, 102.64f, 27.32f, 97.39f);
        notePath.close();

        return notePath;
    }
}


