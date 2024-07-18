package com.example.pianotutorial.features;

import android.media.midi.MidiOutputPort;

public interface MidiAware {

    void onDeviceOpened(MidiOutputPort midiOutputPort);
}
