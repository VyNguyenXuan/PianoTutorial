package com.example.pianotutorial.features.playscreen.activities;

import android.media.MediaPlayer;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.exoplayer.ExoPlayer;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.databinding.ActivityPlayscreenBinding;
import com.example.pianotutorial.features.components.helpers.MidiAware;
import com.example.pianotutorial.features.components.helpers.MidiHandler;
import com.example.pianotutorial.features.components.helpers.MidiNotesReceiver;
import com.example.pianotutorial.features.components.helpers.Note;
import com.example.pianotutorial.features.components.helpers.NoteActionListener;
import com.example.pianotutorial.features.playscreen.eventhandlers.PlayScreenEventHandler;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;

import org.billthefarmer.mididriver.GeneralMidiConstants;
import org.billthefarmer.mididriver.MidiConstants;
import org.billthefarmer.mididriver.MidiDriver;
import org.billthefarmer.mididriver.ReverbConstants;

import java.util.Objects;

public class PlayScreenActivity extends AppCompatActivity implements MidiAware, CompoundButton.OnCheckedChangeListener, MidiDriver.OnMidiStartListener, NoteActionListener {

    private static final String TAG = "PlayScreenActivity";
    private PlayScreenEventHandler playScreenEventHandler;
    private PlayScreenViewModel playScreenViewModel;

    private ActivityPlayscreenBinding activityPlayscreenBinding;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private TextView countdownTextView;

    private MidiManager midiManager;
    private MidiHandler midiHandler;

    protected MidiDriver midi;
    protected MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityPlayscreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_playscreen);
        playScreenViewModel = new ViewModelProvider(this).get(PlayScreenViewModel.class);
        playScreenEventHandler = new PlayScreenEventHandler(playScreenViewModel, this);

        activityPlayscreenBinding.setViewModel(playScreenViewModel);
        activityPlayscreenBinding.setEventHandler(playScreenEventHandler);
        activityPlayscreenBinding.setLifecycleOwner(this);

        countdownTextView = activityPlayscreenBinding.getRoot().findViewById(R.id.countdownText);

        playScreenViewModel.preparePlayer(R.raw.fur_elise_easy_ver, getPackageName());
        setupObservers();
        initializeMIDI();

        if (midi != null) {
            midi.setOnMidiStartListener(this);
        }
    }

    private void setupObservers() {
        playScreenViewModel.getSheetList().observe(this, currentSheet -> {
            if (currentSheet != null) {
                activityPlayscreenBinding.musicView.setMeasures(currentSheet.get(4).getMeasures(), currentSheet.get(5).getMeasures());
                activityPlayscreenBinding.musicView.startDrawing(System.currentTimeMillis());
            }
        });

        playScreenViewModel.getIsPlayed().observe(this, isPlayed -> {
            if (isPlayed != null) {
                if (!isPlayed) {
                    handlePause();
                } else {
                    startCountdown();
                }
            }
        });

        playScreenViewModel.getSpeed().observe(this, this::updateSpeed);
    }

    private void handlePause() {
        activityPlayscreenBinding.opacityView.setVisibility(View.VISIBLE);
        activityPlayscreenBinding.playImage.setBackgroundResource(R.drawable.vector_play_circle);
        GlobalVariables.SPEED = 0;
        playScreenViewModel.pausePlayer();
    }

    private void updateSpeed(float speed) {
        int speed1Res = R.drawable.white_border;
        int speed2Res = R.drawable.white_border;
        int speed3Res = R.drawable.white_border;

        int speed1TextColor = R.color.white;
        int speed2TextColor = R.color.white;
        int speed3TextColor = R.color.white;

        int audioResourceId = R.raw.fur_elise_easy_ver;

        if (speed == 0.6f) {
            speed1Res = R.color.white;
            speed1TextColor = R.color.black;
            audioResourceId = R.raw.fur_elise_easy_ver_06;
        } else if (speed == 0.8f) {
            speed2Res = R.color.white;
            speed2TextColor = R.color.black;
            audioResourceId = R.raw.fur_elise_easy_ver_08;
        } else if (speed == 1f) {
            speed3Res = R.color.white;
            speed3TextColor = R.color.black;
        }

        activityPlayscreenBinding.speed1Layout.setBackgroundResource(speed1Res);
        activityPlayscreenBinding.speed2Layout.setBackgroundResource(speed2Res);
        activityPlayscreenBinding.speed3Layout.setBackgroundResource(speed3Res);

        activityPlayscreenBinding.speed1Text.setTextColor(ContextCompat.getColor(this, speed1TextColor));
        activityPlayscreenBinding.speed2Text.setTextColor(ContextCompat.getColor(this, speed2TextColor));
        activityPlayscreenBinding.speed3Text.setTextColor(ContextCompat.getColor(this, speed3TextColor));

        playScreenViewModel.preparePlayer(audioResourceId, getPackageName());
    }

    private void initializeMIDI() {
        midiManager = (MidiManager) getSystemService(MIDI_SERVICE);
        midi = MidiDriver.getInstance(this);

        midiManager.registerDeviceCallback(deviceCallback, null);
        checkForMidiDevices();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (midi != null) midi.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (midi != null) midi.stop();
        if (player != null) player.stop();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        midi.setReverb(isChecked ? ReverbConstants.CHAMBER : ReverbConstants.OFF);
    }

    @Override
    public void onMidiStart() {
        sendMidi(MidiConstants.PROGRAM_CHANGE, GeneralMidiConstants.ACOUSTIC_GRAND_PIANO);
    }

    protected void sendMidi(int... messages) {
        byte[] msg = new byte[messages.length];
        for (int i = 0; i < messages.length; i++) {
            msg[i] = (byte) messages[i];
        }
        midi.write(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        midiManager.unregisterDeviceCallback(deviceCallback);
        midiHandler.removeDevice();
    }

    private void checkForMidiDevices() {
        for (MidiDeviceInfo device : midiManager.getDevices()) {
            Log.d(TAG, "MIDI device available: " + device.toString());
            openMidiDevice(device);
        }
    }

    private final MidiManager.DeviceCallback deviceCallback = new MidiManager.DeviceCallback() {
        @Override
        public void onDeviceAdded(MidiDeviceInfo device) {
            Log.d(TAG, "MIDI device added: " + device.toString());
            openMidiDevice(device);
        }

        @Override
        public void onDeviceRemoved(MidiDeviceInfo device) {
            Log.d(TAG, "MIDI device removed: " + device.toString());
        }
    };

    private void openMidiDevice(MidiDeviceInfo deviceInfo) {
        midiManager.openDevice(deviceInfo, midiDevice -> {
            if (midiDevice == null) {
                Log.e(TAG, "Could not open MIDI device");
            } else {
                onDeviceSuccess(midiDevice);
            }
        }, null);
    }

    public void onDeviceSuccess(MidiDevice midiDevice) {
        Log.i(TAG, "Midi device has been connected");
        MidiOutputPort midiOutputPort = midiDevice.openOutputPort(0);
        if (midiOutputPort != null) {
            midiOutputPort.connect(new MidiNotesReceiver(this));
        }
    }

    @Override
    public void guessNoteAction(Note note, boolean forceRightGuess) {
        Log.d(TAG, "Note pressed: " + note.toString());
        sendMidi(MidiConstants.NOTE_ON, note.getNoteId(), 63);
        activityPlayscreenBinding.musicView.updateWhiteKeyIndices(note, true);
        activityPlayscreenBinding.musicView.updateBlackKeyIndices(note, true);
        activityPlayscreenBinding.musicView.getNotesAndMeasuresDrawer().setCorrectNoteAction(note);
        activityPlayscreenBinding.musicView.getNotesAndMeasuresDrawerLeftHand().setCorrectNoteAction(note);

    }

    @Override
    public void stopGuessNoteAction(Note note) {
        sendMidi(MidiConstants.NOTE_OFF, note.getNoteId(), 0);
        activityPlayscreenBinding.musicView.updateWhiteKeyIndices(note, false);
        activityPlayscreenBinding.musicView.updateBlackKeyIndices(note, false);
        activityPlayscreenBinding.musicView.getNotesAndMeasuresDrawer().setCorrectNoteAction(null);
        activityPlayscreenBinding.musicView.getNotesAndMeasuresDrawerLeftHand().setCorrectNoteAction(null);
    }

    private void startCountdown() {
        countdownTextView.setVisibility(View.VISIBLE);
        GlobalVariables.SPEED = 0;

        handler.post(new Runnable() {
            int countdown = 3;

            @Override
            public void run() {
                if (countdown > 0) {
                    countdownTextView.setText(String.valueOf(countdown));
                    countdown--;
                    handler.postDelayed(this, 1000);
                } else {
                    countdownTextView.setVisibility(View.GONE);
                    activityPlayscreenBinding.opacityView.setVisibility(View.GONE);
                    activityPlayscreenBinding.playImage.setBackgroundResource(R.drawable.vector_pause_circle);
                    GlobalVariables.SPEED = Objects.requireNonNull(playScreenViewModel.getSpeed().getValue());
                    startUpdatingStaff();
                    playScreenEventHandler.onInitial();
                    ExoPlayer player = playScreenViewModel.getPlayer();
                    if (player.getPlaybackState() == ExoPlayer.STATE_ENDED) {
                        playScreenViewModel.setPlaybackPosition(0);
                        player.seekTo(playScreenViewModel.getPlaybackPosition());
                    }
                    player.play();
                }
            }
        });
    }

    private void startUpdatingStaff() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                activityPlayscreenBinding.musicView.updateView();
                handler.postDelayed(this, 8);
            }
        });
    }

    @Override
    public void onDeviceOpened(MidiOutputPort midiOutputPort) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
