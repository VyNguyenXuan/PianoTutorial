package com.example.pianotutorial.features.components.paints;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.features.components.helpers.Note;
import com.example.pianotutorial.features.components.helpers.NoteActionListener;
import com.example.pianotutorial.features.components.paints.ondraws.AccoladeDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.BlackKeysDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.LeftClefDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.LeftKeySignatureDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.RightClefDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.LeftLineDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.NotesAndMeasuresDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.RightKeySignatureDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.StaffDrawer;
import com.example.pianotutorial.features.components.paints.ondraws.WhiteKeysDrawer;
import com.example.pianotutorial.models.Measure;
import com.example.pianotutorial.models.Sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicView extends View {
    private Paint staffPaint;
    private Paint measurePaint;
    private Paint changedColorPaintPass;
    private Paint changedColorPaintMiss;
    private Sheet sheet;
    private long startTime;
    private long pauseTime;

    private StaffDrawer staffDrawer;
    private StaffDrawer staffDrawerLeftHand;
    private NotesAndMeasuresDrawer notesAndMeasuresDrawer;
    private NotesAndMeasuresDrawer notesAndMeasuresDrawerLeftHand;
    private WhiteKeysDrawer whiteKeysDrawer;
    private BlackKeysDrawer blackKeysDrawer;
    private LeftLineDrawer leftLineDrawer;
    private RightClefDrawer rightClefDrawer;
    private LeftClefDrawer leftClefDrawer;
    private RightKeySignatureDrawer rightKeySignatureDrawer;
    private LeftKeySignatureDrawer leftKeySignatureDrawer;

    private AccoladeDrawer accoladeDrawer;
    private MediaPlayer player;
    private boolean isPaused;

    // Note name to index mapping
    private final Map<String, Integer> noteToIndexMapWhiteKey = createNoteToIndexMapWhiteKey();
    private final Map<String, Integer> noteToIndexMapBlackKey = createNoteToIndexMapBlackKey();

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

        changedColorPaintPass = new Paint();
        changedColorPaintPass.setColor(Color.GREEN);
        changedColorPaintPass.setStrokeWidth(2); // Adjusted for thinner lines

        changedColorPaintMiss = new Paint();
        changedColorPaintMiss.setColor(Color.RED);
        changedColorPaintMiss.setStrokeWidth(2); // Adjusted for thinner lines

        Paint textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Drawable whiteKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_white_button);
        Drawable activeWhiteKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_white_button_active);
        Drawable blackKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_black_button);
        Drawable activeBlackKeyDrawable = ContextCompat.getDrawable(getContext(), R.drawable.vector_black_button_active);

        List<Measure> measures = new ArrayList<>();
        List<Measure> measuresLeftHand = new ArrayList<>();

        staffDrawer = new StaffDrawer(staffPaint, context);
        staffDrawerLeftHand = new StaffDrawer(staffPaint, context);

        notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(measures, measurePaint, staffPaint, changedColorPaintPass, changedColorPaintMiss, this, player, false, 0);
        notesAndMeasuresDrawerLeftHand = new NotesAndMeasuresDrawer(measuresLeftHand, measurePaint, staffPaint, changedColorPaintPass, changedColorPaintMiss, this, player, true, 0);

        whiteKeysDrawer = new WhiteKeysDrawer(whiteKeyDrawable, activeWhiteKeyDrawable);
        blackKeysDrawer = new BlackKeysDrawer(blackKeyDrawable, activeBlackKeyDrawable);

        leftLineDrawer = new LeftLineDrawer();
        accoladeDrawer = new AccoladeDrawer(context);

        rightClefDrawer = new RightClefDrawer(context, 0);
        leftClefDrawer = new LeftClefDrawer(context, 1);
        List<Integer> keySigntureList = new ArrayList<>();
        rightKeySignatureDrawer = new RightKeySignatureDrawer(context, 0, keySigntureList, 0);
        leftKeySignatureDrawer = new LeftKeySignatureDrawer(context, 1, keySigntureList, 0);

        if (!(context instanceof NoteActionListener)) {
            throw new ClassCastException("Activity must implement NoteActionListener");
        }
    }

    private static Map<String, Integer> createNoteToIndexMapWhiteKey() {
        Map<String, Integer> map = new HashMap<>();
        String[] notes = {"C", "D", "E", "F", "G", "A", "B"};
        int index = 0;
        for (int octave = 2; octave <= 6; octave++) {
            for (String note : notes) {
                map.put(note + octave, index++);
            }
        }
        return map;
    }

    private static Map<String, Integer> createNoteToIndexMapBlackKey() {
        Map<String, Integer> map = new HashMap<>();
        String[] notes = {"C#", "D#", "F#", "G#", "A#"};
        int[] positions = {0, 1, 3, 4, 5};
        for (int octave = 2; octave <= 6; octave++) {
            for (int i = 0; i < notes.length; i++) {
                String note = notes[i];
                int index = positions[i];
                map.put(note.charAt(0) + String.valueOf(octave) + note.charAt(1), index + 7 * (octave - 2));
            }
        }
        return map;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        accoladeDrawer.draw(canvas);
        rightClefDrawer.draw(canvas);
        leftClefDrawer.draw(canvas);
        rightKeySignatureDrawer.draw(canvas);
        leftKeySignatureDrawer.draw(canvas);

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

        // Draw the left line using LeftLineDrawer
        float leftLineX = GlobalVariables.CHECK_LINE_X;
        float topY = 100;
        float bottomY = 800;
        leftLineDrawer.draw(canvas, leftLineX - 80, topY, bottomY, 160, 8);

        whiteKeysDrawer.draw(canvas, getWidth(), getHeight());
        blackKeysDrawer.draw(canvas, getWidth(), getHeight());

        postInvalidate(); // Force a redraw to animate the notes
    }

    public void setMeasures(Sheet sheet) {
        this.sheet = sheet;
        float measureDuration = calculateMeasureDuration(sheet);
        if (sheet.getRightHandMeasures() != null) {
            notesAndMeasuresDrawer = new NotesAndMeasuresDrawer(sheet.getRightHandMeasures(), measurePaint, staffPaint, changedColorPaintPass, changedColorPaintMiss, this, player, false, measureDuration);
        }
        if (sheet.getLeftHandMeasures() != null) {
            notesAndMeasuresDrawerLeftHand = new NotesAndMeasuresDrawer(sheet.getLeftHandMeasures(), measurePaint, staffPaint, changedColorPaintPass, changedColorPaintMiss, this, player, true, measureDuration);
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

    public void updateWhiteKeyIndices(Note note, boolean isActive) {
        Integer noteIndex = noteToIndexMapWhiteKey.get(note.toString());
        if (noteIndex != null) {
            whiteKeysDrawer.setActiveKeyIndex(noteIndex, isActive);
            invalidate();
        }
    }

    public void updateBlackKeyIndices(Note note, boolean isActive) {
        Integer noteIndex = noteToIndexMapBlackKey.get(note.toString());
        if (noteIndex != null) {
            blackKeysDrawer.setActiveKeyIndex(noteIndex, isActive);
            invalidate();
        }
    }

    public NotesAndMeasuresDrawer getNotesAndMeasuresDrawer() {
        return notesAndMeasuresDrawer;
    }

    public NotesAndMeasuresDrawer getNotesAndMeasuresDrawerLeftHand() {
        return notesAndMeasuresDrawerLeftHand;
    }

    public void setMediaPlayer(MediaPlayer player) {
        this.player = player;
        if (notesAndMeasuresDrawer != null) {
            notesAndMeasuresDrawer.setMediaPlayer(player);
        }
        if (notesAndMeasuresDrawerLeftHand != null) {
            notesAndMeasuresDrawerLeftHand.setMediaPlayer(player);
        }
    }

    public void updateRightClefDrawer(int clef) {
        if (sheet == null) return;
        rightClefDrawer = new RightClefDrawer(getContext(), clef);
        rightKeySignatureDrawer = new RightKeySignatureDrawer(getContext(), clef, sheet.getKeySignatureList(sheet.getKeySignature()), sheet.getKeySignature());
        invalidate(); // Force a redraw to reflect the changes
    }

    public void updateLeftClefDrawer(int clef) {
        if (sheet == null) return;
        leftClefDrawer = new LeftClefDrawer(getContext(), clef);
        leftKeySignatureDrawer = new LeftKeySignatureDrawer(getContext(), clef, sheet.getKeySignatureList(sheet.getKeySignature()), sheet.getKeySignature());
        invalidate(); // Force a redraw to reflect the changes
    }

    public float calculateMeasureDuration(Sheet sheet) {
        return ((float) sheet.getTopSignature() / sheet.getBottomSignature()) * 4 * GlobalVariables.MEASURE_WIDTH;
    }
}
