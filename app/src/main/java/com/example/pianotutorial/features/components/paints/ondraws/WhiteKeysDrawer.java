package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.pianotutorial.constants.GlobalVariables;

public class WhiteKeysDrawer {
    private Drawable whiteKeyDrawable;

    public WhiteKeysDrawer(Drawable whiteKeyDrawable) {
        this.whiteKeyDrawable = whiteKeyDrawable;
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
            whiteKeyDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
            whiteKeyDrawable.draw(canvas);
        }
    }
}
