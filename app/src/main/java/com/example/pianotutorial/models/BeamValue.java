package com.example.pianotutorial.models;

import java.util.ArrayList;
import java.util.List;

public class BeamValue {
    private Chord startChord;
    private Chord endChord;

    public BeamValue(Chord startChord, Chord endChord) {
        this.startChord = startChord;
        this.endChord = endChord;
    }

    public Chord getStartChord() {
        return startChord;
    }

    public void setStartChord(Chord startChord) {
        this.startChord = startChord;
    }

    public Chord getEndChord() {
        return endChord;
    }

    public void setEndChord(Chord endChord) {
        this.endChord = endChord;
    }

    public boolean isBeamedNoteStemUp() {
        int middleLinePitch = (startChord.getClef() == 0) ? 28 : 16; // 28 : B4 (G clef), 16 : B3 (F clef)
        int farthestNote = 0;
        int startNoteId = startChord.isStemUp() ? startChord.findLowestNoteIdWithoutFlip() : startChord.findHighestNoteIdWithoutFlip();
        int endNoteId = startChord.isStemUp() ? endChord.findLowestNoteIdWithoutFlip() : endChord.findHighestNoteIdWithoutFlip();
        farthestNote = (Math.abs(startNoteId - middleLinePitch) < Math.abs(endNoteId - middleLinePitch)) ? endNoteId : startNoteId;
        // If the farthest note is below the middle line pitch, return true (stem up); otherwise, return false (stem down)
        return farthestNote < middleLinePitch;
    }

    public int findLowestBeamNoteId() {
        int lowestStartChord = startChord.findLowestNoteIdWithoutFlip();
        int lowestEndChord = endChord.findLowestNoteIdWithoutFlip();
        return Math.min(lowestStartChord, lowestEndChord);
    }

    public ChordNote findLowestBeamNote() {
        for (ChordNote chordNote : startChord.getChordNotes()) {
            if (chordNote.getNoteId() == findLowestBeamNoteId()) {
                return chordNote;
            }
        }
        for (ChordNote chordNote : endChord.getChordNotes()) {
            if (chordNote.getNoteId() == findLowestBeamNoteId()) {
                return chordNote;
            }
        }
        return null;
    }

    public int findHighestBeamNoteId() {
        int highestStartChord = startChord.findHighestNoteIdWithoutFlip();
        int highestEndChord = endChord.findHighestNoteIdWithoutFlip();
        return Math.max(highestStartChord, highestEndChord);
    }

    public ChordNote findHighestBeamNote() {
        for (ChordNote chordNote : startChord.getChordNotes()) {
            if (chordNote.getNoteId() == findHighestBeamNoteId()) {
                return chordNote;
            }
        }
        for (ChordNote chordNote : endChord.getChordNotes()) {
            if (chordNote.getNoteId() == findHighestBeamNoteId()) {
                return chordNote;
            }
        }
        return null;
    }

    public List<Chord> getBeamNotes(Chord currentChord) {
        List<Chord> chordList = new ArrayList<>();
        if (startChord == currentChord || endChord == currentChord) {
            chordList.add(startChord);
            chordList.add(endChord);
        }
        return chordList;
    }

}
