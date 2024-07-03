package com.example.pianotutorial.features;

import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pianotutorial.R;

public class MidiActivity extends AppCompatActivity {

    private static final String TAG = "MidiActivity";

    private MidiManager midiManager;
    private TextView checkTextView;

    private MidiManager.DeviceCallback deviceCallback = new MidiManager.DeviceCallback() {
        @Override
        public void onDeviceAdded(MidiDeviceInfo device) {
            super.onDeviceAdded(device);
            Log.d(TAG, "MIDI device added: " + device.toString());
            checkForMidiDevices();
        }

        @Override
        public void onDeviceRemoved(MidiDeviceInfo device) {
            super.onDeviceRemoved(device);
            Log.d(TAG, "MIDI device removed: " + device.toString());
            checkForMidiDevices();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midi);

        // Initialize the MIDI manager
        midiManager = (MidiManager) getSystemService(MIDI_SERVICE);

        // Initialize the views
        Button checkButton = findViewById(R.id.button_check);
        checkTextView = findViewById(R.id.check_text);

        // Set button click listener
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForMidiDevices();
            }
        });

        // Register the device callback
        midiManager.registerDeviceCallback(deviceCallback, null);

        // Initial check for currently connected MIDI devices
        checkForMidiDevices();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the device callback
        midiManager.unregisterDeviceCallback(deviceCallback);
    }

    private void checkForMidiDevices() {
        MidiDeviceInfo[] devices = midiManager.getDevices();
        boolean isConnected = devices.length > 0;
        checkTextView.setText(isConnected ? "True" : "False");
    }
}
