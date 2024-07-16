package com.example.pianotutorial.features.playscreen.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.exoplayer.ExoPlayer;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.GlobalVariables;
import com.example.pianotutorial.databinding.ActivityPlayscreenBinding;
import com.example.pianotutorial.features.playscreen.eventhandlers.PlayScreenEventHandler;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;

import java.util.Objects;

public class PlayScreenActivity extends AppCompatActivity {

    private PlayScreenEventHandler playScreenEventHandler;
    private PlayScreenViewModel playScreenViewModel;

    private ActivityPlayscreenBinding activityPlayscreenBinding;
    private final Handler handler = new Handler();
    private TextView countdownTextView;

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

        int resourceId = R.raw.fur_elise_easy_ver; // Replace 'your_audio_file' with your actual file name
        playScreenViewModel.preparePlayer(resourceId, getPackageName());

        playScreenViewModel.getSheetList().observe(this, currentSheet -> {
            if (currentSheet != null) {
                activityPlayscreenBinding.musicView.setMeasures(currentSheet.get(4).getMeasures(),currentSheet.get(5).getMeasures());
                activityPlayscreenBinding.musicView.startDrawing(System.currentTimeMillis());
            }
        });

        playScreenViewModel.getIsPlayed().observe(this, isPlayed -> {
            if (isPlayed != null) {
                if (!isPlayed) {
                    activityPlayscreenBinding.opacityView.setVisibility(View.VISIBLE);
                    activityPlayscreenBinding.playImage.setBackgroundResource(R.drawable.vector_play_circle);
                    GlobalVariables.SPEED = 0; // Stop the animation
                    playScreenViewModel.pausePlayer();
                } else {
                    startCountdown();
                }
            }
        });

        playScreenViewModel.getSpeed().observe(this, speed -> {
            if(speed == 0.6f){
                activityPlayscreenBinding.speed1Layout.setBackgroundResource(R.color.white);
                activityPlayscreenBinding.speed2Layout.setBackgroundResource(R.drawable.white_border);
                activityPlayscreenBinding.speed3Layout.setBackgroundResource(R.drawable.white_border);
                activityPlayscreenBinding.speed1Text.setTextColor(ContextCompat.getColor(this, R.color.black));
                activityPlayscreenBinding.speed2Text.setTextColor(ContextCompat.getColor(this, R.color.white));
                activityPlayscreenBinding.speed3Text.setTextColor(ContextCompat.getColor(this, R.color.white));
                playScreenViewModel.preparePlayer(R.raw.fur_elise_easy_ver_06, getPackageName());
            }
            else if(speed == 0.8f){
                activityPlayscreenBinding.speed1Layout.setBackgroundResource(R.drawable.white_border);
                activityPlayscreenBinding.speed2Layout.setBackgroundResource(R.color.white);
                activityPlayscreenBinding.speed3Layout.setBackgroundResource(R.drawable.white_border);
                activityPlayscreenBinding.speed1Text.setTextColor(ContextCompat.getColor(this, R.color.white));
                activityPlayscreenBinding.speed2Text.setTextColor(ContextCompat.getColor(this, R.color.black));
                activityPlayscreenBinding.speed3Text.setTextColor(ContextCompat.getColor(this, R.color.white));
                playScreenViewModel.preparePlayer(R.raw.fur_elise_easy_ver_08, getPackageName());
            }
            else if(speed == 1f){
                activityPlayscreenBinding.speed1Layout.setBackgroundResource(R.drawable.white_border);
                activityPlayscreenBinding.speed2Layout.setBackgroundResource(R.drawable.white_border);
                activityPlayscreenBinding.speed3Layout.setBackgroundResource(R.color.white);
                activityPlayscreenBinding.speed1Text.setTextColor(ContextCompat.getColor(this, R.color.white));
                activityPlayscreenBinding.speed2Text.setTextColor(ContextCompat.getColor(this, R.color.white));
                activityPlayscreenBinding.speed3Text.setTextColor(ContextCompat.getColor(this, R.color.black));
                playScreenViewModel.preparePlayer(R.raw.fur_elise_easy_ver, getPackageName());

            }
        });
    }

    private void startCountdown() {
        countdownTextView.setVisibility(View.VISIBLE);
        GlobalVariables.SPEED = 0; // Pause animation during countdown
        handler.postDelayed(new Runnable() {
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
                    GlobalVariables.SPEED = Objects.requireNonNull(activityPlayscreenBinding.getViewModel().getSpeed().getValue()); // Resume the desired speed
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
        }, 0);
    }

    private void startUpdatingStaff() {
        Runnable updateStaff = new Runnable() {
            @Override
            public void run() {
                activityPlayscreenBinding.musicView.updateView();
                handler.postDelayed(this, 8); // Refresh the view every 8 milliseconds (approx. 120 FPS)
            }
        };
        handler.post(updateStaff);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
