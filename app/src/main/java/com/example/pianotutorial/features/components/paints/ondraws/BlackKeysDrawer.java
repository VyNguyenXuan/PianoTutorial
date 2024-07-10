package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.pianotutorial.constants.GlobalVariables;

public class BlackKeysDrawer {
    private Drawable blackKeyDrawable;
    private static final int[] BLACK_KEYS_POSITIONS = {0, 1, 3, 4, 5};


    public BlackKeysDrawer(Drawable blackKeyDrawable) {
        this.blackKeyDrawable = blackKeyDrawable;
    }

    public void draw(Canvas canvas, int width, int height) {
        float whiteKeyWidth = (float) width / GlobalVariables.WHITE_KEYS_COUNT;
        float blackKeyWidth = whiteKeyWidth / 2;
        float blackKeyHeight = 120;

        for (int i = 0; i < GlobalVariables.WHITE_KEYS_COUNT; i++) {
            if (isBlackKey(i)) {
                float left = i * whiteKeyWidth + whiteKeyWidth - blackKeyWidth / 2;
                float top = height - 200;
                float right = left + blackKeyWidth;
                float bottom = top + blackKeyHeight;

                // Set the bounds for the Drawable
                blackKeyDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                blackKeyDrawable.draw(canvas);
            }
        }
    }

    private boolean isBlackKey(int position) {
        int octave = position / 7;
        int relativePosition = position % 7;

        for (int blackKeyPosition : BLACK_KEYS_POSITIONS) {
            if (relativePosition == blackKeyPosition) {
                return true;
            }
        }

        return false;
    }

}
