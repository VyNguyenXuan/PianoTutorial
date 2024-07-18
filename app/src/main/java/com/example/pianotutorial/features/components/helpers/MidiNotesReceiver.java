package com.example.pianotutorial.features.components.helpers;

import android.media.midi.MidiReceiver;
import android.util.Log;

import com.example.pianotutorial.features.playscreen.activities.PlayScreenActivity;

public class MidiNotesReceiver extends MidiReceiver {
    private static final String TAG = "MidiNotesReceiver";
    private final PlayScreenActivity activity;

    public MidiNotesReceiver(PlayScreenActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onSend(byte[] data, int offset, int count, long timestamp) {
        Log.d(TAG, "------ New Message ------");
        Log.d(TAG, "Size: " + count);
        Log.d(TAG, "Offset: " + offset);
        Log.d(TAG, "Type: " + format(data[offset]));

        for (int i = 0; i < count; i++) {
            int currentByte = data[offset] & 0xFF;

            if (currentByte >= 0x90 && currentByte < 0xA0) { // Note On
                Log.d(TAG, "Note On: " + format(data[offset]));
                Log.d(TAG, "Note Value: " + data[offset + 1]);
                Log.d(TAG, "Velocity: " + data[offset + 2]);
                Note note = new Note(data[offset + 1]);
                note.setNoteId(data[2]);
                activity.guessNoteAction(note, false);
                break;
            }

            if (currentByte >= 0x80 && currentByte < 0x90) { // Note Off
                Log.d(TAG, "Note Off: " + format(data[offset]));
                Log.d(TAG, "Note Value: " + data[offset + 1]);
                Log.d(TAG, "Velocity: " + data[offset + 2]);
                Note note = new Note(data[offset + 1]);
                note.setNoteId(data[2]);
                activity.stopGuessNoteAction(note);
                break;
            }
            ++offset;
        }
    }

    private String format(byte b) {
        return String.format("0x%02X", (b & 0xFF));
    }
}
