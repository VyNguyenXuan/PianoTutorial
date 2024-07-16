package com.example.pianotutorial.features.components.paints.ondraws;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.core.content.res.ResourcesCompat;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.GlobalVariables;

public class StaffDrawer {
    private final Context context;
    private final Paint staffPaint;
    private final Paint textPaint;

    public StaffDrawer(Paint staffPaint, Context context) {
        this.context = context;
        this.staffPaint = staffPaint;
        this.staffPaint.setStrokeWidth(2);

        // Initialize text paint
        textPaint = new Paint();
        textPaint.setColor(0xFF000000);
        textPaint.setTextSize(120);
        Typeface bravura = ResourcesCompat.getFont(context, R.font.times_new_roman_bold);
        textPaint.setTypeface(bravura);
    }

    public void draw(Canvas canvas, int width) {
        float staffHeight = (float) GlobalVariables.FIXED_HEIGHT;
        float lineSpacing = staffHeight / 8;

        for (int i = 0; i < 5; i++) {
            float y = staffHeight / 2 + i * lineSpacing;
            canvas.drawLine(100, y, width, y, staffPaint);
        }

        String top = String.valueOf(GlobalVariables.TOP_SIGNATURE); // Convert int to String
        String bottom = String.valueOf(GlobalVariables.BOTTOM_SIGNATURE); // Convert int to String


        // Example text, you can add more text or adjust the position as needed
        canvas.drawText(top, 280, staffHeight / 2 + lineSpacing * 2, textPaint);
        canvas.drawText(bottom, 280, staffHeight / 2 + lineSpacing * 2 + 90, textPaint);
    }
}
