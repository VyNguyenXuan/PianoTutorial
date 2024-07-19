package com.example.pianotutorial.features.components.helpers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.features.components.paints.MusicView;
import com.example.pianotutorial.features.components.paints.notepaints.FlatSignPaint;
import com.example.pianotutorial.models.ChordNote;

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

    public static void drawChromaticSign(Canvas canvas, Paint chromaticPaint, Path chromaticPath, float noteHeadOriginalHeight) {
        float chromaticX = -noteHeadOriginalHeight * 1.5f + 36; // Adjust the x-position as needed
        float chromaticY = 54f;
        canvas.save();
        canvas.translate(chromaticX, chromaticY);
        float scaleFactor = noteHeadOriginalHeight / 24f; // Adjust the scale as needed
        canvas.scale(scaleFactor, scaleFactor);
        canvas.drawPath(chromaticPath, chromaticPaint);
        canvas.restore();
    }

    public static void drawDottedNotes(Canvas canvas, Paint notePaint, int dottedNoteCount, float noteHeadOriginalHeight, int noteId) {
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
            if (xPosition < checkLineX - 160) {
                paintToUse.setAlpha(0);
            }
            canvas.drawLine(xPosition + 24, y, xPosition + 110, y, paintToUse);
        }
    }

    public static void drawRest(Canvas canvas, float xPosition, float duration, long currentTime, MusicView musicView) {
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

    public static float convertPitchToY(int noteId, int clef) {
        float baseHeight = GlobalVariables.C4_CURRENT_NOTE; // D4
        int baseId = (clef == 0) ? 22 : 10;
        int spacingValue = noteId - baseId;
        float noteSpacing = (float) GlobalVariables.FIXED_HEIGHT / 16;
        return baseHeight - spacingValue * noteSpacing;
    }
}

