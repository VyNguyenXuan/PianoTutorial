package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class SixteenthNotePaintWhiteSpace {
    public static Paint create() {
        Paint whitePaint = new Paint();
        whitePaint.setColor(0xFF1DB954); // White color
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setStrokeWidth(3);

        return whitePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        notePath.moveTo(80.12f, 43.72f);
        notePath.cubicTo(80.1f, 43.56f, 80.09f, 43.4f, 80.07f, 43.24f);
        notePath.cubicTo(79.39f, 36.4f, 78.44f, 26.75f, 58.34f, 16.58f);
        notePath.cubicTo(63.29f, 25.01f, 68.96f, 31.22f, 73.88f, 36.59f);
        notePath.cubicTo(76.17f, 39.1f, 78.3f, 41.42f, 80.12f, 43.72f);
        notePath.close();
        return notePath;
    }

}
