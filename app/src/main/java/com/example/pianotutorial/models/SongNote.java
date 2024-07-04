package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

public class SongNote {
    @SerializedName("id")
    private int id;

    @SerializedName("sheet_id")
    private int sheetID;

    @SerializedName("note_id")
    private int noteId;

    @SerializedName("note_name")
    private String noteName;

    @SerializedName("note_pitch")
    private String notePitch;

    @SerializedName("note_octave")
    private int noteOctave;

    @SerializedName("duration")
    private String duration;

    @SerializedName("measure_id")
    private int measureId;

    @SerializedName("position")
    private int position;


    public SongNote(int id, int sheetID, int noteId, String noteName, String notePitch, int noteOctave, String duration, int measureId, int position) {
        this.id = id;
        this.sheetID = sheetID;
        this.noteId = noteId;
        this.noteName = noteName;
        this.notePitch = notePitch;
        this.noteOctave = noteOctave;
        this.duration = duration;
        this.measureId = measureId;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSheetID() {
        return sheetID;
    }

    public void setSheetID(int sheetID) {
        this.sheetID = sheetID;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
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
}
