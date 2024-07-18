package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

public class LeftLineDrawer {
    private final Paint leftLinePaint;
    private final Paint rightLinePaint;

    public LeftLineDrawer() {
        leftLinePaint = new Paint();

        // Initialize paint for the right line
        rightLinePaint = new Paint();
        rightLinePaint.setColor(0xFF4EFF9E);
        rightLinePaint.setStrokeWidth(16);

        // Apply a blur effect to the right line paint
        rightLinePaint.setMaskFilter(new BlurMaskFilter(4, BlurMaskFilter.Blur.NORMAL));
    }

    public void draw(Canvas canvas, float xPosition, float topY, float bottomY, float width, float cornerRadius) {
        float leftX = xPosition - width / 2;
        float rightX = xPosition + width / 2;

        // Draw the rounded rectangle with gradient
        LinearGradient gradient = new LinearGradient(
                rightX, topY, leftX, topY,
                0xFF4EFF9E, 0x004EFF9E,
                Shader.TileMode.CLAMP
        );
        leftLinePaint.setShader(gradient);
        leftLinePaint.setMaskFilter(new BlurMaskFilter(6, BlurMaskFilter.Blur.NORMAL));


        RectF rect = new RectF(leftX, topY, rightX, bottomY);  // Use full height of topY to rectBottomY
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, leftLinePaint);

        // Draw the line on the right side of the rectangle
        canvas.drawLine(rightX, topY, rightX, bottomY, rightLinePaint);
    }

}
