package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.pianotutorial.constants.GlobalVariables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WhiteKeysDrawer {
    private final Drawable whiteKeyDrawable;
    private final Drawable activeWhiteKeyDrawable;
    private Set<Integer> activeKeyIndices = new HashSet<>();

    public WhiteKeysDrawer(Drawable whiteKeyDrawable, Drawable activeWhiteKeyDrawable) {
        this.whiteKeyDrawable = whiteKeyDrawable;
        this.activeWhiteKeyDrawable = activeWhiteKeyDrawable;
    }

    public void draw(Canvas canvas, int width, int height) {
        float keyWidth = (float) width / GlobalVariables.WHITE_KEYS_COUNT;
        float keyHeight = 160;

        for (int i = 0; i < GlobalVariables.WHITE_KEYS_COUNT; i++) {
            float left = i * keyWidth;
            float top = height - keyHeight;
            float right = left + keyWidth;
            float bottom = height - 10;

            boolean isActive = activeKeyIndices.contains(i);

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
