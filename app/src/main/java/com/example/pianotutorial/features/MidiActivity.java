package com.example.pianotutorial.features;

import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pianotutorial.R;

public class MidiActivity extends AppCompatActivity implements MidiAware {

    private static final String TAG = "MidiActivity";

    private MidiManager midiManager;
    private TextView checkTextView;
    private Handler mainHandler;
    private MidiHandler midiHandler;

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

        // Initialize the handler to update UI from other threads
        mainHandler = new Handler(Looper.getMainLooper());

        // Initialize MidiHandler
        midiHandler = new MidiHandler(this, midiManager, findViewById(R.id.midi_status_fragment));
        midiHandler.registerMidiHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the device callback
        midiManager.unregisterDeviceCallback(deviceCallback);
        // Clean up the MIDI handler
        midiHandler.removeDevice();
    }

    private void checkForMidiDevices() {
        MidiDeviceInfo[] devices = midiManager.getDevices();
        boolean isConnected = devices.length > 0;
        checkTextView.setText(isConnected ? "True" : "False");
        for (MidiDeviceInfo device : devices) {
            Log.d(TAG, "MIDI device available: " + device.toString());
            openMidiDevice(device);
        }
    }

    private final MidiManager.DeviceCallback deviceCallback = new MidiManager.DeviceCallback() {
        @Override
        public void onDeviceAdded(MidiDeviceInfo device) {
            super.onDeviceAdded(device);
            Log.d(TAG, "MIDI device added: " + device.toString());
            openMidiDevice(device);
            checkForMidiDevices();
        }

        @Override
        public void onDeviceRemoved(MidiDeviceInfo device) {
            super.onDeviceRemoved(device);
            Log.d(TAG, "MIDI device removed: " + device.toString());
            checkForMidiDevices();
        }
    };

    private void openMidiDevice(MidiDeviceInfo deviceInfo) {
        midiManager.openDevice(deviceInfo, new MidiManager.OnDeviceOpenedListener() {
            @Override
            public void onDeviceOpened(MidiDevice midiDevice) {
                if (midiDevice == null) {
                    Log.e(TAG, "Could not open MIDI device");
                } else {
                    onDeviceSuccess(midiDevice);
                }
            }
        }, null);
    }

    public void onDeviceSuccess(MidiDevice midiDevice) {
        Log.i(TAG, "Midi device has been connected");
        MidiOutputPort midiOutputPort = midiDevice.openOutputPort(0);
        if (midiOutputPort != null) {
            // Connect MidiNotesReceiver
            midiOutputPort.connect(new MidiNotesReceiver(this));
        }
    }

    public void stopGuessNote(Note note) {
        // Handle when a note is turned off
    }

    public void guessNote(final Note note, boolean forceRightGuess) {
        checkTextView.setText(note.getNotePitch().getLabel());
    }

    @Override
    public void onDeviceOpened(MidiOutputPort midiOutputPort) {
        // Handle the event when a MIDI device is opened
    }
}
