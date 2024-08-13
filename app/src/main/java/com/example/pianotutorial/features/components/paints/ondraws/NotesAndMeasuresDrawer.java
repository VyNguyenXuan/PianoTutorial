package com.example.pianotutorial.features.components.paints.ondraws;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;

import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.features.components.helpers.MusicUtils;
import com.example.pianotutorial.features.components.helpers.Note;
import com.example.pianotutorial.features.components.paints.MusicView;
import com.example.pianotutorial.features.components.paints.notepaints.*;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;
import com.example.pianotutorial.models.BeamValue;
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
    private MediaPlayer player;
    private boolean soundPlayed;
    private final boolean isLeftHand;
    private final float measureDuration;

    public NotesAndMeasuresDrawer(List<Measure> measures, Paint measurePaint, Paint staffPaint, Paint changedColorPaintPass, Paint changedColorPaintMiss, MusicView musicView, MediaPlayer player, Boolean isLeftHand, float measureDuration) {
        this.measurePaint = measurePaint;
        this.staffPaint = initializePaint(staffPaint);
        this.changedColorPaintPass = initializePaint(changedColorPaintPass);
        this.changedColorPaintMiss = initializePaint(changedColorPaintMiss);
        this.measures = measures;
        this.musicView = musicView;
        this.correctNotes = new ArrayList<>(); // Initialize the list here
        this.noteStatuses = new HashMap<>();
        this.player = player;
        this.soundPlayed = false;
        this.isLeftHand = isLeftHand;
        this.measureDuration = measureDuration;

        if (!measures.isEmpty()) {
            musicView.updateRightClefDrawer(GlobalVariables.RIGHT_CLEF);
            musicView.updateLeftClefDrawer(GlobalVariables.LEFT_CLEF);

        }
    }

    private Paint initializePaint(Paint paint) {
        paint.setStrokeWidth((float) 4);
        return paint;
    }

    public void setMediaPlayer(MediaPlayer player) {
        this.player = player;
    }

    public void playSound() {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }


    public void setCorrectNoteAction(Note note, boolean isPress) {
        if (note != null) {
            if (isPress) {
                this.correctNotes.add(note.toString()); // Add the single note to the list
            } else {
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

        float currentX = GlobalVariables.CHECK_LINE_X + width - (currentTime * 0.4f * GlobalVariables.SPEED);


        for (int i = 0; i < measures.size(); i++) {
            Measure previousMeasure = null;
            if (i > 0) {
                previousMeasure = measures.get(i - 1);
            }

            drawMeasure(canvas, measures, measures.get(i), previousMeasure, currentX, staffHeight, noteHeadOriginalHeight, measureDuration);
            currentX += measureDuration;
        }
    }

    private void drawMeasure(Canvas canvas, List<Measure> measures, Measure measure, Measure previousMeasure, float measureStartX, float staffHeight, float noteHeadOriginalHeight, float measureDuration) {
        float measureEndX = measureStartX + measureDuration + 12;

        drawMeasureLine(canvas, measureEndX, staffHeight, measureStartX);
        float chordPositionWithinMeasure = 0;
        List<BeamValue> chordsToBeam = measure.groupChordsIntoBeams();
        if (measure.getChords() != null) {
            // Draw any remaining beamed notes
            if (!chordsToBeam.isEmpty()) {
                drawBeamedNotes(canvas, chordsToBeam, measure, measureStartX);
            }
            for (int i = 0; i < measure.getChords().size(); i++) {
                Chord chord = measure.getChords().get(i);
                Chord previousChord = null;
                if (i > 0) {
                    previousChord = measure.getChords().get(i - 1);
                }
                if (previousChord == null && previousMeasure != null) {
                    int size = previousMeasure.getChords().size();
                    previousChord = previousMeasure.getChords().get(size - 1);
                }
                float noteDuration = chord.getDuration();
                float xPosition = measureStartX + chordPositionWithinMeasure;
                chordPositionWithinMeasure += GlobalVariables.MEASURE_WIDTH * noteDuration;

                drawChord(canvas, measures, previousChord, chord, xPosition, staffHeight, noteHeadOriginalHeight, noteDuration, chordsToBeam);
            }
        }
    }


    private void drawBeamedNotes(Canvas canvas, List<BeamValue> chordsToBeam, Measure measure, float measureStartX) {
        if (chordsToBeam.isEmpty()) return;

        float yPosition;
        //Create beamPath
        Path beamPath = new Path();

        for (BeamValue beamValue : chordsToBeam) {
            Chord startValue = beamValue.getStartChord();
            float firstXPosition = measureStartX + GlobalVariables.MEASURE_WIDTH * measure.currentDuration(startValue);
            Chord endValue = beamValue.getEndChord();
            float lastXPosition = measureStartX + GlobalVariables.MEASURE_WIDTH * measure.currentDuration(endValue);

            if (startValue.getDuration() < 1 && startValue.getDuration() >= 0.5f) {
                float translateX = 29.5f;
                if (beamValue.isBeamedNoteStemUp()) {
                    int highestNoteId = beamValue.findHighestBeamNoteId();
                    yPosition = (startValue.getClef() == 0) ? MusicUtils.convertPitchToY(highestNoteId, 0) : MusicUtils.convertPitchToY(highestNoteId, 1);
                    if (startValue.findHighestNoteIdWithoutFlip(true) == endValue.findHighestNoteIdWithoutFlip(true)) {
                        beamPath.moveTo(firstXPosition - 42 + translateX, yPosition + 16);
                        beamPath.lineTo(lastXPosition - 35 + translateX, yPosition + 16);
                        beamPath.lineTo(lastXPosition - 35 + translateX, yPosition - 12);
                        beamPath.lineTo(firstXPosition - 42 + translateX, yPosition - 12);
                        beamPath.close();
                    } else {
                        beamPath.moveTo(firstXPosition - 44 + translateX, yPosition + 16);
                        beamPath.lineTo(lastXPosition - 37 + translateX, yPosition + 16);
                        beamPath.lineTo(lastXPosition - 34 + translateX, yPosition - 12);
                        beamPath.lineTo(firstXPosition - 41 + translateX, yPosition - 12);
                        beamPath.close();
                        Matrix matrix = new Matrix();
                        matrix.setRotate(-8, (firstXPosition + lastXPosition) / 2, yPosition);
                        if (highestNoteId == startValue.findHighestNoteIdWithoutFlip(true)) {
                            matrix.postScale(1, -1, (firstXPosition + lastXPosition) / 2, yPosition);            // Apply the rotation to the beamPath
                            matrix.postTranslate(0, 24);
                        }
                        beamPath.transform(matrix);
                    }

                } else {
                    int lowestNoteId = beamValue.findLowestBeamNoteId();
                    yPosition = (startValue.getClef() == 0) ? MusicUtils.convertPitchToY(lowestNoteId - 13, 0) : MusicUtils.convertPitchToY(lowestNoteId - 13, 1);
                    yPosition += 58;
                    if (startValue.findLowestNoteIdWithoutFlip(false) == endValue.findLowestNoteIdWithoutFlip(false)) {
                        beamPath.moveTo(firstXPosition - 100 + translateX, yPosition - 16);
                        beamPath.lineTo(lastXPosition - 93 + translateX, yPosition - 16);
                        beamPath.lineTo(lastXPosition - 93 + translateX, yPosition + 12);
                        beamPath.lineTo(firstXPosition - 100 + translateX, yPosition + 12);
                        beamPath.close();
                    } else {
                        beamPath.moveTo(firstXPosition - 102 + translateX, yPosition - 16);
                        beamPath.lineTo(lastXPosition - 95 + translateX, yPosition - 16);
                        beamPath.lineTo(lastXPosition - 92 + translateX, yPosition + 12);
                        beamPath.lineTo(firstXPosition - 99 + translateX, yPosition + 12);
                        beamPath.close();
                        Matrix matrix = new Matrix();
                        matrix.setRotate(8, (firstXPosition + lastXPosition) / 2, yPosition);
                        if (lowestNoteId == startValue.findLowestNoteIdWithoutFlip(false)) {
                            matrix.postScale(1, -1, (firstXPosition + lastXPosition) / 2, yPosition);            // Apply the rotation to the beamPath
                            matrix.postTranslate(0, -24);
                        }
                        beamPath.transform(matrix);
                    }
                }
            } else {
                float translateX = 15f;
                if (beamValue.isBeamedNoteStemUp()) {
                    int highestNoteId = beamValue.findHighestBeamNoteId();
                    yPosition = (startValue.getClef() == 0) ? MusicUtils.convertPitchToY(highestNoteId, 0) : MusicUtils.convertPitchToY(highestNoteId, 1);
                    if (startValue.findHighestNoteIdWithoutFlip(true) == endValue.findHighestNoteIdWithoutFlip(true)) {
                        beamPath.moveTo(firstXPosition + 33 + translateX, yPosition + 28);
                        beamPath.lineTo(lastXPosition + 40 + translateX, yPosition + 28);
                        beamPath.lineTo(lastXPosition + 40 + translateX, yPosition + 4);
                        beamPath.lineTo(firstXPosition + 33 + translateX, yPosition + 4);
                        beamPath.close();

                        beamPath.moveTo(firstXPosition + 33 + translateX, yPosition + 64);
                        beamPath.lineTo(lastXPosition + 40 + translateX, yPosition + 64);
                        beamPath.lineTo(lastXPosition + 40 + translateX, yPosition + 40);
                        beamPath.lineTo(firstXPosition + 33 + translateX, yPosition + 40);
                        beamPath.close();
                    } else {
                        beamPath.moveTo(firstXPosition + 25 + translateX, yPosition + 28);
                        beamPath.lineTo(lastXPosition + 34 + translateX, yPosition + 28);
                        beamPath.lineTo(lastXPosition + 41 + translateX, yPosition + 4);
                        beamPath.lineTo(firstXPosition + 32 + translateX, yPosition + 4);
                        beamPath.close();

                        beamPath.moveTo(firstXPosition + 15 + translateX, yPosition + 64);
                        beamPath.lineTo(lastXPosition + 24 + translateX, yPosition + 64);
                        beamPath.lineTo(lastXPosition + 31 + translateX, yPosition + 40);
                        beamPath.lineTo(firstXPosition + 22 + translateX, yPosition + 40);
                        beamPath.close();

                        Matrix matrix = new Matrix();
                        matrix.setRotate(-16, (firstXPosition + lastXPosition) / 2, yPosition);
                        if (highestNoteId == startValue.findHighestNoteIdWithoutFlip(true)) {
                            matrix.postScale(1, -1, (firstXPosition + lastXPosition) / 2, yPosition);            // Apply the rotation to the beamPath
                            matrix.postTranslate(0, 60);
                        }
                        beamPath.transform(matrix);
                    }

                } else {
                    int lowestNoteId = beamValue.findLowestBeamNoteId();
                    yPosition = (startValue.getClef() == 0) ? MusicUtils.convertPitchToY(lowestNoteId - 13, 0) : MusicUtils.convertPitchToY(lowestNoteId - 13, 1);
                    yPosition += 58;
                    if (startValue.findLowestNoteIdWithoutFlip(false) == endValue.findLowestNoteIdWithoutFlip(false)) {
                        beamPath.moveTo(firstXPosition - 24 + translateX, yPosition - 16);
                        beamPath.lineTo(lastXPosition - 17 + translateX, yPosition - 16);
                        beamPath.lineTo(lastXPosition - 17 + translateX, yPosition + 8);
                        beamPath.lineTo(firstXPosition - 24 + translateX, yPosition + 8);
                        beamPath.close();
                        beamPath.moveTo(firstXPosition - 24 + translateX, yPosition - 52);
                        beamPath.lineTo(lastXPosition - 17 + translateX, yPosition - 52);
                        beamPath.lineTo(lastXPosition - 17 + translateX, yPosition - 28);
                        beamPath.lineTo(firstXPosition - 24 + translateX, yPosition - 28);
                        beamPath.close();
                    } else {
                        beamPath.moveTo(firstXPosition - 30 + translateX, yPosition - 16);
                        beamPath.lineTo(lastXPosition - 23 + translateX, yPosition - 16);
                        beamPath.lineTo(lastXPosition - 16 + translateX, yPosition + 8);
                        beamPath.lineTo(firstXPosition - 23 + translateX, yPosition + 8);
                        beamPath.close();
                        beamPath.moveTo(firstXPosition - 40 + translateX, yPosition - 52);
                        beamPath.lineTo(lastXPosition - 33 + translateX, yPosition - 52);
                        beamPath.lineTo(lastXPosition - 26 + translateX, yPosition - 28);
                        beamPath.lineTo(firstXPosition - 33 + translateX, yPosition - 28);
                        beamPath.close();
                        Matrix matrix = new Matrix();
                        matrix.setRotate(16, (firstXPosition + lastXPosition) / 2, yPosition);
                        if (lowestNoteId == startValue.findLowestNoteIdWithoutFlip(false)) {
                            matrix.postScale(1, -1, (firstXPosition + lastXPosition) / 2, yPosition);            // Apply the rotation to the beamPath
                            matrix.postTranslate(0, -60);
                        }
                        beamPath.transform(matrix);
                    }
                }
            }

            Paint beamPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            beamPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            beamPaint.setColor(Color.BLACK);
            beamPaint.setStrokeWidth(1);

            ChordNote chordNote = endValue.getChordNotes().get(0);
            NoteStatus noteStatus = noteStatuses.computeIfAbsent(chordNote, k -> new NoteStatus());

            if (noteStatus.isPassed) {
                beamPaint.setColor(noteStatus.isCorrect ? changedColorPaintPass.getColor() : changedColorPaintMiss.getColor());
                // Update the color of all notes in the chord if the chord is correct
                if (noteStatus.isCorrect) {
                    for (ChordNote cn : endValue.getChordNotes()) {
                        NoteStatus ns = noteStatuses.get(cn);
                        if (ns != null) {
                            ns.isCorrect = true;
                        }
                    }
                }
            }
            if (firstXPosition < GlobalVariables.CHECK_LINE_X - 160) {
                beamPaint.setAlpha(0);
            }

            canvas.drawPath(beamPath, beamPaint);
            beamPath.reset();
        }
    }

    private void drawMeasureLine(Canvas canvas, float measureEndX, float staffHeight, float measureStartX) {
        float topY = staffHeight / 2;
        float bottomY = staffHeight / 2 + 4 * (staffHeight / 8);
        Paint measureLinePaint = new Paint(measurePaint);

        if (!soundPlayed && measureStartX < GlobalVariables.CHECK_LINE_X + 40) {
            playSound();
            soundPlayed = true; // Update the flag to true to prevent future plays
        }

        // Check if the measure line has crossed the measureEndX
        if (measureEndX < GlobalVariables.CHECK_LINE_X - 160) {
            measureLinePaint.setAlpha(0);
        }

        canvas.drawLine(measureEndX, topY, measureEndX, bottomY, measureLinePaint);
    }

    private void drawChord(Canvas canvas, List<Measure> measures, Chord previousChord, Chord chord, float xPosition, float staffHeight, float noteHeadOriginalHeight, float noteDuration, List<BeamValue> beamValues) {
        if (previousChord != null && previousChord.getClef() != chord.getClef()) {
            Path pendingClefPath = (chord.getClef() == 0) ? GClefPaint.createPath(xPosition + 40) : FClefPaint.createPath(xPosition + 40);
            Paint pendingClefPaint = (chord.getClef() == 0) ? GClefPaint.create("#4D000000") : FClefPaint.create("#4D000000");
            if (xPosition < 130) {
                pendingClefPaint.setAlpha(0);
                if (musicView != null) {
                    if (isLeftHand) {
                        musicView.updateLeftClefDrawer(chord.getClef());
                    } else {
                        musicView.updateRightClefDrawer(chord.getClef());

                    }
                }
            }
            canvas.drawPath(pendingClefPath, pendingClefPaint);
        }
        if (!chord.getChordNotes().isEmpty()) {
            chord.setChromaticPositions();
            for (ChordNote chordNote : chord.getChordNotes()) {
                drawNote(canvas, measures, chord, chordNote, xPosition, staffHeight, noteHeadOriginalHeight, noteDuration, beamValues);
            }
        } else {
            MusicUtils.drawRest(canvas, xPosition, noteDuration, musicView);
        }
    }

    private void drawNote(Canvas canvas, List<Measure> measures, Chord chord, ChordNote chordNote, float xPosition, float staffHeight, float noteHeadOriginalHeight, float noteDuration, List<BeamValue> beamValues) {
        int currentNote = chordNote.getNoteId();
        if (currentNote > 112) currentNote -= 112;
        else if (currentNote > 56) currentNote -= 56;

        canvas.save();
        float noteY = MusicUtils.convertPitchToY(currentNote, chord.getClef());
        canvas.translate(xPosition, noteY);

        float scaleFactor = (staffHeight / 10) / noteHeadOriginalHeight;
        canvas.scale(scaleFactor, scaleFactor);

        Paint notePaint = MusicUtils.getNotePaint();
        Path notePath = MusicUtils.getNotePath(chord, currentNote, chordNote, beamValues);

        NoteStatus noteStatus = noteStatuses.computeIfAbsent(chordNote, k -> new NoteStatus());
        Paint currentNotePaint = new Paint(notePaint);

        updateNoteStatus(chord, chordNote, xPosition, currentNote, noteStatus, currentNotePaint);

        if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
            currentNotePaint.setAlpha(0);
        }

        canvas.drawPath(notePath, currentNotePaint);

        drawChromaticSign(canvas, chord, chordNote, xPosition, noteStatus, noteHeadOriginalHeight, beamValues);
        drawDottedNotes(canvas, currentNotePaint, noteDuration, noteHeadOriginalHeight, chordNote.getNoteId());

        canvas.restore();

        MusicUtils.drawLedgerLines(canvas, xPosition, chordNote, chord.getClef(), staffPaint, changedColorPaintPass, changedColorPaintMiss, GlobalVariables.CHECK_LINE_X - 40, noteStatus.isPassed, noteStatus.isCorrect);

        float slurDuration = chordNote.calculateTotalSlurredDuration(measures, chordNote);
        if (slurDuration != 0) {
            ChordNote targetChordNote = chordNote.findChordNoteWithTargetSlurPosition(measures, chordNote);
            Chord targetChord = chordNote.findChordWithTargetSlurPosition(measures, chordNote);
            int measureDiff = targetChord.getMeasureId() - chord.getMeasureId();
            float startX = xPosition + 100;
            float startY = MusicUtils.convertPitchToY(chordNote.getNoteId(), chord.getClef());
            float endX = xPosition + GlobalVariables.MEASURE_WIDTH * slurDuration + measureDuration * measureDiff + 60;
            float endY = MusicUtils.convertPitchToY(targetChordNote.getNoteId(), targetChord.getClef());
            if (MusicUtils.isUpSlur(chordNote.getNoteId(), targetChordNote.getNoteId(), chord.getClef())) {
                drawSlur(canvas, targetChordNote, startX, startY + 130, endX, endY + 130, true);
            } else {
                drawSlur(canvas, targetChordNote, startX, startY + 210, endX, endY + 210, false);
            }
        }
    }

    private void updateNoteStatus(Chord chord, ChordNote chordNote, float xPosition, int currentNote, NoteStatus noteStatus, Paint currentNotePaint) {
        float checkLineX = getCheckLineX(chord, currentNote, chordNote);

        if (xPosition <= checkLineX - 40 && !noteStatus.isPassed) {
            noteStatus.isPassed = true;
            noteStatus.isCorrect = correctNotes != null && correctNotes.contains(chordNote.getRealityNoteName());

            if (noteStatus.isCorrect) {
                GlobalVariables.COUNT_CORRECT += 1;
            } else {
                GlobalVariables.COUNT_INCORRECT += 1;
            }
        }

        if (noteStatus.isPassed) {
            currentNotePaint.setColor(noteStatus.isCorrect ? changedColorPaintPass.getColor() : changedColorPaintMiss.getColor());
            // Cập nhật màu của tất cả các nốt trong hợp âm nếu hợp âm đó chính xác
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

    private void drawSlur(Canvas canvas, ChordNote chordNote, float startX, float startY, float endX, float endY, boolean isUpwards) {
        Paint slurPaint = new Paint();
        slurPaint.setColor(0xFF000000); // Black color
        slurPaint.setStyle(Paint.Style.FILL);
        slurPaint.setAntiAlias(true);

        Path slurPath = new Path();
        float length = endX - startX;
        float curvature = length / 6; // Increased curvature for smoother appearance
        float controlX1 = startX + length / 4;
        float controlX2 = startX + 3 * length / 4;
        float controlY1, controlY2, lowerControlY1, lowerControlY2;

        // Adjusting the curvature to make the slur smoother and more pronounced
        if (isUpwards) {
            controlY1 = startY - curvature;
            controlY2 = startY - curvature;
            lowerControlY1 = startY - (curvature - 10);
            lowerControlY2 = startY - (curvature - 10);
        } else {
            controlY1 = startY + curvature;
            controlY2 = startY + curvature;
            lowerControlY1 = startY + (curvature - 10);
            lowerControlY2 = startY + (curvature - 10);
        }

        // Creating the slur path
        slurPath.moveTo(startX, startY);
        slurPath.cubicTo(controlX1, controlY1, controlX2, controlY2, endX, endY);
        slurPath.cubicTo(controlX2, lowerControlY2, controlX1, lowerControlY1, startX, startY);
        slurPath.close();

        // Checking if the note has been passed and adjusting the color accordingly
        NoteStatus noteStatus = noteStatuses.computeIfAbsent(chordNote, k -> new NoteStatus());
        if (noteStatus.isPassed) {
            slurPaint.setColor(noteStatus.isCorrect ? changedColorPaintPass.getColor() : changedColorPaintMiss.getColor());
        }

        // Making the slur fade as it approaches the check line
        if (endX < GlobalVariables.CHECK_LINE_X - 160) {
            slurPaint.setAlpha(0);
        }

        // Drawing the slur on the canvas
        canvas.drawPath(slurPath, slurPaint);
    }


    private float getCheckLineX(Chord chord, int currentNote, ChordNote chordNote) {
        boolean isChromatic = chordNote.getNoteId() > 65 && chordNote.getNoteId() < 169 || chordNote.getNoteId() > 112;
        float checkLineX = GlobalVariables.CHECK_LINE_X - (isChromatic ? 64 : 40);

        if (!isChromatic) {
            if (chord.getClef() == 0 && MusicUtils.calculateLedgerLinesGClef(currentNote) > 0) {
                checkLineX = GlobalVariables.CHECK_LINE_X - 64;
            } else if (chord.getClef() != 0 && MusicUtils.calculateLedgerLinesFClef(currentNote) > 0) {
                checkLineX = GlobalVariables.CHECK_LINE_X - 64;
            }
        }

        return checkLineX;
    }

    private void drawChromaticSign(Canvas canvas, Chord chord, ChordNote chordNote, float xPosition, NoteStatus noteStatus, float noteHeadOriginalHeight, List<BeamValue> beamValues) {
        Paint chromaticSignPaint;
        Path chromaticSignPath;
        boolean isFlipNote = false;
        int position = chordNote.getChromaticPosition();
        if (MusicUtils.isChordToBeam(beamValues, chord)) {
            if (MusicUtils.isBeamStemUp(beamValues, chord) == 0) {
                if (!chord.getFlipNotes(chord.isStemUp()).isEmpty()) {
                    isFlipNote = true;
                }
            }
        } else {
            if (!chord.isStemUp()) {
                if (!chord.getFlipNotes(chord.isStemUp()).isEmpty()) {
                    isFlipNote = true;
                }
            }
        }
        if (chordNote.getNoteId() > 56 && chordNote.getNoteId() < 113) { // Flat sign
            chromaticSignPaint = new Paint(FlatSignPaint.create());
            chromaticSignPath = FlatSignPaint.createPath();
        } else if (chordNote.getNoteId() > 112) { // Sharp sign
            chromaticSignPaint = new Paint(SharpSignPaint.create());
            chromaticSignPath = SharpSignPaint.createPath();
        } else if (chordNote.getNoteId() <= 56 && chordNote.getChromaticPosition() > 0) {
            chromaticSignPaint = new Paint(NaturalSignPaint.create());
            chromaticSignPath = NaturalSignPaint.createPath();
        } else if (chordNote.isNaturalSign()) {
            chromaticSignPaint = new Paint(NaturalSignPaint.create());
            chromaticSignPath = NaturalSignPaint.createPath();
            position += 1;
        } else {
            return; // No chromatic sign to draw
        }

        if (noteStatus.isPassed) {
            chromaticSignPaint.setColor(noteStatus.isCorrect ? changedColorPaintPass.getColor() : changedColorPaintMiss.getColor());
        }
        if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
            chromaticSignPaint.setAlpha(0);
        }

        MusicUtils.drawChromaticSign(canvas, chromaticSignPaint, chromaticSignPath, noteHeadOriginalHeight, position, isFlipNote);
    }

    private void drawDottedNotes(Canvas canvas, Paint notePaint, float noteDuration, float noteHeadOriginalHeight, int noteId) {
        int dottedNoteCount = MusicUtils.countDottedNotes(noteDuration);
        if (dottedNoteCount > 0) {
            MusicUtils.drawDottedNotes(canvas, notePaint, dottedNoteCount, noteHeadOriginalHeight, noteId);
        }
    }
}
