package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

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
    public ChordNote() {
        id = 0;
        noteId = 0;
        chordId = 0;
        chordPosition = 0;
        noteName = "";
        notePitch = "";
        noteOctave = 0;
    }

    public ChordNote(int id, int noteId, int chordId, int chordPosition, String noteName, String notePitch, int noteOctave) {
        this.id = id;
        this.noteId = noteId;
        this.chordId = chordId;
        this.chordPosition = chordPosition;
        this.noteName = noteName;
        this.notePitch = notePitch;
        this.noteOctave = noteOctave;
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
}