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

    @SerializedName("clef")
    private int clef;

    @SerializedName("chords")
    private List<Chord> chords;
    private List<BeamValue> beams;

    public Measure() {
        id = 0;
        sheetId = 0;
        position = 0;
        clef = 0;
        chords = new ArrayList<>();
        beams = new ArrayList<>();
    }

    public Measure(int id, int sheetId, int position, int clef, List<Chord> chords) {
        this.id = id;
        this.sheetId = sheetId;
        this.position = position;
        this.clef = clef;
        this.chords = chords;
        this.beams = new ArrayList<>();
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

    public List<BeamValue> getBeams() {
        return beams;
    }

    public void setBeams(List<BeamValue> beams) {
        this.beams = beams;
    }

    public List<ChordNote> currentChordNotes() {
        List<ChordNote> noteList = new ArrayList<>();
        if(!chords.isEmpty()){
            for (Chord chord : chords) {
                int noteId;
                if (chord.getChordNotes().size() == 1) {
                    noteId = chord.adjustedNoteId(chord.getChordNotes().get(0).getNoteId());
                } else {
                    boolean stemUp = chord.isStemUp(clef);
                    noteId = stemUp ? chord.findHighestNoteIdWithoutFlip(clef) : chord.findLowestNoteIdWithoutFlip(clef);
                }
                if(!chord.getChordNotes().isEmpty()){
                    for(ChordNote note:chord.getChordNotes()){
                        if(note.getNoteId()==noteId){
                            noteList.add(note);
                        }
                    }
                }
            }
        }
        return noteList;
    }

    public float currentDuration(Chord currentChord) {
        float currentDuration = 0;
        if (chords.contains(currentChord)) {
            for (Chord chord : chords) {
                currentDuration += chord.getDuration();
                if (chord == currentChord) {
                    return currentDuration;
                }
            }
        }
        return 0;
    }


    // Phương thức để nhóm các nốt thành các beam, mỗi beam chỉ chứa tối đa 2 Chord
    public List<BeamValue> groupChordsIntoBeams() {
        List<BeamValue> beamValues = new ArrayList<>();
        for (int i = 0; i < chords.size() - 1; i++) {
            Chord chord1 = chords.get(i);
            Chord chord2 = chords.get(i + 1);

            // Kiểm tra nếu cả hai chord có thể beamed và có duration bằng nhau
            if (chord1.getDuration() == chord2.getDuration() && (!chord1.getChordNotes().isEmpty())) {
                if(chord1.getDuration() < 1 && chord1.getDuration() >= 0.5){
                    beamValues.add(new BeamValue(chord1,chord2));
                    i++;
                }
                else if(chord1.getDuration() < 0.5 && chord1.getDuration() >= 0.25){
                    beamValues.add(new BeamValue(chord1,chord2));
                    i++;
                }
            }
        }
        return beamValues;
    }
}
