package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class NaturalSignPaint {
    public static Paint create() {
        Paint naturalSignPaint = new Paint();
        naturalSignPaint.setColor(0xFF000000); // Black color
        naturalSignPaint.setStyle(Paint.Style.FILL);
        naturalSignPaint.setStrokeWidth(3);

        return naturalSignPaint;
    }

    public static Path createPath() {
        Path naturalSignPath = new Path();
        naturalSignPath.moveTo(17.0f, 16.64f);
        naturalSignPath.lineTo(17.0f, 68.0f);
        naturalSignPath.lineTo(14.794f, 68.0f);
        naturalSignPath.lineTo(14.794f, 48.751f);
        naturalSignPath.lineTo(3.0f, 51.989f);
        naturalSignPath.lineTo(3.0f, 0.0f);
        naturalSignPath.lineTo(5.121f, 0.0f);
        naturalSignPath.lineTo(5.121f, 20.058f);
        naturalSignPath.lineTo(17.0f, 16.64f);
        naturalSignPath.lineTo(17.0f, 16.64f);
        naturalSignPath.moveTo(5.121f, 28.693f);
        naturalSignPath.lineTo(5.121f, 42.815f);
        naturalSignPath.lineTo(14.794f, 40.116f);
        naturalSignPath.lineTo(14.794f, 25.995f);
        naturalSignPath.lineTo(5.121f, 28.693f);
        naturalSignPath.lineTo(5.121f, 28.693f);
        naturalSignPath.close();

        return naturalSignPath;
    }
}
