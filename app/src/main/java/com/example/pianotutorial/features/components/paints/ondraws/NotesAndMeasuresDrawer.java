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
                            //musicView.saveNoteValue(chord); // Notify MusicView to save the note value
                        }

                        if (!chord.getChordNotes().isEmpty()) {
                            for (ChordNote chordNote : chord.getChordNotes()) {
                                int currentNote=chordNote.getNoteId();
                                if(currentNote>112) currentNote-=112;
                                else if(currentNote>56) currentNote-=56;
                                canvas.save();
                                float noteY = MusicUtils.convertPitchToY(currentNote,measure.getClef());
                                canvas.translate(xPosition, noteY);

                                float scaleFactor = (staffHeight / 10) / noteHeadOriginalHeight;
                                canvas.scale(scaleFactor, scaleFactor);

                                Paint notePaint;
                                Path notePath;

                                if(measure.getClef()==0){
                                    if (currentNote>27) {
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
                                }
                                else {
                                    if (currentNote>15) {
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
                                if (chordNote.getNoteId()>65 && chordNote.getNoteId()<113) { // Flat sign
                                    Paint flatSignPaint = new Paint(FlatSignPaint.create());
                                    if (xPosition <= GlobalVariables.CHECK_LINE_X - 20) {
                                        flatSignPaint.setColor(changedColorPaint.getColor());
                                    }
                                    if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
                                        flatSignPaint.setAlpha(0);
                                    }
                                    Path flatSignPath = FlatSignPaint.createPath();
                                    MusicUtils.drawChromaticSign(canvas, flatSignPaint, flatSignPath, noteHeadOriginalHeight);
                                } else if (chordNote.getNoteId()>112) { // Sharp sign
                                    Paint sharpSignPaint = new Paint(SharpSignPaint.create());
                                    if (xPosition <= GlobalVariables.CHECK_LINE_X - 20) {
                                        sharpSignPaint.setColor(changedColorPaint.getColor());
                                    }
                                    if (xPosition < GlobalVariables.CHECK_LINE_X - 160) {
                                        sharpSignPaint.setAlpha(0);
                                    }
                                    Path sharpSignPath = SharpSignPaint.createPath();
                                    MusicUtils.drawChromaticSign(canvas, sharpSignPaint, sharpSignPath, noteHeadOriginalHeight);
                                }

                                // Draw dotted notes
                                int dottedNoteCount = MusicUtils.countDottedNotes(noteDuration);
                                if (dottedNoteCount > 0) {
                                    MusicUtils.drawDottedNotes(canvas, currentNotePaint, dottedNoteCount, noteHeadOriginalHeight, chordNote.getNoteId());
                                }

                                canvas.restore();

                                MusicUtils.drawLedgerLines(canvas, xPosition, chordNote.getNoteId(),measure.getClef() , xPosition <= 580 ? changedColorPaint : staffPaint, xPosition);
                            }
                        } else {
                            MusicUtils.drawRest(canvas, xPosition, noteDuration, currentTime,musicView);
                        }
                    }

                    currentX += GlobalVariables.MEASURE_WIDTH;
                }
            }
        }
    }
}
