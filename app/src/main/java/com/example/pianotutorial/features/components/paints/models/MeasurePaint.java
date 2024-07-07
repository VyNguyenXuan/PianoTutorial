package com.example.pianotutorial.features.components.paints.models;

import com.example.pianotutorial.features.components.paints.models.Note;

import java.util.ArrayList;
import java.util.List;

public class MeasurePaint {
    private int id;
    private List<Note> notes;

    public MeasurePaint(int id) {
        this.id = id;
        this.notes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return notes;
    }
}
