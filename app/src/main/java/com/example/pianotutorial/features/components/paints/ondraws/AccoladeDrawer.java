package com.example.pianotutorial.features.components.paints.ondraws;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;

import androidx.core.content.ContextCompat;

import com.example.pianotutorial.R;

public class AccoladeDrawer {
    private final VectorDrawable accoladeDrawable;

    public AccoladeDrawer(Context context) {
        this.accoladeDrawable = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.vector_accolade);
    }

    public void draw(Canvas canvas) {
        // Set the bounds for the drawable
        int left = 35; // Adjust as needed
        int top = 180; // Adjust as needed

        // Calculate the new width and height for scaling
        int originalWidth = accoladeDrawable.getIntrinsicWidth();
        int originalHeight = accoladeDrawable.getIntrinsicHeight();
        float scaleFactor = 0.585f; // Adjust this value to change the size of the G-clef

        int right = left + (int) (originalWidth * scaleFactor);
        int bottom = top + (int) (originalHeight * scaleFactor);
        accoladeDrawable.setBounds(left, top, right, bottom);

        // Draw the drawable on the canvas
        accoladeDrawable.draw(canvas);
    }
}
