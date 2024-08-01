package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChordNote {
    @SerializedName("id")
    private int id;

    @SerializedName("noteId")
    private int noteId;

    @SerializedName("chordId")
    private int chordId;

    @SerializedName("chordPosition")
    private int chordPosition;

    @SerializedName("noteName")
    private String noteName;

    @SerializedName("notePitch")
    private String notePitch;

    @SerializedName("noteOctave")
    private int noteOctave;

    @SerializedName("slurPosition")
    private int slurPosition;

    public ChordNote() {
        id = 0;
        noteId = 0;
        chordId = 0;
        chordPosition = 0;
        noteName = "";
        notePitch = "";
        noteOctave = 0;
        slurPosition = 0;
    }

    public ChordNote(int id, int noteId, int chordId, int chordPosition, String noteName, String notePitch, int noteOctave, int slurPosition) {
        this.id = id;
        this.noteId = noteId;
        this.chordId = chordId;
        this.chordPosition = chordPosition;
        this.noteName = noteName;
        this.notePitch = notePitch;
        this.noteOctave = noteOctave;
        this.slurPosition = slurPosition;
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

    public String getNotePitch() {
        return notePitch;
    }

    public void setNotePitch(String notePitch) {
        this.notePitch = notePitch;
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

}