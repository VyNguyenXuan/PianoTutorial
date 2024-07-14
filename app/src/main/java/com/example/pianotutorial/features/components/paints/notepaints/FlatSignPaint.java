package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class FlatSignPaint {
    public static Paint create() {
        Paint sharpSignPaint = new Paint();
        sharpSignPaint.setColor(0xFF000000); // Black color
        sharpSignPaint.setStyle(Paint.Style.FILL);
        sharpSignPaint.setStrokeWidth(3);

        return sharpSignPaint;
    }

    public static Path createPath() {
        Path sharpSignPath = new Path();
        sharpSignPath.moveTo(2.475f, 0);
        sharpSignPath.lineTo(2.475f, 31.091f);
        sharpSignPath.lineTo(2.475f, 33.186f);
        sharpSignPath.lineTo(2.475f, 37.378f);
        sharpSignPath.cubicTo(5.332f, 34.693f, 8.537f, 33.317f, 12.091f, 33.252f);
        sharpSignPath.cubicTo(14.313f, 33.252f, 16.217f, 34.201f, 17.804f, 36.101f);
        sharpSignPath.cubicTo(19.2f, 37.869f, 19.93f, 39.834f, 19.994f, 41.995f);
        sharpSignPath.cubicTo(20.057f, 43.698f, 19.645f, 45.662f, 18.756f, 47.889f);
        sharpSignPath.cubicTo(18.439f, 48.806f, 17.74f, 49.788f, 16.661f, 50.836f);
        sharpSignPath.cubicTo(15.836f, 51.622f, 14.979f, 52.441f, 14.091f, 53.292f);
        sharpSignPath.cubicTo(9.394f, 56.829f, 4.697f, 60.398f, 0, 64f);
        sharpSignPath.lineTo(0, 0);
        sharpSignPath.lineTo(2.475f, 0);
        sharpSignPath.moveTo(10.187f, 39.539f);
        sharpSignPath.cubicTo(9.426f, 38.622f, 8.442f, 38.164f, 7.236f, 38.164f);
        sharpSignPath.cubicTo(5.712f, 38.164f, 4.475f, 39.048f, 3.523f, 40.816f);
        sharpSignPath.cubicTo(2.825f, 42.191f, 2.475f, 45.433f, 2.475f, 50.542f);
        sharpSignPath.lineTo(2.475f, 58.99f);
        sharpSignPath.cubicTo(2.539f, 59.252f, 4.316f, 57.647f, 7.807f, 54.176f);
        sharpSignPath.cubicTo(9.711f, 52.343f, 10.949f, 50.181f, 11.52f, 47.693f);
        sharpSignPath.cubicTo(11.774f, 46.71f, 11.901f, 45.728f, 11.901f, 44.746f);
        sharpSignPath.cubicTo(11.901f, 42.584f, 11.33f, 40.849f, 10.187f, 39.539f);
        sharpSignPath.close();

        return sharpSignPath;
    }
}
