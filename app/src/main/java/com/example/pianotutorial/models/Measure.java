package com.example.pianotutorial.models;

import com.example.pianotutorial.constants.enums.Clef;
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
    @SerializedName("clef")
    private int clef;

    @SerializedName("chords")
    private List<Chord> chords;

    public Measure() {
        id = 0;
        sheetId = 0;
        position = 0;
        chords = new ArrayList<>();
    }

    public Measure(int id, int sheetId, int position, int clef, List<Chord> chords) {
        this.id = id;
        this.sheetId = sheetId;
        this.position = position;
        this.clef = clef;
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

    public int getClef() {
        return clef;
    }

    public void setClef(int clef) {
        this.clef = clef;
    }
}