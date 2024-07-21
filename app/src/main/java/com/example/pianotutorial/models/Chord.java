package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Integer> getFlipNotes(int clefValue) {
        List<Integer> flipNotes = new ArrayList<>();
        if (chordNotes.isEmpty()) {
            return flipNotes; // Return empty list if no chord notes
        }

        boolean stemUp = isStemUp(clefValue); // Assuming clefValue is passed as 0
        List<Integer> noteIds = new ArrayList<>();
        for (ChordNote note : chordNotes) {
            noteIds.add(adjustedNoteId(note.getNoteId()));
        }

        if (stemUp) {
            Collections.sort(noteIds);
        } else {
            Collections.sort(noteIds, Collections.reverseOrder());
        }

        int previousNoteId = noteIds.get(0);
        for (int i = 1; i < noteIds.size(); i++) {
            int currentNoteId = noteIds.get(i);
            if (Math.abs(currentNoteId - previousNoteId) == 1) {
                flipNotes.add(currentNoteId);
            }
            previousNoteId = currentNoteId;
        }

        return flipNotes;
    }

    public int findLowestNoteIdWithoutFlip(int clefValue) {
        List<Integer> flipNotes = getFlipNotes(clefValue);
        int lowestNoteId = findLowestNoteId();

        // If the lowestNoteId is in flipNotes and has a preceding note
        if (flipNotes.contains(lowestNoteId) && flipNotes.indexOf(lowestNoteId) > 0) {
            return lowestNoteId + 1;
        }
        return lowestNoteId; // Otherwise, return the original lowestNoteId
    }

    public int findHighestNoteIdWithoutFlip(int clefValue) {
        List<Integer> flipNotes = getFlipNotes(clefValue);
        int highestNoteId = findHighestNoteId();

        // If the highestNoteId is in flipNotes and has a preceding note
        if (flipNotes.contains(highestNoteId) && flipNotes.indexOf(highestNoteId) > 0) {
            return highestNoteId - 1;
        }
        return highestNoteId; // Otherwise, return the original highestNoteId
    }

    int adjustedNoteId(int noteId) {
        while (noteId > 56) {
            noteId -= 56;
        }
        return noteId;
    }
}
