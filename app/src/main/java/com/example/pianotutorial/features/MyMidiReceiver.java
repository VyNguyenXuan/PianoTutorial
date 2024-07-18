package com.example.pianotutorial.features;

import android.media.midi.MidiReceiver;
import android.util.Log;

import java.io.IOException;

public class MyMidiReceiver extends MidiReceiver {
    private static final String TAG = "MyMidiReceiver";

    @Override
    public void onSend(byte[] data, int offset, int count, long timestamp) throws IOException {
        if (data.length >= 3) {
            int status = data[0] & 0xFF;
            int note = data[1] & 0xFF;
            int velocity = data[2] & 0xFF;

            if (status == 0x90 && velocity > 0) {
                // Note on
                Log.d(TAG, "Note on: " + note);
                playSound(note);
            } else if (status == 0x80 || (status == 0x90 && velocity == 0)) {
                // Note off
                Log.d(TAG, "Note off: " + note);
                stopSound(note);
            }
        }
    }

    private void playSound(int note) {
        // Implement logic to play sound
    }

    private void stopSound(int note) {
        // Implement logic to stop sound
    }


}

