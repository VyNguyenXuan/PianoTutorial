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

    @SerializedName("keySignature")
    private int keySignature;

    @SerializedName("rightSymbol")
    private String rightSymbol;

    @SerializedName("leftSymbol")
    private String leftSymbol;

    private List<Measure> rightHandMeasures;

    private List<Measure> leftHandMeasures;

    public Sheet(int id, int songId, String songTitle, int topSignature, int bottomSignature, int instrumentId, String instrumentName, String sheetFile, int keySignature, String rightSymbol, String leftSymbol) {
        this.id = id;
        this.songId = songId;
        this.songTitle = songTitle;
        this.topSignature = topSignature;
        this.bottomSignature = bottomSignature;
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
        this.sheetFile = sheetFile;
        this.keySignature = keySignature;
        this.rightSymbol = rightSymbol;
        this.leftSymbol = leftSymbol;
        this.leftHandMeasures = processMeasures(leftSymbol);
        this.rightHandMeasures = processMeasures(rightSymbol);

    }

    public List<Measure> processMeasures(String sheetString) {
        if (sheetString == null || sheetString.isEmpty()) {
            return new ArrayList<>();
        }
        String[] measureStrings = sheetString.split("/");
        List<Measure> measures = new ArrayList<>();

        for (int i = 0; i < measureStrings.length; i++) {
            boolean isFClef = measureStrings[i].startsWith("F ");
            int clefValue = isFClef ? 1 : 0;
            if (isFClef) {
                measureStrings[i] = measureStrings[i].substring(2);
            }
            String[] chordStrings = measureStrings[i].split(" ");
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

                    int noteId = NoteMapper.getNoteId(noteName);
                    int realityNoteId = realityNoteId(noteId, keySignature, getKeySignatureList(keySignature));
                    String realityNotename = NoteMapper.getNoteName(realityNoteId);
                    chordNotes.add(new ChordNote(k + 1, noteId, realityNoteId, j + 1, j + 1, noteName, realityNotename, octave, slurPosition));
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

    public int getKeySignature() {
        return keySignature;
    }

    public void setKeySignature(int keySignature) {
        this.keySignature = keySignature;
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

    public List<Measure> getRightHandMeasures() {
        return processMeasures(rightSymbol);
    }

    public void setRightHandMeasures(List<Measure> rightHandMeasures) {
        this.rightHandMeasures = rightHandMeasures;
    }

    public List<Measure> getLeftHandMeasures() {
        return processMeasures(leftSymbol);
    }

    public void setLeftHandMeasures(List<Measure> leftHandMeasures) {
        this.leftHandMeasures = leftHandMeasures;
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

    public List<Integer> getKeySignatureList(int keySignature) {
        List<Integer> keySignatureList = new ArrayList<>();
        if (keySignature > 0) {
            keySignatureList.add(4);
        }
        if (keySignature > 1) {
            keySignatureList.add(1);
        }
        if (keySignature > 2) {
            keySignatureList.add(5);
        }
        if (keySignature > 3) {
            keySignatureList.add(2);
        }
        if (keySignature > 4) {
            keySignatureList.add(6);
        }
        if (keySignature > 5) {
            keySignatureList.add(3);
        }
        if (keySignature > 6) {
            keySignatureList.add(7);
        }
        if (keySignature < 0) {
            keySignatureList.add(7);
        }
        if (keySignature < -1) {
            keySignatureList.add(3);
        }
        if (keySignature < -2) {
            keySignatureList.add(6);
        }
        if (keySignature < -3) {
            keySignatureList.add(2);
        }
        if (keySignature < -4) {
            keySignatureList.add(5);
        }
        if (keySignature < -5) {
            keySignatureList.add(1);
        }
        if (keySignature < -6) {
            keySignatureList.add(4);
        }
        return keySignatureList;
    }

    public int getNoteIdFromFlatToSharp(int noteId) {
        int currentNoteId = noteId;
        if (currentNoteId > 56 && currentNoteId < 113) {
            currentNoteId += 55;
        }
        return currentNoteId;
    }

    public int realityNoteId(int noteId, int keySignature, List<Integer> keySignatureList) {
        int currentNoteId = getNoteIdFromFlatToSharp(noteId);
        if (keySignatureList.contains(currentNoteId % 7) || (keySignatureList.contains(7) && (currentNoteId % 7) == 0)) {
            if (keySignature < 0) {
                if (currentNoteId <= 56) {
                    currentNoteId += 111;
                } else if (currentNoteId > 112) {
                    currentNoteId -= 112;
                }
            } else if (keySignature > 0) {
                if (currentNoteId <= 56) {
                    currentNoteId += 112;
                } else if (currentNoteId > 112) {
                    currentNoteId -= 111;
                }
            }
        }


        return currentNoteId;
    }
}