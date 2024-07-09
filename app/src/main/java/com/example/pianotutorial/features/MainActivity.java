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

        // Measure 1 - Various note types and durations
        List<SongNote> songNotes1 = new ArrayList<>();
        songNotes1.add(new SongNote(1, 1, 1, "C", "C", 4, 1f, 1, 0)); // Quarter note
        songNotes1.add(new SongNote(2, 1, 2, "D", "D", 4, 1f, 1, 1)); // Half note
        Measure measure1 = new Measure(1, 1, 1, songNotes1);
        measureList.add(measure1);

        // Measure 2
        List<SongNote> songNotes2 = new ArrayList<>();
        songNotes2.add(new SongNote(3, 1, 3, "E", "E", 4, 0.5f, 2, 0)); // Eighth note
        songNotes2.add(new SongNote(4, 1, 4, "G", "G", 4, 1f, 2, 2)); // Quarter note
        songNotes2.add(new SongNote(5, 1, 5, "E", "E", 4, 0.25f, 2, 0)); // Eighth note
        songNotes2.add(new SongNote(6, 1, 6, "E", "E", 4, 0.25f, 2, 0)); // Eighth note



        Measure measure2 = new Measure(2, 1, 2, songNotes2);
        measureList.add(measure2);

        // Measure 3
        List<SongNote> songNotes3 = new ArrayList<>();
        songNotes3.add(new SongNote(6, 1, 6, "A", "A", 4, 0.25f, 3, 0)); // Sixteenth note
        songNotes3.add(new SongNote(7, 1, 7, "B", "B", 4, 0.25f, 3, 1)); // Sixteenth note
        songNotes3.add(new SongNote(8, 1, 8, "C", "C", 5, 1f, 3, 2)); // Dotted quarter note
        Measure measure3 = new Measure(3, 1, 3, songNotes3);
        measureList.add(measure3);

        // Measure 4
        List<SongNote> songNotes4 = new ArrayList<>();
        songNotes4.add(new SongNote(9, 1, 9, "D", "D", 5, 2f, 4, 0)); // Half note
        Measure measure4 = new Measure(4, 1, 4, songNotes4);
        measureList.add(measure4);

        // Measure 5
        List<SongNote> songNotes5 = new ArrayList<>();
        songNotes5.add(new SongNote(10, 1, 10, "E", "E", 5, 0.5f, 5, 0)); // Eighth note
        songNotes5.add(new SongNote(11, 1, 11, "F", "F", 5, 0.5f, 5, 1)); // Eighth note
        Measure measure5 = new Measure(5, 1, 5, songNotes5);
        measureList.add(measure5);

        // Measure 6
        List<SongNote> songNotes6 = new ArrayList<>();
        songNotes6.add(new SongNote(12, 1, 12, "G", "G", 5, 1f, 6, 0)); // Quarter note
        songNotes6.add(new SongNote(13, 1, 13, "A", "A", 5, 1f, 6, 1)); // Quarter note
        Measure measure6 = new Measure(6, 1, 6, songNotes6);
        measureList.add(measure6);

        // Measure 7
        List<SongNote> songNotes7 = new ArrayList<>();
        songNotes7.add(new SongNote(14, 1, 14, "B", "B", 5, 2f, 7, 0)); // Half note
        Measure measure7 = new Measure(7, 1, 7, songNotes7);
        measureList.add(measure7);

        // Measure 8
        List<SongNote> songNotes8 = new ArrayList<>();
        songNotes8.add(new SongNote(15, 1, 15, "C", "C", 6, 2f, 8, 0)); // Whole note
        Measure measure8 = new Measure(8, 1, 8, songNotes8);
        measureList.add(measure8);

        // Set the measures to the music view
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
