package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.features.components.helpers.MusicUtils;
import com.example.pianotutorial.features.components.paints.MusicView;
import com.example.pianotutorial.features.components.paints.notepaints.EighthNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.EighthNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.FlatSignPaint;
import com.example.pianotutorial.features.components.paints.notepaints.HalfNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.HalfNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.QuarterNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.QuarterNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.SharpSignPaint;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaintReverseWhiteSpace;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaintWhiteSpace;
import com.example.pianotutorial.features.components.paints.notepaints.WholeNotePaint;
import com.example.pianotutorial.models.Chord;
import com.example.pianotutorial.models.ChordNote;
import com.example.pianotutorial.models.Measure;

import java.util.List;

public class NotesAndMeasuresDrawer {
    private final Paint measurePaint;
    private final Paint staffPaint;
    private final Paint changedColorPaint;
    private final List<Measure> measures;
    private final MusicView musicView;

    public NotesAndMeasuresDrawer(List<Measure> measures, Paint measurePaint, Paint staffPaint, Paint changedColorPaint, MusicView musicView) {
        this.measurePaint = measurePaint;
        this.staffPaint = staffPaint;
        staffPaint.setStrokeWidth(4);
        this.changedColorPaint = changedColorPaint;
        changedColorPaint.setStrokeWidth(4);
        this.measures = measures;
        this.musicView = musicView;
    }

    public void draw(Canvas canvas, int width, long startTime) {
        long currentTime = System.currentTimeMillis() - startTime;
        float staffHeight = GlobalVariables.FIXED_HEIGHT;
        float noteHeadOriginalHeight = 22;

        float currentX = 750 - (currentTime * 0.6f * GlobalVariables.SPEED);

        if (measures != null) {
            for (Measure measure : measures) {

                float measureStartX = currentX;
                float measureEndX = measureStartX + GlobalVariables.MEASURE_WIDTH + 12;

                float topY = staffHeight / 2;
                float bottomY = staffHeight / 2 + 4 * (staffHeight / 8);
                canvas.drawLine(measureEndX, topY, measureEndX, bottomY, measurePaint);

                float chordPositionWithinMeasure = 0;

                if (measure.getChords() != null) {
                    for (Chord chord : measure.getChords()) {
                        float noteDuration = chord.getDuration();
                        float xPosition = measureStartX + chordPositionWithinMeasure;

                        chordPositionWithinMeasure += (GlobalVariables.MEASURE_WIDTH / GlobalVariables.TOP_SIGNATURE) * noteDuration;

                        // Change note color and alpha if xPosition <= CHECK_LINE_X
                        if (xPosition <= GlobalVariables.CHECK_LINE_X - 20) {
                            musicView.saveNoteValue(chord); // Notify MusicView to save the note value
                        }

                        if (!chord.getChordNotes().isEmpty()) {
                            for (ChordNote chordNote : chord.getChordNotes()) {
                                canvas.save();
                                float noteY = convertPitchToY(chordNote.getNoteId());
                                canvas.translate(xPosition, noteY);

                                float scaleFactor = (staffHeight / 10) / noteHeadOriginalHeight;
                                canvas.scale(scaleFactor, scaleFactor);

                                Paint notePaint;
                                Path notePath;

                                if (chordNote.getNoteOctave() > 4 && (!chordNote.getNotePitch().equals("5A"))) {
                                    if (noteDuration < 8 && noteDuration >= 4) {
                                        notePaint = WholeNotePaint.create();
                                        notePath = WholeNotePaint.createPath();
                                    } else if (noteDuration < 4 && noteDuration >= 2) {
                                        notePaint = HalfNotePaintReverse.create();
                                        notePath = HalfNotePaintReverse.createPath();
                                    } else if (noteDuration < 2 && noteDuration >= 1) {
                                        notePaint = QuarterNotePaintReverse.create();
                                        notePath = QuarterNotePaintReverse.createPath();
                                    } else if (noteDuration < 1 && noteDuration >= 0.5) {
                                        notePaint = EighthNotePaintReverse.create();
                                        notePath = EighthNotePaintReverse.createPath();
                                    } else {
                                        notePaint = SixteenthNotePaintReverse.create();
                                        notePath = SixteenthNotePaintReverse.createPath();
                                    }
                                } else {
                                    if (noteDuration < 8 && noteDuration >= 4) {
                                        notePaint = WholeNotePaint.create();
                                        notePath = WholeNotePaint.createPath();
                                    } else if (noteDuration < 4 && noteDuration >= 2) {
                                        notePaint = HalfNotePaint.create();
                                        notePath = HalfNotePaint.createPath();
                                    } else if (noteDuration < 2 && noteDuration >= 1) {
                                        notePaint = QuarterNotePaint.create();
                                        notePath = QuarterNotePaint.createPath();
                                    } else if (noteDuration < 1 && noteDuration >= 0.5) {
                                        notePaint = EighthNotePaint.create();
                                        notePath = EighthNotePaint.createPath();
                                    } else {
                                        notePaint = SixteenthNotePaint.create();
                                        notePath = SixteenthNotePaint.createPath();
                                    }
                                }

                                // Change note color and alpha if xPosition <= CHECK_LINE_X
                                Paint currentNotePaint = new Paint(notePaint);
                                if (xPosition <= GlobalVariables.CHECK_LINE_X - 20) {
                                    currentNotePaint.setColor(changedColorPaint.getColor());
                                }
                                if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
                                    currentNotePaint.setAlpha(0);
                                }

                                canvas.drawPath(notePath, currentNotePaint);

                                if (noteDuration == 0.25) {
                                    if (chordNote.getNoteOctave() > 4 || (chordNote.getNoteOctave() == 4 && chordNote.getNotePitch().equals("5B"))) {
                                        notePaint = SixteenthNotePaintReverseWhiteSpace.create();
                                        notePath = SixteenthNotePaintReverseWhiteSpace.createPath();
                                    } else {
                                        notePaint = SixteenthNotePaintWhiteSpace.create();
                                        notePath = SixteenthNotePaintWhiteSpace.createPath();
                                    }
                                    canvas.drawPath(notePath, notePaint);
                                }

                                // Draw chromatic signs (flat or sharp)
                                if (chordNote.getChromatic() == 0) { // Flat sign
                                    Paint flatSignPaint = new Paint(FlatSignPaint.create());
                                    if (xPosition <= GlobalVariables.CHECK_LINE_X - 20) {
                                        flatSignPaint.setColor(changedColorPaint.getColor());
                                    }
                                    if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
                                        flatSignPaint.setAlpha(0);
                                    }
                                    Path flatSignPath = FlatSignPaint.createPath();
                                    drawChromaticSign(canvas, flatSignPaint, flatSignPath, noteHeadOriginalHeight);
                                } else if (chordNote.getChromatic() == 2) { // Sharp sign
                                    Paint sharpSignPaint = new Paint(SharpSignPaint.create());
                                    if (xPosition <= GlobalVariables.CHECK_LINE_X - 20) {
                                        sharpSignPaint.setColor(changedColorPaint.getColor());
                                    }
                                    if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
                                        sharpSignPaint.setAlpha(0);
                                    }
                                    Path sharpSignPath = SharpSignPaint.createPath();
                                    drawChromaticSign(canvas, sharpSignPaint, sharpSignPath, noteHeadOriginalHeight);
                                }

                                // Draw dotted notes
                                int dottedNoteCount = MusicUtils.countDottedNotes(noteDuration);
                                if (dottedNoteCount > 0) {
                                    drawDottedNotes(canvas, currentNotePaint, dottedNoteCount, noteHeadOriginalHeight, chordNote.getNoteId());
                                }

                                canvas.restore();

                                drawLedgerLines(canvas, xPosition, chordNote.getNotePitch(), chordNote.getNoteOctave(), xPosition <= 580 ? changedColorPaint : staffPaint, xPosition);
                            }
                        } else {
                            drawRest(canvas, xPosition, noteDuration, currentTime);
                        }
                    }
                    currentX += GlobalVariables.MEASURE_WIDTH;
                }
            }
        }
    }

    private void drawChromaticSign(Canvas canvas, Paint chromaticPaint, Path chromaticPath, float noteHeadOriginalHeight) {
        float chromaticX = -noteHeadOriginalHeight * 1.5f + 36; // Adjust the x-position as needed
        float chromaticY = 54f;
        canvas.save();
        canvas.translate(chromaticX, chromaticY);
        float scaleFactor = noteHeadOriginalHeight / 24f; // Adjust the scale as needed
        canvas.scale(scaleFactor, scaleFactor);
        canvas.drawPath(chromaticPath, chromaticPaint);
        canvas.restore();
    }

    private void drawDottedNotes(Canvas canvas, Paint notePaint, int dottedNoteCount, float noteHeadOriginalHeight, int noteId) {
        float dotRadius = noteHeadOriginalHeight / 4;
        float dotX = noteHeadOriginalHeight + noteHeadOriginalHeight;
        float dotY = noteHeadOriginalHeight * 4;
        if (MusicUtils.isNoteOnLine(noteId)) {
            dotY -= (float) GlobalVariables.FIXED_HEIGHT / 32; // If isNoteOnLine move up a note
        }
        for (int i = 0; i < dottedNoteCount; i++) {
            canvas.drawCircle(dotX + (i * noteHeadOriginalHeight) + 30, dotY, dotRadius, notePaint);
        }
    }

    private void drawLedgerLines(Canvas canvas, float xPosition, String pitch, int octave, Paint linePaint, float noteXPosition) {
        float staffHeight = GlobalVariables.FIXED_HEIGHT;
        float lineSpacing = staffHeight / 8;
        float topLineY = staffHeight / 2;
        float bottomLineY = topLineY + 4 * lineSpacing;

        int ledgerLineCount = MusicUtils.calculateLedgerLines(pitch, octave);

        for (int i = 0; i < ledgerLineCount; i++) {
            float y = (octave < 4 || (octave == 4 && ("C4".equals(pitch) || "B4".equals(pitch) || "A4".equals(pitch))))
                    ? bottomLineY + (i + 1) * lineSpacing
                    : topLineY - (i + 1) * lineSpacing;

            Paint paintToUse = new Paint(linePaint);
            if (noteXPosition < GlobalVariables.CHECK_LINE_X - 160) {
                paintToUse.setAlpha(0);
            }
            canvas.drawLine(xPosition + 28, y, xPosition +106, y, paintToUse);
        }
    }

    private void drawRest(Canvas canvas, float xPosition, float duration, long currentTime) {
        Drawable restDrawable;
        float yPosition;
        float additionalOffset = 0;

        // Select the appropriate drawable and y-position based on the duration
        if (duration == 4) {
            restDrawable = ResourcesCompat.getDrawable(musicView.getContext().getResources(), R.drawable.vector_whole_rest, null);
            yPosition = 234; // Set appropriate y-position
            additionalOffset = 600; // Add additional left offset
        } else if (duration == 2) {
            restDrawable = ResourcesCompat.getDrawable(musicView.getContext().getResources(), R.drawable.vector_whole_rest, null);
            yPosition = 260; // Set appropriate y-position
            additionalOffset = 300; // Add additional left offset
        } else if (duration == 1) {
            restDrawable = ResourcesCompat.getDrawable(musicView.getContext().getResources(), R.drawable.vector_quarter_rest, null);
            yPosition = 280; // Set appropriate y-position
        } else if (duration == 0.5) {
            restDrawable = ResourcesCompat.getDrawable(musicView.getContext().getResources(), R.drawable.vector_eighth_rest, null);
            yPosition = 282; // Set appropriate y-position
        } else if (duration == 0.25) {
            restDrawable = ResourcesCompat.getDrawable(musicView.getContext().getResources(), R.drawable.vector_sixteenth_rest, null);
            yPosition = 290; // Set appropriate y-position
        } else {
            return; // Invalid duration for rest
        }

        int intrinsicWidth = restDrawable != null ? restDrawable.getIntrinsicWidth() : 0;
        int intrinsicHeight = restDrawable != null ? restDrawable.getIntrinsicHeight() : 0;

        float top = yPosition - (float) intrinsicHeight / 2;
        float right = xPosition + intrinsicWidth + additionalOffset;
        float bottom = top + intrinsicHeight;

        if (restDrawable != null) {
            restDrawable.setBounds((int) (xPosition + additionalOffset), (int) top, (int) right, (int) bottom);
        }

        if (restDrawable != null) {
            restDrawable.draw(canvas);
        }
    }

    private float convertPitchToY(int noteId) {
        float baseHeight = GlobalVariables.C4_CURRENT_NOTE; // D4
        int c4Id = 22;
        int spacingValue = noteId - c4Id;
        float noteSpacing = (float) GlobalVariables.FIXED_HEIGHT / 16;
        return baseHeight - spacingValue * noteSpacing;
    }
}
