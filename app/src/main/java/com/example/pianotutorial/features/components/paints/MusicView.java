package com.example.pianotutorial.features.components.paints;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.pianotutorial.features.components.paints.notepaints.EighthNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.EighthNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.HalfNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.HalfNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.QuarterNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.QuarterNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaintReverseWhiteSpace;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaintWhiteSpace;
import com.example.pianotutorial.features.components.paints.notepaints.WholeNotePaint;
import com.example.pianotutorial.models.Measure;
import com.example.pianotutorial.models.SongNote;

import java.util.ArrayList;
import java.util.List;

public class MusicView extends View {
    private static final int FIXED_HEIGHT = 400;
    private static final float MEASURE_WIDTH = 600; // Fixed width for each measure
    private Paint staffPaint;
    private Paint measurePaint; // Paint object for drawing measure lines
    private List<Measure> measures;
    private long startTime;

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

        measures = new ArrayList<>();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        drawStaff(canvas);
        drawNotesAndMeasures(canvas);
    }

    private void drawStaff(Canvas canvas) {
        float staffHeight = (float) FIXED_HEIGHT;
        float lineSpacing = staffHeight / 8;

        for (int i = 0; i < 5; i++) {
            float y = staffHeight / 2 + i * lineSpacing;
            canvas.drawLine(0, y, getWidth(), y, staffPaint);
        }
    }

    private void drawNotesAndMeasures(Canvas canvas) {
        long currentTime = System.currentTimeMillis() - startTime;
        float staffHeight = (float) FIXED_HEIGHT;
        float noteHeadOriginalHeight = 22; // Height of the original circular part in the vector (as part of path height)
        float measureDuration = 2.0f; // Duration of each measure

        // Increase the speed by changing the multiplier from 0.1f to 0.3f
        float currentX = getWidth() - (currentTime * 0.4f); // Adjust currentX based on elapsed time

        for (Measure measure : measures) {
            float measureStartX = currentX;
            float measureEndX = measureStartX + MEASURE_WIDTH+MEASURE_WIDTH/16;

            // Draw the measure lines
            float topY = staffHeight / 2; // Top line
            float bottomY = staffHeight / 2 + 4 * (staffHeight / 8); // Slightly below the bottom line
            canvas.drawLine(measureEndX, topY, measureEndX, bottomY, measurePaint);

            float notePositionWithinMeasure = 0; // To keep track of note position within the measure

            for (SongNote songNote : measure.getSongNotes()) {
                float noteDuration = songNote.getDuration();

                // Adjust the x-position based on the note's position within the measure
                float xPosition = measureStartX + notePositionWithinMeasure;

                // Select the appropriate paint and path based on note duration and pitch
                Paint notePaint;
                Path notePath;
                switch ((int) (noteDuration * 4)) {
                    case 1: // 0.25 duration
                        if (songNote.getNoteOctave() > 4 || (songNote.getNoteOctave() == 4 && songNote.getNotePitch().equals("B"))) {
                            notePaint = SixteenthNotePaintReverse.create();
                            notePath = SixteenthNotePaintReverse.createPath();
                        } else {
                            notePaint = SixteenthNotePaint.create();
                            notePath = SixteenthNotePaint.createPath();
                        }
                        notePositionWithinMeasure += MEASURE_WIDTH / 8; // Move to next position
                        break;
                    case 2: // 0.5 duration
                        if (songNote.getNoteOctave() > 4 || (songNote.getNoteOctave() == 4 && songNote.getNotePitch().equals("B"))) {
                            notePaint = EighthNotePaintReverse.create();
                            notePath = EighthNotePaintReverse.createPath();
                        } else {
                            notePaint = EighthNotePaint.create();
                            notePath = EighthNotePaint.createPath();
                        }
                        notePositionWithinMeasure += MEASURE_WIDTH / 4; // Move to next position
                        break;
                    case 4: // 1 duration
                        if (songNote.getNoteOctave() > 4 || (songNote.getNoteOctave() == 4 && songNote.getNotePitch().equals("B"))) {
                            notePaint = QuarterNotePaintReverse.create();
                            notePath = QuarterNotePaintReverse.createPath();
                        } else {
                            notePaint = QuarterNotePaint.create();
                            notePath = QuarterNotePaint.createPath();
                        }
                        notePositionWithinMeasure += MEASURE_WIDTH / 2; // Move to next position
                        break;
                    case 8: // 2 duration
                        if (songNote.getNoteOctave() > 4 || (songNote.getNoteOctave() == 4 && songNote.getNotePitch().equals("B"))) {
                            notePaint = HalfNotePaintReverse.create();
                            notePath = HalfNotePaintReverse.createPath();
                        } else {
                            notePaint = HalfNotePaint.create();
                            notePath = HalfNotePaint.createPath();
                        }
                        notePositionWithinMeasure += MEASURE_WIDTH; // Move to next position
                        break;
                    case 16: // 4 duration
                        notePaint = WholeNotePaint.create();
                        notePath = WholeNotePaint.createPath();
                        notePositionWithinMeasure += MEASURE_WIDTH; // Move to next position
                        break;
                    default:
                        notePaint = WholeNotePaint.create(); // Default to Whole Note
                        notePath = WholeNotePaint.createPath();
                        notePositionWithinMeasure += MEASURE_WIDTH; // Move to next position
                        break;
                }

                // Save the canvas state
                canvas.save();

                // Translate the canvas to the note position
                float noteY = convertPitchToY(songNote.getNotePitch(), songNote.getNoteOctave());
                canvas.translate(xPosition, noteY);

                // Calculate the scale factor to fit the note head within one-fifth of the staff height
                float scaleFactor = (staffHeight / 10) / noteHeadOriginalHeight; // Half the current factor
                canvas.scale(scaleFactor, scaleFactor);

                // Draw the note path
                canvas.drawPath(notePath, notePaint);
                if (noteDuration == 0.25) {
                    if (songNote.getNoteOctave() > 4 || (songNote.getNoteOctave() == 4 && songNote.getNotePitch().equals("B"))) {
                        notePaint = SixteenthNotePaintReverseWhiteSpace.create();
                        notePath = SixteenthNotePaintReverseWhiteSpace.createPath();
                        canvas.drawPath(notePath, notePaint);
                    } else {
                        notePaint = SixteenthNotePaintWhiteSpace.create();
                        notePath = SixteenthNotePaintWhiteSpace.createPath();
                        canvas.drawPath(notePath, notePaint);
                    }
                }

                // Restore the canvas state
                canvas.restore();

                // Draw ledger lines if the note is outside the D4-G5 range
                drawLedgerLines(canvas, xPosition, noteY, songNote.getNotePitch(), songNote.getNoteOctave());
            }
            currentX += MEASURE_WIDTH;
        }
    }


    private int calculateLedgerLines(String pitch, int octave) {
        int ledgerLines = 0;
        // Determine the position based on pitch and octave
        if (octave < 4 || (octave == 4 && "C".equals(pitch))) {
            switch (pitch) {
                case "C":
                    ledgerLines = 1;
                    break;
                case "B":
                    ledgerLines = 1;
                    break;
                case "A":
                    ledgerLines = 2;
                    break;
                case "G":
                    ledgerLines = 2;
                    break;
                case "F":
                    ledgerLines = 3;
                    break;
                case "E":
                    ledgerLines = 3;
                    break;
                case "D":
                    ledgerLines = 4;
                    break;
                default:
                    ledgerLines = 0;
                    break;
            }
        } else if (octave > 5 || (octave == 5 && "A".equals(pitch)) || (octave == 5 && "B".equals(pitch))) {
            switch (pitch) {
                case "A":
                    ledgerLines = 1;
                    break;
                case "B":
                    ledgerLines = 1;
                    break;
                case "C":
                    ledgerLines = 2;
                    break;
                case "D":
                    ledgerLines = 2;
                    break;
                case "E":
                    ledgerLines = 3;
                    break;
                case "F":
                    ledgerLines = 3;
                    break;
                case "G":
                    ledgerLines = 4;
                    break;
                default:
                    ledgerLines = 0;
                    break;
            }
        }
        return ledgerLines;
    }

    private void drawLedgerLines(Canvas canvas, float xPosition, float noteY, String pitch, int octave) {
        float staffHeight = (float) FIXED_HEIGHT;
        float lineSpacing = staffHeight / 8;
        float topLineY = staffHeight / 2;
        float bottomLineY = topLineY + 4 * lineSpacing;

        int ledgerLineCount = calculateLedgerLines(pitch, octave);

        for (int i = 0; i < ledgerLineCount; i++) {
            float y = (octave < 4 || (octave == 4 && "C".equals(pitch)))
                    ? bottomLineY + (i + 1) * lineSpacing
                    : topLineY - (i + 1) * lineSpacing;
            canvas.drawLine(xPosition + 32, y, xPosition + 120, y, staffPaint);
        }
    }


    private int pitchValue(String pitch) {
        switch (pitch) {
            case "C":
                return 0;
            case "D":
                return 1;
            case "E":
                return 2;
            case "F":
                return 3;
            case "G":
                return 4;
            case "A":
                return 5;
            case "B":
                return 6;
            default:
                return -1;
        }
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    private float convertPitchToY(String pitch, int octave) {
        // Determine base height based on the pitch and octave
        float baseHeight = 287f;
        float noteSpacing = 25;

        // Calculate PitchValue
        int pitchValue = pitchValue(pitch);

        // Calculate height based on pitchValue and Octave
        return baseHeight - pitchValue * noteSpacing - (octave - 4) * 7 * noteSpacing;
    }

    public void startDrawing(long startTime) {
        this.startTime = startTime;
        invalidate();
    }

    public void updateView() {
        invalidate();
    }
}
