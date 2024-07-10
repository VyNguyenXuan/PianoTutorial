package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class SixteenthNotePaintReverseWhiteSpace {
    public static Paint create() {
        Paint whitePaint = new Paint();
        whitePaint.setColor(0xFF1DB954); // White color
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setStrokeWidth(3);

        return whitePaint;
    }

    public static Path createPath() {
        Path notePath = new Path();
        notePath.moveTo(50.12f, 136.29f);
        notePath.cubicTo(50.1f, 136.44f, 50.09f, 136.6f, 50.07f, 136.76f);
        notePath.cubicTo(49.39f, 143.6f, 48.44f, 153.25f, 28.34f, 163.42f);
        notePath.cubicTo(33.29f, 154.99f, 38.96f, 148.79f, 43.88f, 143.41f);
        notePath.cubicTo(46.17f, 140.9f, 48.3f, 138.57f, 50.12f, 136.29f);
        notePath.close();
        return notePath;
    }

}
