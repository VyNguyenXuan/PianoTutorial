package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.pianotutorial.constants.GlobalVariables;

import java.util.List;

public class WhiteKeysDrawer {
    private final Drawable whiteKeyDrawable;
    private final Drawable activeWhiteKeyDrawable;
    private List<Integer> activeKeyIndices;

    public WhiteKeysDrawer(Drawable whiteKeyDrawable, Drawable activeWhiteKeyDrawable) {
        this.whiteKeyDrawable = whiteKeyDrawable;
        this.activeWhiteKeyDrawable = activeWhiteKeyDrawable;
    }

    public void draw(Canvas canvas, int width, int height) {
        float keyWidth = (float) width / GlobalVariables.WHITE_KEYS_COUNT;
        float keyHeight = 200;

        for (int i = 0; i < GlobalVariables.WHITE_KEYS_COUNT; i++) {
            float left = i * keyWidth;
            float top = height - keyHeight;
            float right = left + keyWidth;
            float bottom = height - 20;

            boolean isActive = activeKeyIndices != null && activeKeyIndices.contains(i);

            // Set the bounds for the Drawable
            if (isActive) {
                activeWhiteKeyDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                activeWhiteKeyDrawable.draw(canvas);
            } else {
                whiteKeyDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                whiteKeyDrawable.draw(canvas);
            }
        }
    }

    public void setActiveKeyIndices(List<Integer> indices) {
        this.activeKeyIndices = indices;
    }
}
