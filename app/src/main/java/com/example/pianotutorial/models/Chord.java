package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Chord {
    @SerializedName("id")
    private int id;

    @SerializedName("duration")
    private float duration;

    @SerializedName("measureId")
    private int measureId;

    @SerializedName("position")
    private int position;

    @SerializedName("chordNotes")
    private List<ChordNote> chordNotes;
    public Chord() {
        id = 0;
        duration = 0;
        measureId = 0;
        position = 0;
        chordNotes = new ArrayList<>();
    }

    public Chord(int id, float duration, int measureId, int position, List<ChordNote> chordNotes) {
        this.id = id;
        this.duration = duration;
        this.measureId = measureId;
        this.position = position;
        this.chordNotes = chordNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getMeasureId() {
        return measureId;
    }

    public void setMeasureId(int measureId) {
        this.measureId = measureId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<ChordNote> getChordNotes() {
        return chordNotes;
    }

    public void setChordNotes(List<ChordNote> chordNotes) {
        this.chordNotes = chordNotes;
    }

    public boolean isStemUp() {
        int middleLinePitch = 28;
        int belowMiddleLine = 0;
        int aboveMiddleLine = 0;

        for (ChordNote note : chordNotes) {
            if (note.getNoteId() < middleLinePitch) {
                belowMiddleLine++;
            } else if (note.getNoteId() > middleLinePitch) {
                aboveMiddleLine++;
            }
        }

        if (belowMiddleLine > aboveMiddleLine) {
            return true; // Stem up
        } else if (aboveMiddleLine > belowMiddleLine) {
            return false; // Stem down
        } else {
            // If equal, check the note farthest from middle line
            int farthestNotePitch = chordNotes.get(0).getNoteId();
            for (ChordNote note : chordNotes) {
                if (Math.abs(note.getNoteId() - middleLinePitch) > Math.abs(farthestNotePitch - middleLinePitch)) {
                    farthestNotePitch = note.getNoteId();
                }
            }
            return farthestNotePitch < middleLinePitch; // Farthest note below middle line means stem up
        }
    }
}
