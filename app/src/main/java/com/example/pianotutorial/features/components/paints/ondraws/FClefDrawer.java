package com.example.pianotutorial.features.components.paints.ondraws;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;

import androidx.core.content.ContextCompat;

import com.example.pianotutorial.R;

public class FClefDrawer {
    private final VectorDrawable fClefDrawable;

    public FClefDrawer(Context context) {
        this.fClefDrawable = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.vector_f_clef);
    }

    public void draw(Canvas canvas) {
        // Set the bounds for the drawable
        int left = 140; // Adjust as needed
        int top = 483; // Adjust as needed

        // Calculate the new width and height for scaling
        int originalWidth = fClefDrawable.getIntrinsicWidth();
        int originalHeight = fClefDrawable.getIntrinsicHeight();
        float scaleFactor = 0.6f; // Adjust this value to change the size of the G-clef

        int right = left + (int) (originalWidth * scaleFactor);
        int bottom = top + (int) (originalHeight * scaleFactor);
        fClefDrawable.setBounds(left, top, right, bottom);

        // Draw the drawable on the canvas
        fClefDrawable.draw(canvas);
    }
}
