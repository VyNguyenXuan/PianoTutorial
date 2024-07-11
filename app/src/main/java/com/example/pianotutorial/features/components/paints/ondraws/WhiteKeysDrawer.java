package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.pianotutorial.constants.GlobalVariables;

public class WhiteKeysDrawer {
    private Drawable whiteKeyDrawable;
    private Drawable activeWhiteKeyDrawable;
    private int activeKeyIndex = -1;

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

            // Set the bounds for the Drawable
            if (i == activeKeyIndex) {
                activeWhiteKeyDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                activeWhiteKeyDrawable.draw(canvas);
            } else {
                whiteKeyDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                whiteKeyDrawable.draw(canvas);
            }
        }
    }

    public void setActiveKeyIndex(int index) {
        activeKeyIndex = index;
    }
}
