package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Measure {
    @SerializedName("id")
    private int id;

    @SerializedName("sheetId")
    private int sheetId;

    @SerializedName("position")
    private int position;

    @SerializedName("chords")
    private List<Chord> chords;

    public Measure() {
        id = 0;
        sheetId = 0;
        position = 0;
        chords = new ArrayList<>();
    }

    public Measure(int id, int sheetId, int position, List<Chord> chords) {
        this.id = id;
        this.sheetId = sheetId;
        this.position = position;
        this.chords = chords;
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

    public List<Chord> getChords() {
        return chords;
    }

    public void setChords(List<Chord> chords) {
        this.chords = chords;
    }
}