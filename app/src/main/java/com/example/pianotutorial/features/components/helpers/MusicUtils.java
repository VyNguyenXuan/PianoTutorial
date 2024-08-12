package com.example.pianotutorial.features.components.helpers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.features.components.paints.MusicView;
import com.example.pianotutorial.features.components.paints.notepaints.CustomQuarterNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.CustomQuarterNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.EighthNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.EighthNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.HalfNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.HalfNotePaintFlip;
import com.example.pianotutorial.features.components.paints.notepaints.HalfNotePaintFlipReverse;
import com.example.pianotutorial.features.components.paints.notepaints.HalfNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.QuarterNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.QuarterNotePaintFlip;
import com.example.pianotutorial.features.components.paints.notepaints.QuarterNotePaintFlipReverse;
import com.example.pianotutorial.features.components.paints.notepaints.QuarterNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.SixteenthNotePaintReverse;
import com.example.pianotutorial.features.components.paints.notepaints.WholeNotePaint;
import com.example.pianotutorial.features.components.paints.notepaints.WholeNotePaintFlip;
import com.example.pianotutorial.features.components.paints.notepaints.WholeNotePaintFlipReverse;
import com.example.pianotutorial.models.BeamValue;
import com.example.pianotutorial.models.Chord;
import com.example.pianotutorial.models.ChordNote;

import java.util.List;

public class MusicUtils {

    public static int countDottedNotes(float duration) {
        int count = 0;

        // Loop to check for dotted notes
        while (duration > 0) {
            if (duration == 4.0f || duration == 2.0f || duration == 1.0f || duration == 0.5f || duration == 0.25f) {
                return count;
            } else {
                if (duration > 4.0f) {
                    duration -= 4.0f;
                } else if (duration > 2.0f) {
                    duration -= 2.0f;
                } else if (duration > 1.0f) {
                    duration -= 1.0f;
                } else if (duration > 0.5f) {
                    duration -= 0.5f;
                } else if (duration > 0.25f) {
                    duration -= 0.25f;
                } else if (duration > 0.125f) {
                    duration -= 0.125f;
                }

                count++;
            }
        }
        return count;
    }

    public static boolean isNoteOnLine(int noteId) {
        // Define the notes that are on lines for the treble clef across several octaves
        return noteId % 2 == 0;
    }

    public static int calculateLedgerLinesGClef(int noteId) {
        // Avoid flat and sharp sign
        while (noteId > 56) {
            noteId -= 56;
        }
        int ledgerLines = 0;

        // If noteId < D4, calculate ledger line
        if (noteId < 23) {
            if ((23 - noteId) % 2 == 0) {
                ledgerLines = (23 - noteId) / 2;
            } else {
                ledgerLines = (23 - noteId) / 2 + 1;
            }

        }

        // If noteId > G5, calculate ledger line
        else if (noteId > 33) {
            if ((noteId - 33) % 2 == 0) {
                ledgerLines = (noteId - 33) / 2;
            } else {
                ledgerLines = (noteId - 33) / 2 + 1;
            }
        }
        return ledgerLines;
    }

    public static int calculateLedgerLinesFClef(int noteId) {
        // Avoid flat and sharp sign
        while (noteId > 56) {
            noteId -= 56;
        }
        int ledgerLines = 0;

        // If noteId < F2, calculate ledger line
        if (noteId < 11) {
            if ((11 - noteId) % 2 == 0) {
                ledgerLines = (11 - noteId) / 2;
            } else {
                ledgerLines = (11 - noteId) / 2 + 1;
            }
        }

        // If noteId > B3, calculate ledger line
        else if (noteId > 21) {
            if ((noteId - 21) % 2 == 0) {
                ledgerLines = (noteId - 21) / 2;
            } else {
                ledgerLines = (noteId - 21) / 2 + 1;
            }
        }
        return ledgerLines;
    }

    public static void drawChromaticSign(Canvas canvas, Paint chromaticPaint, Path chromaticPath, float noteHeadOriginalHeight, int chromaticPosition, boolean isFlipNote) {
        if (chromaticPosition > 0) {
            float chromaticX = -noteHeadOriginalHeight * 1.5f + 36 - (chromaticPosition - 1) * 20; // Adjust the x-position as needed
            chromaticX = (isFlipNote) ? chromaticX - 28 : chromaticX;
            float chromaticY = 77f;
            canvas.save();
            canvas.translate(chromaticX, chromaticY);
            float scaleFactor = noteHeadOriginalHeight / 24f; // Adjust the scale as needed
            canvas.scale(scaleFactor, scaleFactor);
            canvas.drawPath(chromaticPath, chromaticPaint);
            canvas.restore();
        }

    }

    public static void drawDottedNotes(Canvas canvas, Paint notePaint, int dottedNoteCount, float noteHeadOriginalHeight, int noteId) {
        float dotRadius = noteHeadOriginalHeight / 4;
        float dotX = noteHeadOriginalHeight + noteHeadOriginalHeight;
        float dotY = noteHeadOriginalHeight * 4+18;
        if (MusicUtils.isNoteOnLine(noteId)) {
            dotY -= (float) GlobalVariables.FIXED_HEIGHT / 32; // If isNoteOnLine move up a note
        }
        for (int i = 0; i < dottedNoteCount; i++) {
            canvas.drawCircle(dotX + (i * noteHeadOriginalHeight) + 36, dotY, dotRadius, notePaint);
        }
    }

    public static void drawLedgerLines(Canvas canvas, float xPosition, ChordNote chordNote, int clef, Paint linePaint, Paint changedColorPaintPass, Paint changedColorPaintMiss, float checkLineX, boolean isPassed, boolean isCorrect) {
        float staffHeight = GlobalVariables.FIXED_HEIGHT;
        float lineSpacing = staffHeight / 8;
        float topLineY = staffHeight / 2;
        float bottomLineY = topLineY + 4 * lineSpacing;
        int noteId = chordNote.getNoteId();
        while (noteId > 56) {
            noteId -= 56;
        }

        int ledgerLineCount = (clef == 0)
                ? MusicUtils.calculateLedgerLinesGClef(noteId)
                : MusicUtils.calculateLedgerLinesFClef(noteId);

        for (int i = 0; i < ledgerLineCount; i++) {
            float y;
            if (clef == 0) {
                y = (noteId < 23)
                        ? bottomLineY + (i + 1) * lineSpacing
                        : topLineY - (i + 1) * lineSpacing;
            } else {
                y = (noteId < 11)
                        ? bottomLineY + (i + 1) * lineSpacing
                        : topLineY - (i + 1) * lineSpacing;
            }


            Paint paintToUse = new Paint(linePaint);
            if (isPassed) {
                if (isCorrect) {
                    paintToUse.setColor(changedColorPaintPass.getColor());
                } else {
                    paintToUse.setColor(changedColorPaintMiss.getColor());
                }
            }
            if (xPosition < checkLineX - 160 + 24) {
                paintToUse.setAlpha(0);
            }
            canvas.drawLine(xPosition + 34, y, xPosition + 130, y, paintToUse);
        }
    }

    public static void drawRest(Canvas canvas, float xPosition, float duration, MusicView musicView) {
        Drawable restDrawable;
        float yPosition;
        float additionalOffset;

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
            additionalOffset = 20;
        } else if (duration == 0.5) {
            restDrawable = ResourcesCompat.getDrawable(musicView.getContext().getResources(), R.drawable.vector_eighth_rest, null);
            yPosition = 282; // Set appropriate y-position
            additionalOffset = 20;
        } else if (duration == 0.25) {
            restDrawable = ResourcesCompat.getDrawable(musicView.getContext().getResources(), R.drawable.vector_sixteenth_rest, null);
            yPosition = 290; // Set appropriate y-position
            additionalOffset = 20;
        } else {
            return; // Invalid duration for rest
        }

        int intrinsicWidth = restDrawable != null ? restDrawable.getIntrinsicWidth() : 0;
        int intrinsicHeight = restDrawable != null ? restDrawable.getIntrinsicHeight() : 0;

        float top = yPosition - (float) intrinsicHeight / 2;
        float right = xPosition + intrinsicWidth + additionalOffset;
        float bottom = top + intrinsicHeight;

        // Check if the xPosition is less than the threshold, if so, make it disappear
        if (additionalOffset == 600) {
            if (xPosition < GlobalVariables.CHECK_LINE_X - 760) {
                return; // Do not draw the rest
            }
        } else if (additionalOffset == 300) {
            if (xPosition < GlobalVariables.CHECK_LINE_X - 460) {
                return; // Do not draw the rest
            }
        } else {
            if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
                return; // Do not draw the rest
            }
        }


        if (restDrawable != null) {
            restDrawable.setBounds((int) (xPosition + additionalOffset), (int) top, (int) right, (int) bottom);
            restDrawable.draw(canvas);
        }
    }


    public static Paint getNotePaint() {
        return WholeNotePaint.create();
    }

    public static Path getNotePath(Chord chord, int currentNote, ChordNote chordNote, List<BeamValue> beamValues) {
        if (chord.getClef() == 0) {
            return getGKeyNotePath(currentNote, chordNote, chord, beamValues);
        } else {
            return getFKeyNotePath(currentNote, chordNote, chord, beamValues);
        }
    }

    public static Path getGKeyNotePath(int currentNote, ChordNote chordNote, Chord chord, List<BeamValue> beamValues) {
        if (currentNote > 27) {
            return getNotePathByDuration(chord, chordNote, true, beamValues);
        } else {
            return getNotePathByDuration(chord, chordNote, false, beamValues);
        }
    }

    public static Path getFKeyNotePath(int currentNote, ChordNote chordNote, Chord chord, List<BeamValue> beamValues) {
        if (currentNote > 15) {
            return getNotePathByDuration(chord, chordNote, true, beamValues);
        } else {
            return getNotePathByDuration(chord, chordNote, false, beamValues);
        }
    }

    public static Path getNotePathByDuration(Chord chord, ChordNote chordNote, boolean reverse, List<BeamValue> beamValues) {

        float noteDuration = chord.getDuration();
        int currentNoteId = chord.adjustedNoteId(chordNote.getNoteId());
        if (isChordToBeam(beamValues, chord)) {
            if (isBeamStemUp(beamValues, chord) == 1) {
                if (chord.getFlipNotes(true).contains(currentNoteId)) {
                    return QuarterNotePaintFlip.createPath();
                } else {
                    List<Chord> chords = getBeamNotes(beamValues, chord);
                    assert chords != null;
                    if (!chords.isEmpty()) {
                        float diff = Math.abs(chords.get(0).findHighestNoteIdWithoutFlip(true) - chords.get(1).findHighestNoteIdWithoutFlip(true));
                        if (chords.get(0).findHighestNoteIdWithoutFlip(true) < chords.get(1).findHighestNoteIdWithoutFlip(true)) {
                            if (currentNoteId == chords.get(0).findHighestNoteIdWithoutFlip(true)) {
                                return CustomQuarterNotePaint.createPath(-11.67f * (diff - 1));
                            } else {
                                return QuarterNotePaint.createPath();
                            }
                        } else {
                            if (currentNoteId == chords.get(0).findHighestNoteIdWithoutFlip(true)) {
                                return QuarterNotePaint.createPath();
                            } else {
                                return CustomQuarterNotePaint.createPath(-11.67f * (diff - 1));
                            }
                        }
                    } else {
                        return WholeNotePaint.createPath();
                    }
                }
            } else {
                if (chord.getFlipNotes(false).contains(currentNoteId)) {
                    return QuarterNotePaintFlipReverse.createPath();
                } else {
                    List<Chord> chords = getBeamNotes(beamValues, chord);
                    assert chords != null;
                    if (!chords.isEmpty()) {
                        float diff = Math.abs(chords.get(0).findLowestNoteIdWithoutFlip(false) - chords.get(1).findLowestNoteIdWithoutFlip(false));
                        if (chords.get(0).findLowestNoteIdWithoutFlip(false) > chords.get(1).findLowestNoteIdWithoutFlip(false)) {
                            if (currentNoteId == chords.get(0).findLowestNoteIdWithoutFlip(false)) {
                                return CustomQuarterNotePaintReverse.createPath(11.67f * (diff - 1));//To draw beam
                            } else {
                                return QuarterNotePaintReverse.createPath();
                            }
                        } else {
                            if (currentNoteId == chords.get(0).findLowestNoteIdWithoutFlip(false)) {
                                return QuarterNotePaintReverse.createPath();
                            } else {
                                return CustomQuarterNotePaintReverse.createPath(11.67f * (diff - 1));
                            }
                        }
                    } else {
                        return WholeNotePaint.createPath();
                    }
                }
            }
        } else {
            if (!chord.hasMultipleNotes()) {
                if (noteDuration < 8 && noteDuration >= 4) {
                    return WholeNotePaint.createPath();
                } else if (noteDuration < 4 && noteDuration >= 2) {
                    return reverse ? HalfNotePaintReverse.createPath() : HalfNotePaint.createPath();
                } else if (noteDuration < 2 && noteDuration >= 1) {
                    return reverse ? QuarterNotePaintReverse.createPath() : QuarterNotePaint.createPath();
                } else if (noteDuration < 1 && noteDuration >= 0.5) {
                    return reverse ? EighthNotePaintReverse.createPath() : EighthNotePaint.createPath();
                } else {
                    return reverse ? SixteenthNotePaintReverse.createPath() : SixteenthNotePaint.createPath();
                }
            } else {
                if (chord.isStemUp()) {
                    if (chord.findHighestNoteIdWithoutFlip(true) == currentNoteId) {
                        if (noteDuration < 8 && noteDuration >= 4) {
                            return WholeNotePaint.createPath();
                        } else if (noteDuration < 4 && noteDuration >= 2) {
                            return HalfNotePaint.createPath();
                        } else if (noteDuration < 2 && noteDuration >= 1) {
                            return QuarterNotePaint.createPath();
                        } else if (noteDuration < 1 && noteDuration >= 0.5) {
                            return EighthNotePaint.createPath();
                        } else {
                            return SixteenthNotePaint.createPath();
                        }
                    } else if (chord.getFlipNotes(true).contains(currentNoteId)) {
                        if (noteDuration < 8 && noteDuration >= 4) {
                            return WholeNotePaintFlip.createPath();
                        } else if (noteDuration < 4 && noteDuration >= 2) {
                            return HalfNotePaintFlip.createPath();
                        } else {
                            return QuarterNotePaintFlip.createPath();
                        }
                    } else {
                        if (noteDuration < 8 && noteDuration >= 4) {
                            return WholeNotePaint.createPath();
                        } else if (noteDuration < 4 && noteDuration >= 2) {
                            return HalfNotePaint.createPath();
                        } else {
                            return QuarterNotePaint.createPath();
                        }
                    }
                } else {
                    if (chord.findLowestNoteIdWithoutFlip(false) == currentNoteId) {
                        if (noteDuration < 8 && noteDuration >= 4) {
                            return WholeNotePaint.createPath();
                        } else if (noteDuration < 4 && noteDuration >= 2) {
                            return HalfNotePaintReverse.createPath();
                        } else if (noteDuration < 2 && noteDuration >= 1) {
                            return QuarterNotePaintReverse.createPath();
                        } else if (noteDuration < 1 && noteDuration >= 0.5) {
                            return EighthNotePaintReverse.createPath();
                        } else {
                            return SixteenthNotePaintReverse.createPath();
                        }
                    } else if (chord.getFlipNotes(false).contains(currentNoteId)) {
                        if (noteDuration < 8 && noteDuration >= 4) {
                            return WholeNotePaintFlipReverse.createPath();
                        } else if (noteDuration < 4 && noteDuration >= 2) {
                            return HalfNotePaintFlipReverse.createPath();
                        } else {
                            return QuarterNotePaintFlipReverse.createPath();
                        }
                    } else {
                        if (noteDuration < 8 && noteDuration >= 4) {
                            return WholeNotePaint.createPath();
                        } else if (noteDuration < 4 && noteDuration >= 2) {
                            return HalfNotePaintReverse.createPath();
                        } else {
                            return QuarterNotePaintReverse.createPath();
                        }
                    }
                }
            }
        }


    }

    public static boolean isChordToBeam(List<BeamValue> beamValueList, Chord chord) {
        if (!beamValueList.isEmpty()) {
            for (BeamValue beamValue : beamValueList) {
                if (beamValue.getStartChord() == chord || beamValue.getEndChord() == chord) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int isBeamStemUp(List<BeamValue> beamValueList, Chord chord) {
        if (!beamValueList.isEmpty()) {
            for (BeamValue beamValue : beamValueList) {
                if (beamValue.isBeamedNoteStemUp()) {
                    if (chord == beamValue.getStartChord() || (chord == beamValue.getEndChord())) {
                        return 1;
                    }
                } else {
                    if (chord == beamValue.getStartChord() || (chord == beamValue.getEndChord())) {
                        return 0;
                    }
                }
            }
        }
        return -1;
    }

    public static List<Chord> getBeamNotes(List<BeamValue> beamValueList, Chord chord) {
        if (!beamValueList.isEmpty()) {
            for (BeamValue beamValue : beamValueList) {
                if (!beamValue.getBeamNotes(chord).isEmpty()) {
                    return beamValue.getBeamNotes(chord);
                }
            }
        }
        return null;
    }

    public static float convertPitchToY(int noteId, int clef) {
        noteId = currentNoteId(noteId);
        float baseHeight = GlobalVariables.C4_CURRENT_NOTE; // D4
        int baseId = (clef == 0) ? 22 : 10;
        int spacingValue = noteId - baseId;
        float noteSpacing = (float) GlobalVariables.FIXED_HEIGHT / 16;
        return baseHeight - spacingValue * noteSpacing;
    }

    public static int currentNoteId(int noteId) {
        while (noteId > 56) {
            noteId -= 56;
        }
        return noteId;
    }

    public static boolean isUpSlur(int noteId1, int noteId2, int clef) {
        noteId1 = currentNoteId(noteId1);
        noteId2 = currentNoteId(noteId2);
        int middleLineNoteId = (clef == 0) ? 28 : 16;
        if (noteId1 <= middleLineNoteId && noteId2 <= middleLineNoteId) {
            return false;
        } else if (noteId1 > middleLineNoteId && noteId2 > middleLineNoteId) {
            return true;
        } else {
            return noteId1 >= noteId2; // Return true if noteId1 is lower, otherwise false
        }
    }


}

