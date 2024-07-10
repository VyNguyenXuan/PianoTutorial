package com.example.pianotutorial.features.components.paints;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.components.paints.ondraws.BlackKeysDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.LeftLineDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.NotesAndMeasuresDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.StaffDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.WhiteKeysDrawer;
import com.example.pianotutorial.models.Measure;

import java.util.ArrayList;
import java.util.List;

public class MusicView extends View {
    private Paint staffPaint;
    private Paint measurePaint;
    private Paint changedColorPaint; // Define changedColorPaint
    private Drawable whiteKeyDrawable;
    private Drawable blackKeyDrawable;
    private List<Measure> measures;
    private long startTime;

    private StaffDrawer staffDrawer;
    private NotesAndMeasuresDrawer notesAndMeasuresDrawer;
    private WhiteKeysDrawer whiteKeysDrawer;
    private BlackKeysDrawer blackKeysDrawer;

    private LeftLineDrawer leftLineDrawer; // New instance of LeftLineDrawer

    public MusicView(Context context) {
        super(context);
        init();
    }

    public MusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        staffPaint = new Paint();
        staffPaint.setColor(Color.BLACK);
        staffPaint.setStrokeWidth(5);

        measurePaint = new Paint();
        measurePaint.setColor(Color.BLACK);
        measurePaint.setStrokeWidth(5);

        changedColorPaint = new Paint(); // Initialize changedColorPaint
        changedColorPaint.setColor(Color.RED); // Set the color to red or any color you prefer
        changedColorPaint.setStrokeWidth(5);

        whiteKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_white_button);
        blackKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_black_button);

        measures = new ArrayList<>();

        staffDrawer = new StaffDrawer(staffPaint);
        notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(measures, measurePaint, staffPaint, changedColorPaint); // Pass changedColorPaint
        whiteKeysDrawer = new WhiteKeysDrawer(whiteKeyDrawable);
        blackKeysDrawer = new BlackKeysDrawer(blackKeyDrawable);

        leftLineDrawer = new LeftLineDrawer(); // Initialize LeftLineDrawer
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // Draw the left line using LeftLineDrawer
        float leftLineX = 640; // Adjust this value to change the position of the line
        float topY = 0;
        float bottomY = getHeight() - 200;
        leftLineDrawer.draw(canvas, leftLineX, topY, bottomY);

        // Draw other components
        staffDrawer.draw(canvas, getWidth(), getHeight());
        notesAndMeasuresDrawer.draw(canvas, getWidth(), getHeight(), startTime);
        whiteKeysDrawer.draw(canvas, getWidth(), getHeight());
        blackKeysDrawer.draw(canvas, getWidth(), getHeight());

        invalidate(); // Force a redraw to animate the notes
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
        notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(measures, measurePaint, staffPaint, changedColorPaint); // Pass changedColorPaint
    }

    public void startDrawing(long startTime) {
        this.startTime = startTime;
        invalidate();
    }

    public void updateView() {
        invalidate();
    }
}
