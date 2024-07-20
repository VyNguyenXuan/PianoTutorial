package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.features.components.helpers.MusicUtils;
import com.example.pianotutorial.features.components.helpers.Note;
import com.example.pianotutorial.features.components.paints.MusicView;
import com.example.pianotutorial.features.components.paints.notepaints.*;
import com.example.pianotutorial.models.Chord;
import com.example.pianotutorial.models.ChordNote;
import com.example.pianotutorial.models.Measure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotesAndMeasuresDrawer {
    private final Paint measurePaint;
    private final Paint staffPaint;
    private final Paint changedColorPaintPass;
    private final Paint changedColorPaintMiss;
    private final List<Measure> measures;
    private final MusicView musicView;
    private final List<String> correctNotes;

    private final Map<ChordNote, NoteStatus> noteStatuses;

    public NotesAndMeasuresDrawer(List<Measure> measures, Paint measurePaint, Paint staffPaint, Paint changedColorPaintPass, Paint changedColorPaintMiss, MusicView musicView) {
        this.measurePaint = measurePaint;
        this.staffPaint = initializePaint(staffPaint, 4);
        this.changedColorPaintPass = initializePaint(changedColorPaintPass, 4);
        this.changedColorPaintMiss = initializePaint(changedColorPaintMiss, 4);
        this.measures = measures;
        this.musicView = musicView;
        this.correctNotes = new ArrayList<>(); // Initialize the list here
        this.noteStatuses = new HashMap<>();
    }

    private Paint initializePaint(Paint paint, float strokeWidth) {
        paint.setStrokeWidth(strokeWidth);
        return paint;
    }

    public void setCorrectNoteAction(Note note, boolean isPress) {
        if (note != null) {
            if(isPress){
                this.correctNotes.add(note.toString()); // Add the single note to the list
            }
            else {
                this.correctNotes.remove(note.toString()); // Remove the single note from the list
            }
        }
    }

    private static class NoteStatus {
        boolean isPassed;
        boolean isCorrect;
    }

    public void draw(Canvas canvas, int width, long startTime) {
        long currentTime = System.currentTimeMillis() - startTime;
        float staffHeight = GlobalVariables.FIXED_HEIGHT;
        float noteHeadOriginalHeight = 22;

        float currentX = GlobalVariables.CHECK_LINE_X - (currentTime * 0.6f * GlobalVariables.SPEED);

        if (measures != null) {
            for (Measure measure : measures) {
                drawMeasure(canvas, measure, currentX, staffHeight, noteHeadOriginalHeight);
                currentX += GlobalVariables.MEASURE_WIDTH;
            }
        }
    }

    private void drawMeasure(Canvas canvas, Measure measure, float measureStartX, float staffHeight, float noteHeadOriginalHeight) {
        float measureEndX = measureStartX + GlobalVariables.MEASURE_WIDTH + 12;
        drawMeasureLine(canvas, measureEndX, staffHeight, measureStartX);

        float chordPositionWithinMeasure = 0;
        if (measure.getChords() != null) {
            for (Chord chord : measure.getChords()) {
                float noteDuration = chord.getDuration();
                float xPosition = measureStartX + chordPositionWithinMeasure;

                chordPositionWithinMeasure += (GlobalVariables.MEASURE_WIDTH / GlobalVariables.TOP_SIGNATURE) * noteDuration;
                drawChord(canvas, chord, xPosition, measure, staffHeight, noteHeadOriginalHeight, noteDuration);
            }
        }
    }

    private void drawMeasureLine(Canvas canvas, float measureEndX, float staffHeight, float measureStartX) {
        float topY = staffHeight / 2;
        float bottomY = staffHeight / 2 + 4 * (staffHeight / 8);
        Paint measureLinePaint = new Paint(measurePaint);

        // Check if the measure line has crossed the measureEndX
        if (measureEndX < GlobalVariables.CHECK_LINE_X - 160) {
            measureLinePaint.setAlpha(0);
        }

        canvas.drawLine(measureEndX, topY, measureEndX, bottomY, measureLinePaint);
    }

    private void drawChord(Canvas canvas, Chord chord, float xPosition, Measure measure, float staffHeight, float noteHeadOriginalHeight, float noteDuration) {
        if (!chord.getChordNotes().isEmpty()) {
            for (ChordNote chordNote : chord.getChordNotes()) {
                drawNote(canvas, chord, chordNote, xPosition, measure, staffHeight, noteHeadOriginalHeight, noteDuration);
            }
        } else {
            MusicUtils.drawRest(canvas, xPosition, noteDuration, System.currentTimeMillis(), musicView);
        }
    }

    private void drawNote(Canvas canvas, Chord chord, ChordNote chordNote, float xPosition, Measure measure, float staffHeight, float noteHeadOriginalHeight, float noteDuration) {
        int currentNote = chordNote.getNoteId();
        if (currentNote > 112) currentNote -= 112;
        else if (currentNote > 56) currentNote -= 56;

        canvas.save();
        float noteY = MusicUtils.convertPitchToY(currentNote, measure.getClef());
        canvas.translate(xPosition, noteY);

        float scaleFactor = (staffHeight / 10) / noteHeadOriginalHeight;
        canvas.scale(scaleFactor, scaleFactor);

        Paint notePaint = MusicUtils.getNotePaint(measure, currentNote, noteDuration);
        Path notePath = MusicUtils.getNotePath(measure, currentNote, noteDuration);

        NoteStatus noteStatus = noteStatuses.computeIfAbsent(chordNote, k -> new NoteStatus());
        Paint currentNotePaint = new Paint(notePaint);

        updateNoteStatus(chord, chordNote, xPosition, measure, currentNote, noteStatus, currentNotePaint);

        if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
            currentNotePaint.setAlpha(0);
        }

        canvas.drawPath(notePath, currentNotePaint);

        if (noteDuration == 0.25) {
            drawSixteenthNoteWhiteSpace(canvas, chordNote, noteHeadOriginalHeight);
        }

        drawChromaticSign(canvas, chordNote, xPosition, measure, noteStatus, noteHeadOriginalHeight);
        drawDottedNotes(canvas, currentNotePaint, noteDuration, noteHeadOriginalHeight, chordNote.getNoteId());

        canvas.restore();

        MusicUtils.drawLedgerLines(canvas, xPosition, chordNote, measure.getClef(), staffPaint, changedColorPaintPass, changedColorPaintMiss, GlobalVariables.CHECK_LINE_X - 40, noteStatus.isPassed, noteStatus.isCorrect);
    }

    private void updateNoteStatus(Chord chord, ChordNote chordNote, float xPosition, Measure measure, int currentNote, NoteStatus noteStatus, Paint currentNotePaint) {
        float checkLineX = getCheckLineX(measure, currentNote, chordNote);

        if (xPosition <= checkLineX && !noteStatus.isPassed) {
            noteStatus.isPassed = true;
            noteStatus.isCorrect = correctNotes != null && correctNotes.contains(chordNote.getNotePitch());
        }

        if (noteStatus.isPassed) {
            currentNotePaint.setColor(noteStatus.isCorrect ? changedColorPaintPass.getColor() : changedColorPaintMiss.getColor());
            // Update the color of all notes in the chord if the chord is correct
            if (noteStatus.isCorrect) {
                for (ChordNote cn : chord.getChordNotes()) {
                    NoteStatus ns = noteStatuses.get(cn);
                    if (ns != null) {
                        ns.isCorrect = true;
                    }
                }
            }
        }
    }

    private float getCheckLineX(Measure measure, int currentNote, ChordNote chordNote) {
        boolean isChromatic = chordNote.getNoteId() > 65 && chordNote.getNoteId() < 169 || chordNote.getNoteId() > 112;
        float checkLineX = GlobalVariables.CHECK_LINE_X - (isChromatic ? 64 : 40);

        if (!isChromatic) {
            if (measure.getClef() == 0 && MusicUtils.calculateLedgerLinesGClef(currentNote) > 0) {
                checkLineX = GlobalVariables.CHECK_LINE_X - 64;
            } else if (measure.getClef() != 0 && MusicUtils.calculateLedgerLinesFClef(currentNote) > 0) {
                checkLineX = GlobalVariables.CHECK_LINE_X - 64;
            }
        }

        return checkLineX;
    }

    private void drawSixteenthNoteWhiteSpace(Canvas canvas, ChordNote chordNote, float noteHeadOriginalHeight) {
        Paint notePaint;
        Path notePath;
        if (chordNote.getNoteOctave() > 4 || (chordNote.getNoteOctave() == 4 && chordNote.getNotePitch().equals("5B"))) {
            notePaint = SixteenthNotePaintReverseWhiteSpace.create();
            notePath = SixteenthNotePaintReverseWhiteSpace.createPath();
        } else {
            notePaint = SixteenthNotePaintWhiteSpace.create();
            notePath = SixteenthNotePaintWhiteSpace.createPath();
        }
        canvas.drawPath(notePath, notePaint);
    }

    private void drawChromaticSign(Canvas canvas, ChordNote chordNote, float xPosition, Measure measure, NoteStatus noteStatus, float noteHeadOriginalHeight) {
        Paint chromaticSignPaint;
        Path chromaticSignPath;
        if (chordNote.getNoteId() > 65 && chordNote.getNoteId() < 113) { // Flat sign
            chromaticSignPaint = new Paint(FlatSignPaint.create());
            chromaticSignPath = FlatSignPaint.createPath();
        } else if (chordNote.getNoteId() > 112) { // Sharp sign
            chromaticSignPaint = new Paint(SharpSignPaint.create());
            chromaticSignPath = SharpSignPaint.createPath();
        } else {
            return; // No chromatic sign to draw
        }

        if (noteStatus.isPassed) {
            chromaticSignPaint.setColor(noteStatus.isCorrect ? changedColorPaintPass.getColor() : changedColorPaintMiss.getColor());
        }
        if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
            chromaticSignPaint.setAlpha(0);
        }

        MusicUtils.drawChromaticSign(canvas, chromaticSignPaint, chromaticSignPath, noteHeadOriginalHeight);
    }

    private void drawDottedNotes(Canvas canvas, Paint notePaint, float noteDuration, float noteHeadOriginalHeight, int noteId) {
        int dottedNoteCount = MusicUtils.countDottedNotes(noteDuration);
        if (dottedNoteCount > 0) {
            MusicUtils.drawDottedNotes(canvas, notePaint, dottedNoteCount, noteHeadOriginalHeight, noteId);
        }
    }
}
