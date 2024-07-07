package com.example.pianotutorial.features;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.components.paints.MusicView;
import com.example.pianotutorial.features.components.paints.models.MeasurePaint;
import com.example.pianotutorial.features.components.paints.models.Note;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer player;
    private MusicView musicView;
    private Button playButton;
    private Handler handler = new Handler();
    private Runnable updateStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicView = findViewById(R.id.music_view);
        playButton = findViewById(R.id.play_button);

        float screenWidth = getResources().getDisplayMetrics().widthPixels;

        // Measure 1
        MeasurePaint measure1 = new MeasurePaint(1);
        musicView.addMeasure(measure1);
        musicView.addNoteToMeasure(1, new Note(screenWidth, 200, 1000, 0.1f));
        musicView.addNoteToMeasure(1, new Note(screenWidth, 300, 2000, 0.1f));

        // Measure 2
        MeasurePaint measure2 = new MeasurePaint(2);
        musicView.addMeasure(measure2);
        musicView.addNoteToMeasure(2, new Note(screenWidth, 250, 3000, 0.1f));
        musicView.addNoteToMeasure(2, new Note(screenWidth, 350, 4000, 0.1f));

        // Initialize ExoPlayer
        player = new ExoPlayer.Builder(this).build();
        MediaItem mediaItem = MediaItem.fromUri("file:///android_asset/your_audio_file.mp3");
        player.setMediaItem(mediaItem);
        player.prepare();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.play();
                musicView.startDrawing(System.currentTimeMillis());
                startNoteAnimation();
            }
        });
    }

    private void startNoteAnimation() {
        updateStaff = new Runnable() {
            @Override
            public void run() {
                musicView.updateView();
                handler.postDelayed(this, 16); // roughly 60fps
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