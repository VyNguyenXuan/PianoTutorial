package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Measure {
    @SerializedName("id")
    private int id;

    @SerializedName("sheet_id")
    private int sheetId;

    @SerializedName("position")
    private int position;

    @SerializedName("song_notes")
    private List<SongNote> songNotes;

    public Measure(int id, int sheetId, int position, List<SongNote> songNotes) {
        this.id = id;
        this.sheetId = sheetId;
        this.position = position;
        this.songNotes = songNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSheetId() {
        return sheetId;
    }

    public void setSheetId(int sheetId) {
        this.sheetId = sheetId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<SongNote> getSongNotes() {
        return songNotes;
    }

    public void setSongNotes(List<SongNote> songNotes) {
        this.songNotes = songNotes;
    }
}
