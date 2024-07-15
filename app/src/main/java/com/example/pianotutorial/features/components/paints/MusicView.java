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
import com.example.pianotutorial.features.components.helpers.MusicUtils;
import com.example.pianotutorial.features.components.paints.ondraws.BlackKeysDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.GlefDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.LeftLineDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.NotesAndMeasuresDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.StaffDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.WhiteKeysDrawer;
import com.example.pianotutorial.models.Chord;
import com.example.pianotutorial.models.ChordNote;
import com.example.pianotutorial.models.Measure;

import java.util.ArrayList;
import java.util.List;

public class MusicView extends View {
    private Paint staffPaint;
    private Paint measurePaint;
    private Paint changedColorPaint; // Define changedColorPaint
    private List<Measure> measures;
    private long startTime;
    private long pauseTime;

    private StaffDrawer staffDrawer;
    private NotesAndMeasuresDrawer notesAndMeasuresDrawer;
    private WhiteKeysDrawer whiteKeysDrawer;
    private BlackKeysDrawer blackKeysDrawer;

    private LeftLineDrawer leftLineDrawer; // New instance of LeftLineDrawer
    private GlefDrawer gClefDrawer; // New instance of ClefDrawer
    private boolean isPaused; // Add this flag

    public MusicView(Context context) {
        super(context);
        init(context);
    }

    public MusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        staffPaint = new Paint();
        staffPaint.setColor(Color.BLACK);
        staffPaint.setStrokeWidth(5);

        measurePaint = new Paint();
        measurePaint.setColor(Color.BLACK);
        measurePaint.setStrokeWidth(5);

        changedColorPaint = new Paint(); // Initialize changedColorPaint
        changedColorPaint.setColor(Color.BLUE); // Set the color to blue or any color you prefer
        changedColorPaint.setStrokeWidth(5);

        // Define textPaint for drawing note names
        Paint textPaint = new Paint(); // Initialize textPaint
        textPaint.setColor(Color.RED); // Set the text color to red or any color you prefer
        textPaint.setTextSize(50); // Set the text size
        textPaint.setTextAlign(Paint.Align.CENTER);

        Drawable whiteKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_white_button);
        Drawable activeWhiteKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_white_button_active);
        Drawable blackKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_black_button);

        measures = new ArrayList<>();

        staffDrawer = new StaffDrawer(staffPaint);
        notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(measures, measurePaint, staffPaint, changedColorPaint, this); // Pass MusicView to NotesAndMeasuresDrawer
        whiteKeysDrawer = new WhiteKeysDrawer(whiteKeyDrawable, activeWhiteKeyDrawable);
        blackKeysDrawer = new BlackKeysDrawer(blackKeyDrawable);

        leftLineDrawer = new LeftLineDrawer(); // Initialize LeftLineDrawer
        gClefDrawer = new GlefDrawer(context); // Initialize GlefDrawer
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // Draw the left line using LeftLineDrawer
        float leftLineX = 640; // Adjust this value to change the position of the line
        float topY = 0;
        float bottomY = getHeight() - 200;
        leftLineDrawer.draw(canvas, leftLineX, topY, bottomY);

        // Draw the G-clef using GlefDrawer
        gClefDrawer.draw(canvas);

        // Draw other components
        staffDrawer.draw(canvas, getWidth());
        if (!isPaused) {
            notesAndMeasuresDrawer.draw(canvas, getWidth(), startTime);
            invalidate(); // Force a redraw to animate the notes
        } else {
            notesAndMeasuresDrawer.draw(canvas, getWidth(), pauseTime); // Draw with pauseTime to keep the notes at the same position
        }
        whiteKeysDrawer.draw(canvas, getWidth(), getHeight());
        blackKeysDrawer.draw(canvas, getWidth(), getHeight());
    }

    public void setMeasures(List<Measure> measures) {
        if (measures != null) {
            this.measures = measures;
            notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(measures, measurePaint, staffPaint, changedColorPaint, this); // Pass MusicView to NotesAndMeasuresDrawer
        }
    }

    public void startDrawing(long startTime) {
        this.startTime = startTime;
        invalidate();
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        if (paused) {
            this.isPaused = true;
            this.pauseTime = System.currentTimeMillis() - startTime;
        } else {
            this.isPaused = false;
            this.startTime = System.currentTimeMillis() - pauseTime;
            invalidate();
        }
    }

    public void updateView() {
        invalidate();
    }

    public void saveNoteValue(Chord chord) {
        // List to store notes that passed CHECK_LINE_X

        List<Integer> noteIndices = new ArrayList<>();
        if (chord.getChordNotes() != null) {
            for (ChordNote note : chord.getChordNotes()) {
                int notePitchIndex = MusicUtils.pitchValue(note.getNotePitch());
                if (notePitchIndex != 5) {
                    int noteIndex = (note.getNoteOctave() - 2) * 7 + notePitchIndex;
                    noteIndices.add(noteIndex);
                }
            }
            whiteKeysDrawer.setActiveKeyIndices(noteIndices);
            invalidate(); // Request a redraw to show the new notes
        }
    }
}
