package com.example.pianotutorial.features.components.helpers;

import android.media.midi.MidiOutputPort;

public interface MidiAware {

    void onDeviceOpened(MidiOutputPort midiOutputPort);
}
