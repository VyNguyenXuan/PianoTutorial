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

    @SerializedName("rightMeasures")
    private List<Measure> rightMeasures;

    @SerializedName("leftMeasures")
    private List<Measure> leftMeasures;

    public Sheet(int id, int songId, String songTitle, int topSignature, int bottomSignature, int instrumentId, String instrumentName, String sheetFile, List<Measure> rightMeasures, List<Measure> leftMeasures) {
        this.id = id;
        this.songId = songId;
        this.songTitle = songTitle;
        this.topSignature = topSignature;
        this.bottomSignature = bottomSignature;
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
        this.sheetFile = sheetFile;
        this.rightMeasures = rightMeasures;
        this.leftMeasures = leftMeasures;
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

    public List<Measure> getRightMeasures() {
        return rightMeasures;
    }

    public void setRightMeasures(List<Measure> rightMeasures) {
        this.rightMeasures = rightMeasures;
    }

    public List<Measure> getLeftMeasures() {
        return leftMeasures;
    }

    public void setLeftMeasures(List<Measure> leftMeasures) {
        this.leftMeasures = leftMeasures;
    }
}