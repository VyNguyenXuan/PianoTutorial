package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class FlatSignPaint {
    public static Paint create() {
        Paint flatSignPaint = new Paint();
        flatSignPaint.setColor(0xFF000000); // Black color
        flatSignPaint.setStyle(Paint.Style.FILL);
        flatSignPaint.setStrokeWidth(3);

        return flatSignPaint;
    }

    public static Path createPath() {
        Path flatSignPath = new Path();
        float yOffset = -12f;
        flatSignPath.moveTo(2.475f, 0 + yOffset);
        flatSignPath.lineTo(2.475f, 31.091f + yOffset);
        flatSignPath.lineTo(2.475f, 33.186f + yOffset);
        flatSignPath.lineTo(2.475f, 37.378f + yOffset);
        flatSignPath.cubicTo(5.332f, 34.693f + yOffset, 8.537f, 33.317f + yOffset, 12.091f, 33.252f + yOffset);
        flatSignPath.cubicTo(14.313f, 33.252f + yOffset, 16.217f, 34.201f + yOffset, 17.804f, 36.101f + yOffset);
        flatSignPath.cubicTo(19.2f, 37.869f + yOffset, 19.93f, 39.834f + yOffset, 19.994f, 41.995f + yOffset);
        flatSignPath.cubicTo(20.057f, 43.698f + yOffset, 19.645f, 45.662f + yOffset, 18.756f, 47.889f + yOffset);
        flatSignPath.cubicTo(18.439f, 48.806f + yOffset, 17.74f, 49.788f + yOffset, 16.661f, 50.836f + yOffset);
        flatSignPath.cubicTo(15.836f, 51.622f + yOffset, 14.979f, 52.441f + yOffset, 14.091f, 53.292f + yOffset);
        flatSignPath.cubicTo(9.394f, 56.829f + yOffset, 4.697f, 60.398f + yOffset, 0, 64 + yOffset);
        flatSignPath.lineTo(0, 0 + yOffset);
        flatSignPath.lineTo(2.475f, 0 + yOffset);
        flatSignPath.moveTo(10.187f, 39.539f + yOffset);
        flatSignPath.cubicTo(9.426f, 38.622f + yOffset, 8.442f, 38.164f + yOffset, 7.236f, 38.164f + yOffset);
        flatSignPath.cubicTo(5.712f, 38.164f + yOffset, 4.475f, 39.048f + yOffset, 3.523f, 40.816f + yOffset);
        flatSignPath.cubicTo(2.825f, 42.191f + yOffset, 2.475f, 45.433f + yOffset, 2.475f, 50.542f + yOffset);
        flatSignPath.lineTo(2.475f, 58.99f + yOffset);
        flatSignPath.cubicTo(2.539f, 59.252f + yOffset, 4.316f, 57.647f + yOffset, 7.807f, 54.176f + yOffset);
        flatSignPath.cubicTo(9.711f, 52.343f + yOffset, 10.949f, 50.181f + yOffset, 11.52f, 47.693f + yOffset);
        flatSignPath.cubicTo(11.774f, 46.71f + yOffset, 11.901f, 45.728f + yOffset, 11.901f, 44.746f + yOffset);
        flatSignPath.cubicTo(11.901f, 42.584f + yOffset, 11.33f, 40.849f + yOffset, 10.187f, 39.539f + yOffset);
        flatSignPath.close();

        return flatSignPath;
    }
}
