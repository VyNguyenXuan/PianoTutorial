package com.example.pianotutorial.features.components.paints.notepaints;

import android.graphics.Paint;
import android.graphics.Path;

public class SharpSignPaint {
    public static Paint create() {
        Paint flatSignPaint = new Paint();
        flatSignPaint.setColor(0xFF000000); // Black color
        flatSignPaint.setStyle(Paint.Style.FILL);
        flatSignPaint.setStrokeWidth(3);

        return flatSignPaint;
    }

    public static Path createPath() {
        Path flatSignPath = new Path();
        flatSignPath.moveTo(6.523f, 43.5f);
        flatSignPath.lineTo(6.523f, 26.659f);
        flatSignPath.lineTo(13.368f, 24.682f);
        flatSignPath.lineTo(13.368f, 41.438f);
        flatSignPath.lineTo(6.523f, 43.5f);
        flatSignPath.moveTo(20f, 39.426f);
        flatSignPath.lineTo(15.294f, 40.837f);
        flatSignPath.lineTo(15.294f, 24.081f);
        flatSignPath.lineTo(20f, 22.706f);
        flatSignPath.lineTo(20f, 15.746f);
        flatSignPath.lineTo(15.294f, 17.12f);
        flatSignPath.lineTo(15.294f, 0f);
        flatSignPath.lineTo(13.368f, 0f);
        flatSignPath.lineTo(13.368f, 17.64f);
        flatSignPath.lineTo(6.523f, 19.698f);
        flatSignPath.lineTo(6.523f, 3.05f);
        flatSignPath.lineTo(4.706f, 3.05f);
        flatSignPath.lineTo(4.706f, 20.332f);
        flatSignPath.lineTo(0f, 21.71f);
        flatSignPath.lineTo(0f, 28.685f);
        flatSignPath.lineTo(4.706f, 27.31f);
        flatSignPath.lineTo(4.706f, 44.034f);
        flatSignPath.lineTo(0f, 45.405f);
        flatSignPath.lineTo(0f, 52.351f);
        flatSignPath.lineTo(4.706f, 50.976f);
        flatSignPath.lineTo(4.706f, 68f);
        flatSignPath.lineTo(6.523f, 68f);
        flatSignPath.lineTo(6.523f, 50.368f);
        flatSignPath.lineTo(13.368f, 48.398f);
        flatSignPath.lineTo(13.368f, 64.96f);
        flatSignPath.lineTo(15.294f, 64.96f);
        flatSignPath.lineTo(15.294f, 47.775f);
        flatSignPath.lineTo(20f, 46.397f);
        flatSignPath.lineTo(20f, 39.426f);
        flatSignPath.close();

        return flatSignPath;
    }
}
