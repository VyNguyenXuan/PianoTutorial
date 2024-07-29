package com.example.pianotutorial.features.components.helpers;

import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiOutputPort;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pianotutorial.R;

import java.io.IOException;

public class MidiHandler {

    private static final String TAG = "MidiHandler";
    private MidiDevice parentDevice;
    private final MidiAware midiAware;
    private final MidiManager midiManager;
    private final View view;

    private final TextView deviceStatus;

    public MidiHandler(final MidiAware midiAware, MidiManager midiManager, View view) {
        this.midiAware = midiAware;
        this.midiManager = midiManager;
        this.view = view;
        this.deviceStatus = view.findViewById(R.id.status);

        String deviceInfos = getDefaultDeviceInfos(view);

        deviceStatus.setText(R.string.midi_device_status_disconnected);

    }

    @NonNull
    private String getDefaultDeviceInfos(View view) {
        return view.getContext().getString(
                R.string.midi_device_infos,
                0,
                "N/A",
                0,
                0);
    }

    public void removeDevice() {
        Log.d(TAG, "Closing device: ");
        try {
            if (this.parentDevice != null) {
                Log.d(TAG, "Device to close : " + parentDevice.getInfo().getId());
                this.parentDevice.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerMidiHandler() {
        Log.d(TAG, "On Start");
        Handler handler = new Handler();

        midiManager.registerDeviceCallback(new MidiManager.DeviceCallback() {
            @Override
            public void onDeviceAdded(final MidiDeviceInfo device) {
                openDevice(device, midiManager);
            }

            @Override
            public void onDeviceRemoved(MidiDeviceInfo device) {
                deviceStatus.setText(R.string.midi_device_status_disconnected);

                removeDevice();

            }
        }, handler);
    }

    public void openConnectedDevice() {
        for (MidiDeviceInfo info : midiManager.getDevices()) {
            openDevice(info, midiManager);
            break;
        }
    }

    private void openDevice(MidiDeviceInfo deviceInfo, final MidiManager midiManager) {
        final TextView statusText = view.findViewById(R.id.status);

        Log.d(TAG, "Opening device: ");

        final int deviceId = deviceInfo.getId();
        final String deviceManufacturer = deviceInfo.getProperties().getString(MidiDeviceInfo.PROPERTY_MANUFACTURER);

        Log.d(TAG, "Device: " + deviceId + " " + deviceManufacturer);

        midiManager.openDevice(deviceInfo, device -> {
                    if (device == null) {
                        statusText.setText(R.string.midi_device_status_error);
                    } else {
                        parentDevice = device;
                        statusText.setText(R.string.midi_device_status_connected);

                        MidiDeviceInfo.PortInfo[] portInfos = device.getInfo().getPorts();


                        for (MidiDeviceInfo.PortInfo portInfo : portInfos) {
                            Log.d(TAG, "Cycling port " + portInfo.getPortNumber());
                            if (portInfo.getType() == MidiDeviceInfo.PortInfo.TYPE_OUTPUT) {
                                Log.d(TAG, "Found OUTPUT port");


                                final MidiOutputPort outputPort = device.openOutputPort(portInfo.getPortNumber());

                                if (midiAware != null) {
                                    midiAware.onDeviceOpened(outputPort);
                                }
                                break;
                            }
                        }
                    }
                }, new Handler(Looper.getMainLooper())
        );
    }
}
