package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class HalfNotePaintFlip {
    public static Paint create() {
        Paint notePaint = new Paint();
        notePaint.setColor(0xFF000000); // Black color
        notePaint.setStyle(Paint.Style.FILL);
        notePaint.setStrokeWidth(3);

        return notePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        notePath.moveTo(55.32f, 97.39f);
        notePath.cubicTo(51.8f, 92.15f, 55.58f, 84.61f, 63.64f, 80.57f);
        notePath.cubicTo(71.7f, 76.53f, 81.15f, 77.3f, 84.68f, 82.54f);
        notePath.cubicTo(88.2f, 87.78f, 84.42f, 95.32f, 76.36f, 99.47f);
        notePath.cubicTo(68.3f, 103.51f, 58.85f, 102.64f, 55.32f, 97.39f);
        notePath.close();

        notePath.moveTo(83.42f, 83.19f);
        notePath.cubicTo(82.03f, 81.01f, 74.98f, 82.21f, 67.67f, 85.93f);
        notePath.lineTo(67.42f, 86.03f);
        notePath.lineTo(67.17f, 86.14f);
        notePath.cubicTo(59.98f, 89.86f, 55.32f, 94.55f, 56.83f, 96.63f);
        notePath.cubicTo(58.22f, 98.81f, 65.28f, 97.61f, 72.58f, 93.9f);
        notePath.lineTo(72.83f, 93.79f);
        notePath.lineTo(72.96f, 93.68f);
        notePath.cubicTo(80.14f, 90.08f, 84.8f, 85.38f, 83.42f, 83.19f);
        notePath.close();

        return notePath;
    }
}
