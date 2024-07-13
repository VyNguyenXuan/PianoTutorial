package com.example.pianotutorial.features.playscreen.activities;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ActivityPlayscreenBinding;
import com.example.pianotutorial.features.playscreen.eventhandlers.PlayScreenEventHandler;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;


public class PlayScreenActivity extends AppCompatActivity {

    private PlayScreenEventHandler playScreenEventHandler;
    private ActivityPlayscreenBinding activityPlayscreenBinding;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityPlayscreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_playscreen);
        PlayScreenViewModel playScreenViewModel = new ViewModelProvider(this).get(PlayScreenViewModel.class);
        playScreenEventHandler = new PlayScreenEventHandler(playScreenViewModel, this);

        activityPlayscreenBinding.setViewModel(playScreenViewModel);
        activityPlayscreenBinding.setEventHandler(playScreenEventHandler);
        activityPlayscreenBinding.setLifecycleOwner(this);

        int resourceId = R.raw.theduck; // Replace 'your_audio_file' with your actual file name
        playScreenViewModel.preparePlayer(resourceId, getPackageName());

        playScreenViewModel.getSheetList().observe(this, sheetList -> {
            if (sheetList != null) {
                activityPlayscreenBinding.musicView.setMeasures(sheetList.get(0).getMeasures());
                activityPlayscreenBinding.musicView.startDrawing(System.currentTimeMillis());
            }
        });

        playScreenViewModel.getIsPlayed().observe(this, isplayed -> {
            if (isplayed) {
                startUpdatingStaff();
                playScreenEventHandler.onInitial();

            }
        });
    }

    private void startUpdatingStaff() {
        // Refresh the view every 16 milliseconds (approx. 60 FPS)
        Runnable updateStaff = new Runnable() {
            @Override
            public void run() {
                activityPlayscreenBinding.musicView.updateView();
                handler.postDelayed(this, 16); // Refresh the view every 16 milliseconds (approx. 60 FPS)
            }
        };
        handler.post(updateStaff);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playScreenEventHandler.clear();
    }
}
