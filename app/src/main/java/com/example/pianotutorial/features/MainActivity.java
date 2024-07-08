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
import com.example.pianotutorial.models.Measure;
import com.example.pianotutorial.models.SongNote;

import java.util.ArrayList;
import java.util.List;

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

        // Create a list of measures
        List<Measure> measureList = new ArrayList<>();

        // Measure 1
        List<SongNote> songNotes1 = new ArrayList<>();
        songNotes1.add(new SongNote(1, 1, 1, "C", "C", 4, 1f, 1, 0));
        songNotes1.add(new SongNote(2, 1, 2, "E", "E", 4, 0.5f, 1, 1));
        Measure measure1 = new Measure(1, 1, 1, songNotes1);
        measureList.add(measure1);

        // Measure 2
        List<SongNote> songNotes2 = new ArrayList<>();
        songNotes2.add(new SongNote(3, 1, 3, "G", "G", 4, 1f, 2, 0));
        songNotes2.add(new SongNote(4, 1, 4, "B", "B", 4, 0.5f, 2, 1));
        Measure measure2 = new Measure(2, 1, 2, songNotes2);
        measureList.add(measure2);

        // Measure 3
        List<SongNote> songNotes3 = new ArrayList<>();
        songNotes3.add(new SongNote(5, 1, 5, "C", "C", 5, 2f, 3, 0));
        Measure measure3 = new Measure(3, 1, 3, songNotes3);
        measureList.add(measure3);

        // Measure 4
        List<SongNote> songNotes4 = new ArrayList<>();
        songNotes4.add(new SongNote(7, 1, 7, "E", "E", 5, 0.25f, 4, 0));
        songNotes4.add(new SongNote(8, 1, 8, "F", "F", 5, 1f, 4, 1));
        Measure measure4 = new Measure(4, 1, 4, songNotes4);
        measureList.add(measure4);

        // Measure 5
        List<SongNote> songNotes5 = new ArrayList<>();
        songNotes5.add(new SongNote(9, 1, 9, "G", "G", 5, 0.5f, 5, 0));
        songNotes5.add(new SongNote(10, 1, 10, "A", "A", 5, 0.5f, 5, 1));
        Measure measure5 = new Measure(5, 1, 5, songNotes5);
        measureList.add(measure5);

        musicView.setMeasures(measureList);

        // Initialize ExoPlayer
        player = new ExoPlayer.Builder(this).build();
        MediaItem mediaItem = MediaItem.fromUri("asset:///path/to/your/audio/file.mp3");
        player.setMediaItem(mediaItem);
        player.prepare();

        // Play button click listener
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.play();
                musicView.startDrawing(System.currentTimeMillis());
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
