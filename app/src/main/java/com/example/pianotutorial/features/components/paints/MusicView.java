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
        float currentX = getWidth() - (currentTime * 0.3f); // Adjust currentX based on elapsed time

        for (Measure measure : measures) {
            float measureStartX = currentX;
            float measureEndX = measureStartX + MEASURE_WIDTH;

            // Draw the measure lines
            float topY = staffHeight / 2; // Top line
            float bottomY = staffHeight / 2 + 4 * (staffHeight / 8); // Slightly below the bottom line
            canvas.drawLine(measureEndX, topY, measureEndX, bottomY, measurePaint);

            float notePositionWithinMeasure = 0; // To keep track of note position within the measure

            for (SongNote songNote : measure.getSongNotes()) {
                float noteDuration = songNote.getDuration();
                float segmentWidth = MEASURE_WIDTH / measureDuration;

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
                canvas.translate(xPosition, convertPitchToY(songNote.getNotePitch(), songNote.getNoteOctave()));

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
            }
            currentX += MEASURE_WIDTH;
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
        int pitchValue;
        switch (pitch) {
            case "C":
                pitchValue = 0;
                break;
            case "D":
                pitchValue = 1;
                break;
            case "E":
                pitchValue = 2;
                break;
            case "F":
                pitchValue = 3;
                break;
            case "G":
                pitchValue = 4;
                break;
            case "A":
                pitchValue = 5;
                break;
            case "B":
                pitchValue = 6;
                break;
            default:
                pitchValue = -1;
                break;
        }

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
