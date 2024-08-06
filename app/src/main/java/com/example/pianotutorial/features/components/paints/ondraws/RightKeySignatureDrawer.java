package com.example.pianotutorial.features.components.paints.ondraws;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;

import androidx.core.content.ContextCompat;

import com.example.pianotutorial.R;

import java.util.List;

public class RightKeySignatureDrawer {
    private final VectorDrawable sharpDrawable;
    private final VectorDrawable flatDrawable;
    private final int clefType;
    private final float left;
    private final float baseTop;
    private final float scaleFactor;
    private final List<Integer> keySignatureList;
    private final int keySignature;

    public RightKeySignatureDrawer(Context context, int clefType, List<Integer> keySignatureList, int keySignature) {
        this.clefType = clefType;
        this.left = 360;
        this.sharpDrawable = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.vector_sharp_sign);
        this.flatDrawable = (VectorDrawable) ContextCompat.getDrawable(context, R.drawable.vector_flat_sign);
        this.baseTop = 130f;
        this.scaleFactor = 0.5f;
        this.keySignatureList = keySignatureList;
        this.keySignature = keySignature;
    }

    private float getSharpTop(int position, int clef) {
        // Adjust these values based on your layout requirements
        float clefHeight = (clef == 1) ? (22.5f * 2) : 0f;
        switch (position) {
            case 1:
                return baseTop + 22.5f * 3 + clefHeight;        // Position for sharp at 1
            case 2:
                return baseTop + 22.5f * 2 + clefHeight;   // Position for sharp at 2
            case 3:
                return baseTop + 22.5f * 1 + clefHeight;   // Position for sharp at 3
            case 4:
                return baseTop + clefHeight;   // Position for sharp at 4
            case 5:
                return baseTop - 22.5f * 1 + clefHeight;   // Position for sharp at 5
            case 6:
                return baseTop + 22.5f * 5 + clefHeight;  // Position for sharp at 6
            case 7:
                return baseTop + 22.5f * 4 + clefHeight;  // Position for sharp at 7
            case -1:
                return baseTop + 22.5f * 2 + clefHeight;        // Position for sharp at 1
            case -2:
                return baseTop + 22.5f * 1 + clefHeight;   // Position for sharp at 2
            case -3:
                return baseTop + clefHeight;   // Position for sharp at 3
            case -4:
                return baseTop + 22.5f * 6 + clefHeight;   // Position for sharp at 4
            case -5:
                return baseTop + 22.5f * 5 + clefHeight;   // Position for sharp at 5
            case -6:
                return baseTop + 22.5f * 4 + clefHeight;  // Position for sharp at 6
            case -7:
                return baseTop + 22.5f * 3 + clefHeight;  // Position for sharp at 7
            default:
                return baseTop;
        }
    }

    public void draw(Canvas canvas) {
        float symbolLeft = left;

        for (int i = 0; i < keySignatureList.size(); i++) {
            int position = keySignatureList.get(i);
            float symbolTop = getSharpTop(position, clefType);

            float originalWidth, originalHeight;
            VectorDrawable symbolDrawable;

            if (keySignature > 0) {
                symbolDrawable = sharpDrawable;
            } else {
                symbolDrawable = flatDrawable;
            }

            originalWidth = symbolDrawable.getIntrinsicWidth();
            originalHeight = symbolDrawable.getIntrinsicHeight();

            float symbolRight = symbolLeft + (originalWidth * scaleFactor);
            float symbolBottom = symbolTop + (originalHeight * scaleFactor);
            symbolDrawable.setBounds((int) symbolLeft, (int) symbolTop, (int) symbolRight, (int) symbolBottom);
            symbolDrawable.draw(canvas);

            // Update position for the next symbol
            symbolLeft = symbolRight + 10; // Space between symbols
        }
    }
}
