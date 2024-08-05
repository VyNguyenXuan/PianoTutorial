package com.example.pianotutorial.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @SerializedName("rightSymbol")
    private String rightSymbol;

    @SerializedName("leftSymbol")
    private String leftSymbol;

    private List<Measure> rightMeasures;

    private List<Measure> leftMeasures;

    public Sheet(int id, int songId, String songTitle, int topSignature, int bottomSignature, int instrumentId, String instrumentName, String sheetFile, String rightSymbol, String leftSymbol) {
        this.id = id;
        this.songId = songId;
        this.songTitle = songTitle;
        this.topSignature = topSignature;
        this.bottomSignature = bottomSignature;
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
        this.sheetFile = sheetFile;
        this.rightSymbol = rightSymbol;
        this.leftSymbol = leftSymbol;
        this.leftMeasures = processMeasures(leftSymbol);
        this.rightMeasures = processMeasures(rightSymbol);

    }

    public List<Measure> processMeasures(String sheetString) {
        if (sheetString == null || sheetString.isEmpty()) {
            return new ArrayList<>();
        }
        String[] measureStrings = sheetString.split("/");
        List<Measure> measures = new ArrayList<>();

        for (int i = 0; i < measureStrings.length; i++) {
            String[] chordStrings = measureStrings[i].split(" ");
            int clefValue = measureStrings[i].startsWith("F ") ? 1 : 0;
            List<Chord> chords = new ArrayList<>();

            for (int j = 0; j < chordStrings.length; j++) {
                List<ChordNote> chordNotes = new ArrayList<>();
                List<String> chordNoteStrings = splitChordString(chordStrings[j]);

                for (int k = 0; k < chordNoteStrings.size(); k++) {
                    String chordNoteString = chordNoteStrings.get(k);
                    String noteName;
                    int slurPosition = 0;
                    int octave = 0;

                    if (chordNoteString.contains("-")) {
                        String[] parts = chordNoteString.split("-");
                        noteName = parts[0];
                        slurPosition = Integer.parseInt(parts[1]);
                    } else {
                        noteName = chordNoteString;
                    }

                    if (noteName.length() > 1) {
                        octave = Character.getNumericValue(noteName.charAt(1));
                    }

                    int noteId = getNoteId(noteName);
                    chordNotes.add(new ChordNote(k + 1, noteId, j + 1, j + 1, noteName, noteName, octave, slurPosition));
                }

                float duration = 0;
                int underscoreIndex = chordStrings[j].indexOf('_');
                if (underscoreIndex != -1) {
                    duration = Float.parseFloat(chordStrings[j].substring(underscoreIndex + 1));

                }


                if (chordStrings[j].startsWith("P")) {
                    chords.add(new Chord(j + 1, duration, i + 1, j + 1, chordNotes));
                } else {
                    chords.add(new Chord(j + 1, duration, i + 1, j + 1, chordNotes));
                }
            }
            measures.add(new Measure(i + 1, id, i + 1, clefValue, chords));
        }

        return measures;
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

    public String getRightSymbol() {
        return rightSymbol;
    }

    public void setRightSymbol(String rightSymbol) {
        this.rightSymbol = rightSymbol;
    }

    public String getLeftSymbol() {
        return leftSymbol;
    }

    public void setLeftSymbol(String leftSymbol) {
        this.leftSymbol = leftSymbol;
    }

    public List<Measure> getRightMeasures() {
        return processMeasures(rightSymbol);

    }

    public void setRightMeasures(List<Measure> rightMeasures) {
        this.rightMeasures = rightMeasures;
    }

    public List<Measure> getLeftMeasures() {
        return processMeasures(leftSymbol);
    }

    public void setLeftMeasures(List<Measure> leftMeasures) {
        this.leftMeasures = leftMeasures;
    }

    public List<String> splitChordString(String chordString) {
        List<String> chordNoteStrings = new ArrayList<>();
        Pattern pattern = Pattern.compile("[A-G][^A-G]*");
        Matcher matcher = pattern.matcher(chordString);
        while (matcher.find()) {
            String chordNote = matcher.group();
            int underscoreIndex = chordNote.indexOf('_');
            if (underscoreIndex != -1) {
                chordNote = chordNote.substring(0, underscoreIndex);
            }
            chordNoteStrings.add(chordNote);
        }
        return chordNoteStrings;
    }


    public int getNoteId(String noteName) {
        switch (noteName) {
            // Octave 1
            case "C1":
                return 1;
            case "D1":
                return 2;
            case "E1":
                return 3;
            case "F1":
                return 4;
            case "G1":
                return 5;
            case "A1":
                return 6;
            case "B1":
                return 7;
            case "C1b":
                return 57;
            case "D1b":
                return 58;
            case "E1b":
                return 59;
            case "F1b":
                return 60;
            case "G1b":
                return 61;
            case "A1b":
                return 62;
            case "B1b":
                return 63;
            case "C1#":
                return 113;
            case "D1#":
                return 114;
            case "E1#":
                return 115;
            case "F1#":
                return 116;
            case "G1#":
                return 117;
            case "A1#":
                return 118;
            case "B1#":
                return 119;

            // Octave 2
            case "C2":
                return 8;
            case "D2":
                return 9;
            case "E2":
                return 10;
            case "F2":
                return 11;
            case "G2":
                return 12;
            case "A2":
                return 13;
            case "B2":
                return 14;
            case "C2b":
                return 64;
            case "D2b":
                return 65;
            case "E2b":
                return 66;
            case "F2b":
                return 67;
            case "G2b":
                return 68;
            case "A2b":
                return 69;
            case "B2b":
                return 70;
            case "C2#":
                return 120;
            case "D2#":
                return 121;
            case "E2#":
                return 122;
            case "F2#":
                return 123;
            case "G2#":
                return 124;
            case "A2#":
                return 125;
            case "B2#":
                return 126;

            // Octave 3
            case "C3":
                return 15;
            case "D3":
                return 16;
            case "E3":
                return 17;
            case "F3":
                return 18;
            case "G3":
                return 19;
            case "A3":
                return 20;
            case "B3":
                return 21;
            case "C3b":
                return 71;
            case "D3b":
                return 72;
            case "E3b":
                return 73;
            case "F3b":
                return 74;
            case "G3b":
                return 75;
            case "A3b":
                return 76;
            case "B3b":
                return 77;
            case "C3#":
                return 127;
            case "D3#":
                return 128;
            case "E3#":
                return 129;
            case "F3#":
                return 130;
            case "G3#":
                return 131;
            case "A3#":
                return 132;
            case "B3#":
                return 133;

            // Octave 4
            case "C4":
                return 22;
            case "D4":
                return 23;
            case "E4":
                return 24;
            case "F4":
                return 25;
            case "G4":
                return 26;
            case "A4":
                return 27;
            case "B4":
                return 28;
            case "C4b":
                return 78;
            case "D4b":
                return 79;
            case "E4b":
                return 80;
            case "F4b":
                return 81;
            case "G4b":
                return 82;
            case "A4b":
                return 83;
            case "B4b":
                return 84;
            case "C4#":
                return 134;
            case "D4#":
                return 135;
            case "E4#":
                return 136;
            case "F4#":
                return 137;
            case "G4#":
                return 138;
            case "A4#":
                return 139;
            case "B4#":
                return 140;

            // Octave 5
            case "C5":
                return 29;
            case "D5":
                return 30;
            case "E5":
                return 31;
            case "F5":
                return 32;
            case "G5":
                return 33;
            case "A5":
                return 34;
            case "B5":
                return 35;
            case "C5b":
                return 85;
            case "D5b":
                return 86;
            case "E5b":
                return 87;
            case "F5b":
                return 88;
            case "G5b":
                return 89;
            case "A5b":
                return 90;
            case "B5b":
                return 91;
            case "C5#":
                return 141;
            case "D5#":
                return 142;
            case "E5#":
                return 143;
            case "F5#":
                return 144;
            case "G5#":
                return 145;
            case "A5#":
                return 146;
            case "B5#":
                return 147;

            // Octave 6
            case "C6":
                return 36;
            case "D6":
                return 37;
            case "E6":
                return 38;
            case "F6":
                return 39;
            case "G6":
                return 40;
            case "A6":
                return 41;
            case "B6":
                return 42;
            case "C6b":
                return 92;
            case "D6b":
                return 93;
            case "E6b":
                return 94;
            case "F6b":
                return 95;
            case "G6b":
                return 96;
            case "A6b":
                return 97;
            case "B6b":
                return 98;
            case "C6#":
                return 148;
            case "D6#":
                return 149;
            case "E6#":
                return 150;
            case "F6#":
                return 151;
            case "G6#":
                return 152;
            case "A6#":
                return 153;
            case "B6#":
                return 154;

            // Octave 7
            case "C7":
                return 43;
            case "D7":
                return 44;
            case "E7":
                return 45;
            case "F7":
                return 46;
            case "G7":
                return 47;
            case "A7":
                return 48;
            case "B7":
                return 49;
            case "C7b":
                return 99;
            case "D7b":
                return 100;
            case "E7b":
                return 101;
            case "F7b":
                return 102;
            case "G7b":
                return 103;
            case "A7b":
                return 104;
            case "B7b":
                return 105;
            case "C7#":
                return 155;
            case "D7#":
                return 156;
            case "E7#":
                return 157;
            case "F7#":
                return 158;
            case "G7#":
                return 159;
            case "A7#":
                return 160;
            case "B7#":
                return 161;

            // Octave 8
            case "C8":
                return 50;
            case "D8":
                return 51;
            case "E8":
                return 52;
            case "F8":
                return 53;
            case "G8":
                return 54;
            case "A8":
                return 55;
            case "B8":
                return 56;
            case "C8b":
                return 106;
            case "D8b":
                return 107;
            case "E8b":
                return 108;
            case "F8b":
                return 109;
            case "G8b":
                return 110;
            case "A8b":
                return 111;
            case "B8b":
                return 112;
            case "C8#":
                return 162;
            case "D8#":
                return 163;
            case "E8#":
                return 164;
            case "F8#":
                return 165;
            case "G8#":
                return 166;
            case "A8#":
                return 167;
            case "B8#":
                return 168;

            default:
                return -1; // or throw an exception if you prefer
        }
    }


}
