package com.example.pianotutorial.models;

import java.util.List;

public class ChordNote {
    private int id;
    private int noteId;
    private int realityNoteId;
    private int chordId;
    private int chordPosition;
    private String noteName;
    private String realityNoteName;
    private int noteOctave;
    private int slurPosition;
    private int chromaticPosition;
    private boolean isNaturalSign;

    public ChordNote() {
        id = 0;
        noteId = 0;
        realityNoteId = 0;
        chordId = 0;
        chordPosition = 0;
        noteName = "";
        realityNoteName = "";
        noteOctave = 0;
        slurPosition = 0;
        chromaticPosition = 0;
        isNaturalSign = false;
    }

    public ChordNote(int id, int noteId, int realityNoteId, int chordId, int chordPosition, String noteName, String realityNoteName, int noteOctave, int slurPosition, boolean isNaturalSign) {
        this.id = id;
        this.noteId = noteId;
        this.realityNoteId = realityNoteId;
        this.chordId = chordId;
        this.chordPosition = chordPosition;
        this.noteName = noteName;
        this.realityNoteName = realityNoteName;
        this.noteOctave = noteOctave;
        this.slurPosition = slurPosition;
        this.chromaticPosition = 0;
        this.isNaturalSign = isNaturalSign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getRealityNoteId() {
        return realityNoteId;
    }

    public void setRealityNoteId(int realityNoteId) {
        this.realityNoteId = realityNoteId;
    }

    public int getChordId() {
        return chordId;
    }

    public void setChordId(int chordId) {
        this.chordId = chordId;
    }

    public int getChordPosition() {
        return chordPosition;
    }

    public void setChordPosition(int chordPosition) {
        this.chordPosition = chordPosition;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getRealityNoteName() {
        return realityNoteName;
    }

    public void setRealityNoteName(String realityNoteName) {
        this.realityNoteName = realityNoteName;
    }

    public int getNoteOctave() {
        return noteOctave;
    }

    public void setNoteOctave(int noteOctave) {
        this.noteOctave = noteOctave;
    }

    public int getSlurPosition() {
        return slurPosition;
    }

    public void setSlurPosition(int slurPosition) {
        this.slurPosition = slurPosition;
    }

    public int getChromaticPosition() {
        return chromaticPosition;
    }

    public void setChromaticPosition(int chromaticPosition) {
        this.chromaticPosition = chromaticPosition;
    }

    public boolean isNaturalSign() {
        return isNaturalSign;
    }

    public void setNaturalSign(boolean naturalSign) {
        isNaturalSign = naturalSign;
    }

    public float calculateTotalSlurredDuration(List<Measure> measures, ChordNote startChordNote) {
        float totalDuration = 0;
        boolean slurActive = false;
        int remainingSlurs = startChordNote.slurPosition;
        if (remainingSlurs <= 0) return totalDuration;

        for (Measure measure : measures) {
            for (Chord chord : measure.getChords()) {
                if (chord.getChordNotes().contains(startChordNote)) {
                    slurActive = true;
                }
                if (slurActive) {
                    totalDuration += chord.getDuration();
                    remainingSlurs -= chord.getChordNotes().size();
                }
                if (remainingSlurs <= 0) return totalDuration;
            }
        }
        return totalDuration;
    }

    public ChordNote findChordNoteWithTargetSlurPosition(List<Measure> measures, ChordNote startChordNote) {
        boolean slurActive = false;
        int remainingSlurs = startChordNote.slurPosition;

        for (Measure measure : measures) {
            for (Chord chord : measure.getChords()) {
                for (ChordNote chordNote : chord.getChordNotes()) {
                    if (slurActive || chordNote.equals(startChordNote)) {
                        slurActive = true;

                        if (chordNote.equals(startChordNote)) {
                            remainingSlurs = startChordNote.slurPosition;
                        } else {
                            remainingSlurs--;
                        }

                        if (remainingSlurs == 0) {
                            return chordNote;
                        }
                    }
                }
            }
        }
        return null; // Return null if the target slurPosition is not found
    }
    public Chord findChordWithTargetSlurPosition(List<Measure> measures, ChordNote startChordNote) {
        boolean slurActive = false;
        int remainingSlurs = startChordNote.slurPosition;

        for (Measure measure : measures) {
            for (Chord chord : measure.getChords()) {
                for (ChordNote chordNote : chord.getChordNotes()) {
                    if (slurActive || chordNote.equals(startChordNote)) {
                        slurActive = true;

                        if (chordNote.equals(startChordNote)) {
                            remainingSlurs = startChordNote.slurPosition;
                        } else {
                            remainingSlurs--;
                        }

                        if (remainingSlurs == 0) {
                            return chord;
                        }
                    }
                }
            }
        }
        return null; // Return null if the target slurPosition is not found
    }


}