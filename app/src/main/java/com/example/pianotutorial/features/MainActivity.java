package com.example.pianotutorial.features;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.components.paints.MusicView;
import com.example.pianotutorial.models.Measure;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer player;
    private MusicView musicView;
    private LinearLayout playIcon;
    private Handler handler = new Handler();
    private Runnable updateStaff;
    private long playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicView = findViewById(R.id.music_view);
        playIcon = findViewById(R.id.play_icon);

        player = new ExoPlayer.Builder(this).build();
        int resourceId = R.raw.theduck; // Replace 'your_audio_file' with your actual file name
        Uri audioUri = Uri.parse("android.resource://" + getPackageName() + "/" + resourceId);
        MediaItem mediaItem = MediaItem.fromUri(audioUri);
        player.setMediaItem(mediaItem);
        player.prepare();
        // Play button click listener
        playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.getPlaybackState() == ExoPlayer.STATE_ENDED) {
                    // Playback ended, reset position to start
                    playbackPosition = 0;
                    player.seekTo(playbackPosition);
                } else {
                    // Playback in progress or paused, store current position
                    playbackPosition = player.getCurrentPosition();
                }

                player.play();
                //musicView.startDrawing(System.currentTimeMillis());
                startUpdatingStaff();
            }
        });


    }

    private void startUpdatingStaff() {
        updateStaff = new Runnable() {
            @Override
            public void run() {
                musicView.updateView();
                handler.postDelayed(this, 16); // Refresh the view every 16 milliseconds (approx. 60 FPS)
            }
        };
        handler.post(updateStaff);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
        handler.removeCallbacks(updateStaff);
    }
}
