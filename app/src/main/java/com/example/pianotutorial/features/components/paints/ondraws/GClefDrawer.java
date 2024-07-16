package com.example.pianotutorial.features.components.paints.ondraws;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;

import androidx.core.content.ContextCompat;

import com.example.pianotutorial.R;

public class GClefDrawer {
    private final VectorDrawable gClefDrawable;

    public GClefDrawer(Context context) {
        this.gClefDrawable = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.vector_g_clef);
    }

    public void draw(Canvas canvas) {
        // Set the bounds for the drawable
        int left = 140; // Adjust as needed
        int top = 130; // Adjust as needed

        // Calculate the new width and height for scaling
        int originalWidth = gClefDrawable.getIntrinsicWidth();
        int originalHeight = gClefDrawable.getIntrinsicHeight();
        float scaleFactor = 0.6f; // Adjust this value to change the size of the G-clef

        int right = left + (int) (originalWidth * scaleFactor);
        int bottom = top + (int) (originalHeight * scaleFactor);
        gClefDrawable.setBounds(left, top, right, bottom);

        // Draw the drawable on the canvas
        gClefDrawable.draw(canvas);
    }
}
