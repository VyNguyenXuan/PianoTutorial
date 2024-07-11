package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.VectorDrawable;
import androidx.core.content.ContextCompat;
import com.example.pianotutorial.R;
import android.content.Context;

public class GlefDrawer {
    private final Paint staffPaint;
    private final Context context;
    private final VectorDrawable gClefDrawable;

    public GlefDrawer(Paint staffPaint, Context context) {
        this.staffPaint = staffPaint;
        this.context = context;
        this.gClefDrawable = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.vector_g_clef);
    }

    public void draw(Canvas canvas, int width, int height) {
        // Set the bounds for the drawable
        int left = 100; // Adjust as needed
        int top = 110; // Adjust as needed

        // Calculate the new width and height for scaling
        int originalWidth = gClefDrawable.getIntrinsicWidth();
        int originalHeight = gClefDrawable.getIntrinsicHeight();
        float scaleFactor = 0.5f; // Adjust this value to change the size of the G-clef

        int right = left + (int) (originalWidth * scaleFactor);
        int bottom = top + (int) (originalHeight * scaleFactor);
        gClefDrawable.setBounds(left, top, right, bottom);

        // Draw the drawable on the canvas
        gClefDrawable.draw(canvas);
    }
}
