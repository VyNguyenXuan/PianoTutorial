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
import android.widget.CompoundButton;
import android.widget.TextView;
import android.media.MediaPlayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.components.helpers.MidiAware;
import com.example.pianotutorial.features.components.helpers.MidiHandler;
import com.example.pianotutorial.features.components.helpers.Note;

import org.billthefarmer.mididriver.MidiDriver;
import org.billthefarmer.mididriver.MidiConstants;
import org.billthefarmer.mididriver.GeneralMidiConstants;
import org.billthefarmer.mididriver.ReverbConstants;

public class MidiActivity extends AppCompatActivity implements MidiAware, CompoundButton.OnCheckedChangeListener, MidiDriver.OnMidiStartListener {

    private static final String TAG = "MidiActivity";
    private MidiManager midiManager;
    private TextView checkTextView;
    private Handler mainHandler;
    private MidiHandler midiHandler;
    protected MidiDriver midi;
    protected MediaPlayer player;

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

        // Initialize the MIDI driver
        midi = MidiDriver.getInstance(this);

        // Set on midi start listener
        if (midi != null)
            midi.setOnMidiStartListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Start midi
        if (midi != null)
            midi.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Stop midi
        if (midi != null)
            midi.stop();

        // Stop player
        if (player != null)
            player.stop();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked)
            midi.setReverb(ReverbConstants.CHAMBER);
        else
            midi.setReverb(ReverbConstants.OFF);
    }

    @Override
    public void onMidiStart() {
        // Program change - piano
        sendMidi(MidiConstants.PROGRAM_CHANGE, GeneralMidiConstants.ACOUSTIC_GRAND_PIANO);

        // Get the config
        int config[] = midi.config();

    }

    protected void sendMidi(int m, int n) {
        byte msg[] = new byte[2];

        msg[0] = (byte) m;
        msg[1] = (byte) n;

        midi.write(msg);
    }

    protected void sendMidi(int m, int n, int v) {
        byte msg[] = new byte[3];

        msg[0] = (byte) m;
        msg[1] = (byte) n;
        msg[2] = (byte) v;

        midi.write(msg);
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
            //midiOutputPort.connect(new MidiNotesReceiver(this));
        }
    }

    public void stopGuessNote(Note note) {
        sendMidi(MidiConstants.NOTE_OFF, note.getNoteId(), 0);    }

    public void guessNote(final Note note, boolean forceRightGuess) {
        Log.d(TAG, "Note pressed: " + note.toString());
        sendMidi(MidiConstants.NOTE_ON, note.getNoteId(), 63);

    }

    @Override
    public void onDeviceOpened(MidiOutputPort midiOutputPort) {
        // Handle the event when a MIDI device is opened
    }
}
