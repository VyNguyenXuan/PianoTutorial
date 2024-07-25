package com.example.pianotutorial.models;

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

    public boolean isBeamedNoteStemUp(int clef) {
        int middleLinePitch = (clef == 0) ? 28 : 16; // 28 : B4 (G clef), 16 : B3 (F clef)
        int farthestNote = 0;
        int startNoteId = startChord.isStemUp(clef) ? startChord.findLowestNoteIdWithoutFlip(clef) : startChord.findHighestNoteIdWithoutFlip(clef);
        int endNoteId = startChord.isStemUp(clef) ? endChord.findLowestNoteIdWithoutFlip(clef) : endChord.findHighestNoteIdWithoutFlip(clef);
        farthestNote = (Math.abs(startNoteId - middleLinePitch) < Math.abs(endNoteId - middleLinePitch)) ? endNoteId : startNoteId;
        // If the farthest note is below the middle line pitch, return true (stem up); otherwise, return false (stem down)
        return farthestNote < middleLinePitch;
    }

    public int findLowestBeamNoteId(int clef) {
        int lowestStartChord = startChord.findLowestNoteIdWithoutFlip(clef);
        int lowestEndChord = endChord.findLowestNoteIdWithoutFlip(clef);
        return Math.min(lowestStartChord, lowestEndChord);
    }

    public int findHighestBeamNoteId(int clef) {
        int highestStartChord = startChord.findHighestNoteIdWithoutFlip(clef);
        int highestEndChord = endChord.findHighestNoteIdWithoutFlip(clef);
        return Math.max(highestStartChord, highestEndChord);
    }
}
