package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Song {
    @SerializedName("id")
    private int id;

    @SerializedName("song_id")
    private int songId;

    @SerializedName("song_title")
    private String songTitle;

    @SerializedName("instrument_id")
    private int instrumentId;

    @SerializedName("instrument_name")
    private String instrumentName;

    @SerializedName("measures")
    private List<Measure> measures;

    public Song(int id, int songId, String songTitle, int instrumentId, String instrumentName, List<Measure> measures) {
        this.id = id;
        this.songId = songId;
        this.songTitle = songTitle;
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
        this.measures = measures;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(int instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }
}
