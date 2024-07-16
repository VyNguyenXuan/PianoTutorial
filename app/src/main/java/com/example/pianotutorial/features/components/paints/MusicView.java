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
import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.features.components.helpers.MusicUtils;
import com.example.pianotutorial.features.components.paints.ondraws.AccoladeDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.BlackKeysDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.FClefDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.GClefDrawer;
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
    private Paint changedColorPaint;
    private List<Measure> measures;
    private List<Measure> measuresLeftHand;
    private long startTime;
    private long pauseTime;

    private StaffDrawer staffDrawer;
    private StaffDrawer staffDrawerLeftHand;
    private NotesAndMeasuresDrawer notesAndMeasuresDrawer;
    private NotesAndMeasuresDrawer notesAndMeasuresDrawerLeftHand;
    private WhiteKeysDrawer whiteKeysDrawer;
    private BlackKeysDrawer blackKeysDrawer;

    private LeftLineDrawer leftLineDrawer;
    private GClefDrawer gClefDrawer;
    private FClefDrawer fClefDrawer;

    private AccoladeDrawer accoladeDrawer;

    private boolean isPaused;

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
        staffPaint.setStrokeWidth(2); // Adjusted for thinner lines

        measurePaint = new Paint();
        measurePaint.setColor(Color.BLACK);
        measurePaint.setStrokeWidth(2); // Adjusted for thinner lines

        changedColorPaint = new Paint();
        changedColorPaint.setColor(Color.BLUE);
        changedColorPaint.setStrokeWidth(2); // Adjusted for thinner lines

        Paint textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Drawable whiteKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_white_button);
        Drawable activeWhiteKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_white_button_active);
        Drawable blackKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_black_button);

        measures = new ArrayList<>();
        measuresLeftHand = new ArrayList<>();

        staffDrawer = new StaffDrawer(staffPaint,context);
        staffDrawerLeftHand = new StaffDrawer(staffPaint,context);

        notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(measures, measurePaint, staffPaint, changedColorPaint, this);
        notesAndMeasuresDrawerLeftHand = new NotesAndMeasuresDrawer(measuresLeftHand, measurePaint, staffPaint, changedColorPaint, this);

        whiteKeysDrawer = new WhiteKeysDrawer(whiteKeyDrawable, activeWhiteKeyDrawable);
        blackKeysDrawer = new BlackKeysDrawer(blackKeyDrawable);

        leftLineDrawer = new LeftLineDrawer();
        accoladeDrawer = new AccoladeDrawer(context);
        gClefDrawer = new GClefDrawer(context);
        fClefDrawer = new FClefDrawer(context);

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // Draw the left line using LeftLineDrawer
        float leftLineX = GlobalVariables.CHECK_LINE_X;
        float topY = 100;
        float bottomY = 800;
        leftLineDrawer.draw(canvas, leftLineX, topY, bottomY);

        // Draw the G-clef using GlefDrawer
        accoladeDrawer.draw(canvas);
        gClefDrawer.draw(canvas);
        fClefDrawer.draw(canvas);


        // Draw the staff for the right hand
        staffDrawer.draw(canvas, getWidth());
        if (!isPaused) {
            notesAndMeasuresDrawer.draw(canvas, getWidth(), startTime);
        } else {
            notesAndMeasuresDrawer.draw(canvas, getWidth(), pauseTime);
        }

        // Draw the staff for the left hand
        canvas.save();
        canvas.translate(0, 360); // Adjust the translation to position the left hand staff correctly
        staffDrawerLeftHand.draw(canvas, getWidth());
        if (!isPaused) {
            notesAndMeasuresDrawerLeftHand.draw(canvas, getWidth(), startTime);
        } else {
            notesAndMeasuresDrawerLeftHand.draw(canvas, getWidth(), pauseTime);
        }
        canvas.restore();

        whiteKeysDrawer.draw(canvas, getWidth(), getHeight());
        blackKeysDrawer.draw(canvas, getWidth(), getHeight());

        invalidate(); // Force a redraw to animate the notes
    }

    public void setMeasures(List<Measure> measures, List<Measure> measuresLeftHand) {
        if (measures != null) {
            this.measures = measures;
            notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(measures, measurePaint, staffPaint, changedColorPaint, this);
        }
        if (measuresLeftHand != null) {
            this.measuresLeftHand = measuresLeftHand;
            notesAndMeasuresDrawerLeftHand = new NotesAndMeasuresDrawer(measuresLeftHand, measurePaint, staffPaint, changedColorPaint, this);
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
        List<Integer> noteIndices = new ArrayList<>();
        if (chord.getChordNotes() != null) {
            for (ChordNote note : chord.getChordNotes()) {
                int notePitchIndex = MusicUtils.pitchValue(note.getNotePitch());
                if (notePitchIndex != -1) {
                    int noteIndex = (note.getNoteOctave() - 2) * 7 + notePitchIndex;
                    noteIndices.add(noteIndex);
                }
            }
            whiteKeysDrawer.setActiveKeyIndices(noteIndices);
            invalidate();
        }
    }
}
