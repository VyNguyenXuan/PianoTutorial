package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.pianotutorial.constants.GlobalVariables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlackKeysDrawer {
    private final Drawable blackKeyDrawable;
    private final Drawable activeBlackKeyDrawable;
    private Set<Integer> activeKeyIndices = new HashSet<>();
    private static final int[] BLACK_KEYS_POSITIONS = {0, 1, 3, 4, 5};

    public BlackKeysDrawer(Drawable blackKeyDrawable, Drawable activeBlackKeyDrawable) {
        this.blackKeyDrawable = blackKeyDrawable;
        this.activeBlackKeyDrawable = activeBlackKeyDrawable;
    }

    public void draw(Canvas canvas, int width, int height) {
        float whiteKeyWidth = (float) width / GlobalVariables.WHITE_KEYS_COUNT;
        float blackKeyWidth = whiteKeyWidth / 2;
        float blackKeyHeight = 100;

        for (int i = 0; i < GlobalVariables.WHITE_KEYS_COUNT; i++) {
            if (isBlackKey(i)) {
                float left = i * whiteKeyWidth + whiteKeyWidth - blackKeyWidth / 2;
                float top = height - 160;
                float right = left + blackKeyWidth;
                float bottom = top + blackKeyHeight;

                boolean isActive = activeKeyIndices.contains(i);

                // Set the bounds for the Drawable
                if (isActive) {
                    activeBlackKeyDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                    activeBlackKeyDrawable.draw(canvas);
                } else {
                    blackKeyDrawable.setBounds((int) left, (int) top, (int) right, (int) bottom);
                    blackKeyDrawable.draw(canvas);
                }
            }
        }
    }

    private boolean isBlackKey(int position) {
        int relativePosition = position % 7;

        for (int blackKeyPosition : BLACK_KEYS_POSITIONS) {
            if (relativePosition == blackKeyPosition) {
                return true;
            }
        }

        return false;
    }

    public void setActiveKeyIndices(List<Integer> indices) {
        activeKeyIndices.clear();
        if (indices != null) {
            activeKeyIndices.addAll(indices);
        }
    }

    public void setActiveKeyIndex(int index, boolean isActive) {
        if (isActive) {
            activeKeyIndices.add(index);
        } else {
            activeKeyIndices.remove(index);
        }
    }
}
