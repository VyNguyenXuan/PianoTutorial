package com.example.pianotutorial.features.components.paints.ondraws;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;

import androidx.core.content.ContextCompat;

import com.example.pianotutorial.R;

public class LeftClefDrawer {
    private final VectorDrawable clefDrawable;
    private final float left;
    private final float top;
    private final float scaleFactor;

    public LeftClefDrawer(Context context, int clefType, float left) {
        this.left = left;
        switch (clefType) {
            case 1: // F-clef
                this.clefDrawable = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.vector_f_clef);
                this.top = 483f;
                this.scaleFactor = 0.6f;
                break;
            case 0: // G-clef
                this.clefDrawable = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.vector_g_clef);
                this.top = 483f;
                this.scaleFactor = 0.6f;
                break;
            default:
                throw new IllegalArgumentException("Invalid clef type. Use 0 for G-clef and 1 for F-clef.");
        }
    }

    public void draw(Canvas canvas) {
        // Calculate the new width and height for scaling
        float originalWidth = clefDrawable.getIntrinsicWidth();
        float originalHeight = clefDrawable.getIntrinsicHeight();

        float right = left + (originalWidth * scaleFactor);
        float bottom = top + (originalHeight * scaleFactor);
        clefDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);

        // Draw the drawable on the canvas
        clefDrawable.draw(canvas);
    }
}