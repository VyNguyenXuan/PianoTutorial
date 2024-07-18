package com.example.pianotutorial.features.components.helpers;

public interface NoteActionListener {
    void guessNoteAction(Note note, boolean forceRightGuess);
    void stopGuessNoteAction(Note note);
}
