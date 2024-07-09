package com.example.pianotutorial.features.components.paints.notepaints;

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

        // First path
        notePath.moveTo(28f, 180f);
        notePath.lineTo(26f, 180f);
        notePath.lineTo(26f, 92f);
        notePath.lineTo(28f, 92f);
        notePath.lineTo(28f, 145.79f);
        notePath.cubicTo(29.64f, 144.83f, 31.22f, 143.92f, 32.73f, 143.05f);
        notePath.cubicTo(49.7f, 133.29f, 58.12f, 128.45f, 48.83f, 104f);
        notePath.cubicTo(58.63f, 118.41f, 57.26f, 126.47f, 51.43f, 134.56f);
        notePath.cubicTo(54.06f, 149.18f, 47.15f, 156.09f, 39.94f, 163.3f);
        notePath.cubicTo(35.24f, 168f, 30.41f, 172.83f, 28f, 180f);

        notePath.moveTo(50.12f, 136.29f);
        notePath.cubicTo(50.1f, 136.44f, 50.09f, 136.6f, 50.07f, 136.76f);
        notePath.cubicTo(49.39f, 143.6f, 48.44f, 153.25f, 28.34f, 163.42f);
        notePath.cubicTo(33.29f, 154.99f, 38.96f, 148.79f, 43.88f, 143.41f);
        notePath.cubicTo(46.17f, 140.9f, 48.3f, 138.57f, 50.12f, 136.29f);

        // Second path
        notePath.moveTo(27.32f, 97.39f);
        notePath.cubicTo(23.8f, 92.15f, 27.58f, 84.61f, 35.64f, 80.57f);
        notePath.cubicTo(43.7f, 76.53f, 53.15f, 77.3f, 56.68f, 82.54f);
        notePath.cubicTo(60.2f, 87.78f, 56.42f, 95.32f, 48.36f, 99.47f);
        notePath.cubicTo(40.3f, 103.51f, 30.85f, 102.64f, 27.32f, 97.39f);
        notePath.close();

        return notePath;
    }
}
