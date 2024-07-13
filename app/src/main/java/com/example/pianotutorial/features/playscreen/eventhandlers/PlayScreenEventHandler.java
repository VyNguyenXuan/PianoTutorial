package com.example.pianotutorial.features.playscreen.eventhandlers;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import androidx.media3.exoplayer.ExoPlayer;

import com.example.pianotutorial.features.playscreen.servicehandlers.PlayScreenServiceHandler;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;

public class PlayScreenEventHandler {

    private final PlayScreenViewModel playScreenViewModel;
    private final PlayScreenServiceHandler playScreenServiceHandler;
    private Runnable updateStaff;
    private final Handler handler = new Handler();

    public PlayScreenEventHandler(PlayScreenViewModel viewModel, Context context) {
        this.playScreenViewModel = viewModel;
        playScreenServiceHandler = new PlayScreenServiceHandler(context, playScreenViewModel);
    }

    public void onInitial() {
        playScreenServiceHandler.getAllSheets();
    }

    public void onPlayIconClick(View view) {
        playScreenViewModel.getIsPlayed().setValue(Boolean.FALSE.equals(playScreenViewModel.getIsPlayed().getValue()));
        ExoPlayer player = playScreenViewModel.getPlayer();
        if (player.getPlaybackState() == ExoPlayer.STATE_ENDED) {
            playScreenViewModel.setPlaybackPosition(0);
            player.seekTo(playScreenViewModel.getPlaybackPosition());
        } else {
            playScreenViewModel.setPlaybackPosition(player.getCurrentPosition());
        }

        player.play();
        startUpdatingStaff();
    }

    private void startUpdatingStaff() {
        updateStaff = new Runnable() {
            @Override
            public void run() {
                // Assuming you have a method in your view to update
                // musicView.updateView();
                handler.postDelayed(this, 16); // Refresh the view every 16 milliseconds (approx. 60 FPS)
            }
        };
        handler.post(updateStaff);
    }

    public void clear() {
        handler.removeCallbacks(updateStaff);
    }
}
