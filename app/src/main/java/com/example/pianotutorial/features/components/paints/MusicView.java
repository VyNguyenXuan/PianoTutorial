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
import com.example.pianotutorial.features.components.paints.notepaints.HalfNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.QuarterNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaint;
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

        float currentX = getWidth() - (currentTime * 0.1f); // Adjust currentX based on elapsed time

        for (Measure measure : measures) {
            float measureStartX = currentX;
            float measureEndX = measureStartX + MEASURE_WIDTH;

            // Draw the measure lines
            float topY = staffHeight / 2; // Top line
            float bottomY = staffHeight / 2 + 4 * (staffHeight / 8); // Slightly below the bottom line
            canvas.drawLine(measureEndX, topY, measureEndX, bottomY, measurePaint);

            for (SongNote songNote : measure.getSongNotes()) {
                float noteDuration = songNote.getDuration();
                float segmentWidth = MEASURE_WIDTH / measureDuration;

                // Adjust the x-position based on the note's position within the measure
                float notePositionWithinMeasure = songNote.getPosition() * segmentWidth;
                float xPosition = measureStartX + notePositionWithinMeasure;

                // Select the appropriate paint and path based on note duration
                Paint notePaint;
                Path notePath;
                switch ((int) (noteDuration * 4)) {
                    case 1: // 0.25 duration
                        notePaint = SixteenthNotePaint.create();
                        notePath = SixteenthNotePaint.createPath();
                        break;
                    case 2: // 0.5 duration
                        notePaint = EighthNotePaint.create();
                        notePath = EighthNotePaint.createPath();
                        break;
                    case 4: // 1 duration
                        notePaint = QuarterNotePaint.create();
                        notePath = QuarterNotePaint.createPath();
                        break;
                    case 8: // 2 duration
                        notePaint = HalfNotePaint.create();
                        notePath = HalfNotePaint.createPath();
                        break;
                    case 16: // 4 duration
                        notePaint = WholeNotePaint.create();
                        notePath = WholeNotePaint.createPath();
                        break;
                    default:
                        notePaint = WholeNotePaint.create(); // Default to Whole Note
                        notePath = WholeNotePaint.createPath();
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
                if(noteDuration==0.25){
                    notePaint = SixteenthNotePaintWhiteSpace.create();
                    notePath = SixteenthNotePaintWhiteSpace.createPath();
                    canvas.drawPath(notePath, notePaint);

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
        // Conversion logic based on pitch and octave to y coordinate on the staff
        // This is a simple example and may need to be adjusted based on your specific requirements
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
                pitchValue = 0;
                break;
        }
        return FIXED_HEIGHT - (octave * 7 + pitchValue) * (FIXED_HEIGHT / 56); // Simplified example
    }

    public void startDrawing(long startTime) {
        this.startTime = startTime;
        invalidate();
    }

    public void updateView() {
        invalidate();
    }
}
