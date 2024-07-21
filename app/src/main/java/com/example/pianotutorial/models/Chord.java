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

    public boolean isStemUp(int clefValue) {
        int middleLinePitch = (clefValue == 0) ? 28 : 16; // 28 : B4 (G clef), 16 : B3 (F clef)
        int belowMiddleLine = 0;
        int aboveMiddleLine = 0;

        // Count belowMiddleLine, aboveMiddleLine
        for (ChordNote note : chordNotes) {
            int adjustedNoteId = adjustedNoteId(note.getNoteId());
            if (adjustedNoteId < middleLinePitch) {
                belowMiddleLine++;
            } else if (adjustedNoteId > middleLinePitch) {
                aboveMiddleLine++;
            }
        }

        if (belowMiddleLine > aboveMiddleLine) {
            return true; // Stem up
        } else if (aboveMiddleLine > belowMiddleLine) {
            return false; // Stem down
        } else {

            int farthestNotePitch = adjustedNoteId(chordNotes.get(0).getNoteId());
            for (ChordNote note : chordNotes) {
                int adjustedNoteId = adjustedNoteId(note.getNoteId());
                if (Math.abs(adjustedNoteId - middleLinePitch) > Math.abs(farthestNotePitch - middleLinePitch)) {
                    farthestNotePitch = adjustedNoteId;
                }
            }
            return farthestNotePitch < middleLinePitch;
        }
    }

    public int findLowestNoteId() {
        if (chordNotes.isEmpty()) {
            return -1; // or throw an exception if you prefer
        }

        int lowestNoteId = adjustedNoteId(chordNotes.get(0).getNoteId());
        for (ChordNote note : chordNotes) {
            int adjustedNoteId = adjustedNoteId(note.getNoteId());
            if (adjustedNoteId < lowestNoteId) {
                lowestNoteId = adjustedNoteId;
            }
        }
        return lowestNoteId;
    }

    // Find the note with the highest noteId
    public int findHighestNoteId() {
        if (chordNotes.isEmpty()) {
            return -1; // or throw an exception if you prefer
        }

        int highestNoteId = adjustedNoteId(chordNotes.get(0).getNoteId());
        for (ChordNote note : chordNotes) {
            int adjustedNoteId = adjustedNoteId(note.getNoteId());
            if (adjustedNoteId > highestNoteId) {
                highestNoteId = adjustedNoteId;
            }
        }
        return highestNoteId;
    }

    public boolean hasMultipleNotes() {
        return chordNotes.size() > 1;
    }

    int adjustedNoteId(int noteId) {
        while (noteId > 56) {
            noteId -= 56;
        }
        return noteId;
    }
}
