package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.models.Measure;
import com.example.pianotutorial.models.SongNote;

import java.util.List;

import com.example.pianotutorial.features.components.paints.notepaints.*;

public class NotesAndMeasuresDrawer {
    private static final float MEASURE_WIDTH = 600;
    private Paint measurePaint;
    private Paint staffPaint; // Define staffPaint
    private Paint changedColorPaint; // Define changedColorPaint
    private List<Measure> measures;

    public NotesAndMeasuresDrawer(List<Measure> measures, Paint measurePaint, Paint staffPaint, Paint changedColorPaint) {
        this.measurePaint = measurePaint;
        this.staffPaint = staffPaint; // Initialize staffPaint
        this.changedColorPaint = changedColorPaint; // Initialize changedColorPaint
        this.measures = measures;
    }

    public void draw(Canvas canvas, int width, int height, long startTime) {
        long currentTime = System.currentTimeMillis() - startTime;
        float staffHeight = 400;
        float noteHeadOriginalHeight = 22;
        float measureDuration = 2.0f;

        float currentX = width - (currentTime * 0.6f);

        for (Measure measure : measures) {
            float measureStartX = currentX;
            float measureEndX = measureStartX + MEASURE_WIDTH + MEASURE_WIDTH / 16;

            float topY = staffHeight / 2;
            float bottomY = staffHeight / 2 + 4 * (staffHeight / 8);
            canvas.drawLine(measureEndX, topY, measureEndX, bottomY, measurePaint);

            float notePositionWithinMeasure = 0;

            for (SongNote songNote : measure.getSongNotes()) {
                float noteDuration = songNote.getDuration();
                float xPosition = measureStartX + notePositionWithinMeasure;

                Paint notePaint;
                Path notePath;
                switch ((int) (noteDuration * 4)) {
                    case 1:
                        notePaint = SixteenthNotePaint.create();
                        notePath = SixteenthNotePaint.createPath();
                        notePositionWithinMeasure += MEASURE_WIDTH / 8;
                        break;
                    case 2:
                        notePaint = EighthNotePaint.create();
                        notePath = EighthNotePaint.createPath();
                        notePositionWithinMeasure += MEASURE_WIDTH / 4;
                        break;
                    case 4:
                        notePaint = QuarterNotePaint.create();
                        notePath = QuarterNotePaint.createPath();
                        notePositionWithinMeasure += MEASURE_WIDTH / 2;
                        break;
                    case 8:
                        notePaint = HalfNotePaint.create();
                        notePath = HalfNotePaint.createPath();
                        notePositionWithinMeasure += MEASURE_WIDTH;
                        break;
                    case 16:
                        notePaint = WholeNotePaint.create();
                        notePath = WholeNotePaint.createPath();
                        notePositionWithinMeasure += MEASURE_WIDTH;
                        break;
                    default:
                        notePaint = WholeNotePaint.create();
                        notePath = WholeNotePaint.createPath();
                        notePositionWithinMeasure += MEASURE_WIDTH;
                        break;
                }

                // Change note color if xPosition <= 600
                if (xPosition <= 580) {
                    notePaint = changedColorPaint;
                }

                canvas.save();
                float noteY = convertPitchToY(songNote.getNotePitch(), songNote.getNoteOctave());
                canvas.translate(xPosition, noteY);

                float scaleFactor = (staffHeight / 10) / noteHeadOriginalHeight;
                canvas.scale(scaleFactor, scaleFactor);

                canvas.drawPath(notePath, notePaint);

                if (noteDuration == 0.25) {
                    int noteOctave = songNote.getNoteOctave();
                    String notePitch = songNote.getNotePitch();

                    if ((noteOctave > 4 || (noteOctave == 4 && notePitch.equals("B"))) && !(noteOctave == 5 && (notePitch.equals("A") || notePitch.equals("B")))) {
                        notePaint = SixteenthNotePaintReverseWhiteSpace.create();
                        notePath = SixteenthNotePaintReverseWhiteSpace.createPath();
                        canvas.drawPath(notePath, notePaint);
                    } else {
                        notePaint = SixteenthNotePaintWhiteSpace.create();
                        notePath = SixteenthNotePaintWhiteSpace.createPath();
                        canvas.drawPath(notePath, notePaint);
                    }
                }

                canvas.restore();

                drawLedgerLines(canvas, xPosition, noteY, songNote.getNotePitch(), songNote.getNoteOctave());
            }
            currentX += MEASURE_WIDTH;
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
                return -1;
            case "B":
                return -2;
            default:
                return 5;
        }
    }

    private int calculateLedgerLines(String pitch, int octave) {
        int ledgerLines = 0;
        // Determine the position based on pitch and octave
        if (octave < 4 || (octave == 4 && "C".equals(pitch)) || (octave == 4 && "B".equals(pitch)) || (octave == 4 && "A".equals(pitch))) {
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
        } else if (octave > 5) {
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
        float staffHeight = (float) GlobalVariables.FIXED_HEIGHT;
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

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    private float convertPitchToY(String pitch, int octave) {
        // Determine base height based on the pitch and octave
        float baseHeight = 287f; // D4
        float noteSpacing = 25;

        // Calculate PitchValue
        int pitchValue = pitchValue(pitch);

        // Calculate height based on pitchValue and Octave
        return baseHeight - pitchValue * noteSpacing - (octave - 4) * 7 * noteSpacing;
    }
}
