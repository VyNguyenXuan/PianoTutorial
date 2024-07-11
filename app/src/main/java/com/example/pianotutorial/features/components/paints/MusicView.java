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
import com.example.pianotutorial.features.components.paints.ondraws.BlackKeysDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.GlefDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.LeftLineDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.NotesAndMeasuresDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.StaffDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.WhiteKeysDrawer;
import com.example.pianotutorial.models.Measure;
import com.example.pianotutorial.models.SongNote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicView extends View {
    private Paint staffPaint;
    private Paint measurePaint;
    private Paint changedColorPaint; // Define changedColorPaint
    private Paint textPaint; // Define textPaint for drawing note names
    private Drawable whiteKeyDrawable;
    private Drawable activeWhiteKeyDrawable;
    private Drawable blackKeyDrawable;
    private List<Measure> measures;
    private SongNote passedNote; // List to store notes that passed CHECK_LINE_X
    private long startTime;

    private StaffDrawer staffDrawer;
    private NotesAndMeasuresDrawer notesAndMeasuresDrawer;
    private WhiteKeysDrawer whiteKeysDrawer;
    private BlackKeysDrawer blackKeysDrawer;

    private LeftLineDrawer leftLineDrawer; // New instance of LeftLineDrawer
    private GlefDrawer gClefDrawer; // New instance of GlefDrawer

    private static final Map<String, Integer> NOTE_PITCH_MAP = new HashMap<>();

    static {
        NOTE_PITCH_MAP.put("C", 0);
        NOTE_PITCH_MAP.put("D", 1);
        NOTE_PITCH_MAP.put("E", 2);
        NOTE_PITCH_MAP.put("F", 3);
        NOTE_PITCH_MAP.put("G", 4);
        NOTE_PITCH_MAP.put("A", 5);
        NOTE_PITCH_MAP.put("B", 6);
    }

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

        textPaint = new Paint(); // Initialize textPaint
        textPaint.setColor(Color.RED); // Set the text color to red or any color you prefer
        textPaint.setTextSize(50); // Set the text size
        textPaint.setTextAlign(Paint.Align.CENTER);

        whiteKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_white_button);
        activeWhiteKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_white_button_active);
        blackKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_black_button);

        measures = new ArrayList<>();

        staffDrawer = new StaffDrawer(staffPaint);
        notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(measures, measurePaint, staffPaint, changedColorPaint, this); // Pass MusicView to NotesAndMeasuresDrawer
        whiteKeysDrawer = new WhiteKeysDrawer(whiteKeyDrawable, activeWhiteKeyDrawable);
        blackKeysDrawer = new BlackKeysDrawer(blackKeyDrawable);

        leftLineDrawer = new LeftLineDrawer(); // Initialize LeftLineDrawer
        gClefDrawer = new GlefDrawer(staffPaint, context); // Initialize GlefDrawer
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
        gClefDrawer.draw(canvas, getWidth(), getHeight());

        // Draw other components
        staffDrawer.draw(canvas, getWidth(), getHeight());
        notesAndMeasuresDrawer.draw(canvas, getWidth(), getHeight(), startTime);
        whiteKeysDrawer.draw(canvas, getWidth(), getHeight());
        blackKeysDrawer.draw(canvas, getWidth(), getHeight());

        // Draw the name of the passed note
        if (passedNote != null) {
            float x = leftLineX + 50; // Adjust the x position of the text
            float y = 100; // Adjust the y position of the text
            String noteName = passedNote.getNotePitch() + passedNote.getNoteOctave(); // Get the note name
            canvas.drawText(noteName, x, y, textPaint); // Draw the text
        }

        invalidate(); // Force a redraw to animate the notes
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
        notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(measures, measurePaint, staffPaint, changedColorPaint, this); // Pass MusicView to NotesAndMeasuresDrawer
    }

    public void startDrawing(long startTime) {
        this.startTime = startTime;
        invalidate();
    }

    public void updateView() {
        invalidate();
    }

    public void saveNoteValue(SongNote note) {
        passedNote = note;

        // Calculate the index of the note in the range C2 to B7
        int notePitchIndex = NOTE_PITCH_MAP.get(note.getNotePitch());
        int noteIndex = (note.getNoteOctave() - 2) * 7 + notePitchIndex;
        whiteKeysDrawer.setActiveKeyIndex(noteIndex);

        invalidate(); // Request a redraw to show the new note
    }

    public SongNote getPassedNote() {
        return passedNote;
    }
}
