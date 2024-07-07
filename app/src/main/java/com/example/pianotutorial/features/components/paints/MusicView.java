package com.example.pianotutorial.features.components.paints;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.pianotutorial.features.components.paints.models.MeasurePaint;
import com.example.pianotutorial.features.components.paints.models.Note;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaintWhiteSpace;
import com.example.pianotutorial.features.components.paints.notepaints.WholeNotePaint;

import java.util.ArrayList;
import java.util.List;

public class MusicView extends View {
    private Paint staffPaint;
    private Paint measurePaint; // Paint object for drawing measure lines
    private List<Note> notes;
    private List<MeasurePaint> measures;
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

        notes = new ArrayList<>();
        measures = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawStaff(canvas);
        drawNotes(canvas);
        drawMeasureLines(canvas); // Draw measure lines
    }

    private void drawStaff(Canvas canvas) {
        float staffHeight = getHeight() / 2;
        float lineSpacing = staffHeight / 8;

        for (int i = 0; i < 5; i++) {
            float y = staffHeight / 2 + i * lineSpacing;
            canvas.drawLine(0, y, getWidth(), y, staffPaint);
        }
    }

    private void drawNotes(Canvas canvas) {
        long currentTime = System.currentTimeMillis() - startTime;
        float staffHeight = getHeight() / 2;
        float noteHeadOriginalHeight = 22; // Height of the original circular part in the vector (as part of path height)
        float noteOriginalHeight = 180; // Original height of the entire vector path
        float noteOriginalWidth = 86; // Original width of the vector path

        for (Note note : notes) {
            if (currentTime >= note.getTime()) {
                float elapsedTime = currentTime - note.getTime();
                float xPosition = note.getInitialX() - elapsedTime * note.getSpeed();

                // Save the canvas state
                canvas.save();

                // Translate the canvas to the note position
                canvas.translate(xPosition, note.getY());

                // Calculate the scale factor to fit the note head within one-fifth of the staff height
                float scaleFactor = (staffHeight / 10) / noteHeadOriginalHeight; // Half the current factor
                canvas.scale(scaleFactor, scaleFactor);

                // Draw the note path using the black paint object
                Paint blackPaint = WholeNotePaint.create();
                Paint whitePaint = SixteenthNotePaintWhiteSpace.create();
                Path blackPath = WholeNotePaint.createPath();
                Path whitePath = SixteenthNotePaintWhiteSpace.createPath();

                // Draw the black part of the note path
                canvas.drawPath(blackPath, blackPaint);

                // Draw the white part of the note path
                //canvas.drawPath(whitePath, whitePaint);

                // Restore the canvas state
                canvas.restore();
            }
        }
    }

    private void drawMeasureLines(Canvas canvas) {
        long currentTime = System.currentTimeMillis() - startTime;
        float staffHeight = getHeight() / 2;

        for (MeasurePaint measure : measures) {
            List<Note> measureNotes = measure.getNotes();
            if (!measureNotes.isEmpty()) {
                // Get the last note of the measure to draw the measure line
                Note lastNote = measureNotes.get(measureNotes.size() - 1);
                if (currentTime >= lastNote.getTime()) {
                    float elapsedTime = currentTime - lastNote.getTime();
                    float xPosition = lastNote.getInitialX() - elapsedTime * lastNote.getSpeed();
                    float barXPosition = xPosition;

                    // Ensure the bar line stays within the staff bounds
                    float topY = staffHeight / 2; // Top line

                    // Draw the measure line, ensuring it does not exceed the staff bounds
                    canvas.drawLine(barXPosition, topY, barXPosition, staffHeight / 2 + 4 * (staffHeight / 8), measurePaint);
                }
            }
        }
    }

    public void addNoteToMeasure(int measureId, Note note) {
        for (MeasurePaint measure : measures) {
            if (measure.getId() == measureId) {
                measure.addNote(note);
                notes.add(note);
                break;
            }
        }
    }

    public void addMeasure(MeasurePaint measure) {
        measures.add(measure);
    }

    public void startDrawing(long startTime) {
        this.startTime = startTime;
        invalidate();
    }

    public void updateView() {
        invalidate();
    }
}