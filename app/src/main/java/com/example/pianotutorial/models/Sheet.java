package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    @SerializedName("id")
    private int id;

    @SerializedName("songId")
    private int songId;

    @SerializedName("songTitle")
    private String songTitle;

    @SerializedName("topSignature")
    private int topSignature;

    @SerializedName("bottomSignature")
    private int bottomSignature;

    @SerializedName("instrumentId")
    private int instrumentId;

    @SerializedName("instrumentName")
    private String instrumentName;

    @SerializedName("sheetFile")
    private String sheetFile;

    @SerializedName("measures")
    private List<Measure> measures;

    @SerializedName("leftHandSheetId")
    private int leftHandSheetId;
    @SerializedName("leftHandSheet")
    private Sheet leftHandSheet;

    public Sheet(int id, int songId, String songTitle, int topSignature, int bottomSignature, int instrumentId, String instrumentName, String sheetFile, List<Measure> measures, int leftHandSheetId, Sheet leftHandSheet) {
        this.id = id;
        this.songId = songId;
        this.songTitle = songTitle;
        this.topSignature = topSignature;
        this.bottomSignature = bottomSignature;
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
        this.sheetFile = sheetFile;
        this.measures = measures;
        this.leftHandSheetId = leftHandSheetId;
        this.leftHandSheet = leftHandSheet;
    }

    public Sheet() {
        id = 0;
        songId = 0;
        songTitle = "";
        topSignature = 0;
        bottomSignature = 0;
        instrumentId = 0;
        instrumentName = "";
        sheetFile = "";
        measures = new ArrayList<>();
        leftHandSheetId=0;
        leftHandSheet=null;
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

    public int getTopSignature() {
        return topSignature;
    }

    public void setTopSignature(int topSignature) {
        this.topSignature = topSignature;
    }

    public int getBottomSignature() {
        return bottomSignature;
    }

    public void setBottomSignature(int bottomSignature) {
        this.bottomSignature = bottomSignature;
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

    public String getSheetFile() {
        return sheetFile;
    }

    public void setSheetFile(String sheetFile) {
        this.sheetFile = sheetFile;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public int getLeftHandSheetId() {
        return leftHandSheetId;
    }

    public void setLeftHandSheetId(int leftHandSheetId) {
        this.leftHandSheetId = leftHandSheetId;
    }

    public Sheet getLeftHandSheet() {
        return leftHandSheet;
    }

    public void setLeftHandSheet(Sheet leftHandSheet) {
        this.leftHandSheet = leftHandSheet;
    }
}