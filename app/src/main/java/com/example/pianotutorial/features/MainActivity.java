package com.example.pianotutorial.features;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    private LinearLayout playIcon;

    private Handler handler = new Handler();
    private Runnable updateStaff;
    private long playbackPosition = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicView = findViewById(R.id.music_view);
        playIcon = findViewById(R.id.play_icon);

        // Create a list of measures
        List<Measure> measureList = new ArrayList<>();
        List<SongNote> songNotes = new ArrayList<>();
        Measure measure = new Measure(1, 1, 1, songNotes);


        // Measure 1 - Various note types and durations
        List<SongNote> songNotes1 = new ArrayList<>();
        songNotes1.add(new SongNote(1, 1, 1, "C", "C", 4, 1.5f, 1, 0)); // Quarter note
        songNotes1.add(new SongNote(2, 1, 2, "F", "F", 4, 0.5f, 1, 1)); // Quarter note
        Measure measure1 = new Measure(1, 1, 1, songNotes1);
        measureList.add(measure1);

        // Measure 2
        List<SongNote> songNotes2 = new ArrayList<>();
        songNotes2.add(new SongNote(3, 1, 3, "D", "D", 4, 1f, 2, 0)); // Eighth note
        songNotes2.add(new SongNote(4, 1, 4, "D", "D", 4, 0.5f, 2, 1)); // Dotted quarter note
        songNotes2.add(new SongNote(5, 1, 5, "F", "F", 4, 0.5f, 2, 1)); // Dotted quarter note

        Measure measure2 = new Measure(2, 1, 2, songNotes2);
        measureList.add(measure2);

        // Measure 3
        List<SongNote> songNotes3 = new ArrayList<>();
        songNotes3.add(new SongNote(5, 1, 5, "F", "F", 4, 1.5f, 3, 0)); // Quarter note
        songNotes3.add(new SongNote(6, 1, 6, "G", "G", 4, 0.5f, 3, 1)); // Quarter note
        Measure measure3 = new Measure(3, 1, 3, songNotes3);
        measureList.add(measure3);

        // Measure 4
        List<SongNote> songNotes4 = new ArrayList<>();
        songNotes4.add(new SongNote(7, 1, 7, "A", "A", 5, 2f, 4, 0)); // Eighth note
        Measure measure4 = new Measure(4, 1, 4, songNotes4);
        measureList.add(measure4);

        // Measure 5
        List<SongNote> songNotes5 = new ArrayList<>();
        songNotes5.add(new SongNote(9, 1, 9, "A", "A", 5, 1f, 5, 0)); // Quarter note
        songNotes5.add(new SongNote(10, 1, 10, "F", "F", 4, 1f, 5, 1)); // Quarter note
        Measure measure5 = new Measure(5, 1, 5, songNotes5);
        measureList.add(measure5);

        // Measure 6
        List<SongNote> songNotes6 = new ArrayList<>();
        songNotes6.add(new SongNote(11, 1, 11, "G", "D", 4, 1f, 6, 0)); // Eighth note
        songNotes6.add(new SongNote(12, 1, 12, "A", "A", 5, 0.5f, 6, 1)); // Dotted quarter note
        songNotes6.add(new SongNote(13, 1, 13, "A", "A", 5, 0.5f, 6, 1)); // Dotted quarter note
        Measure measure6 = new Measure(6, 1, 6, songNotes6);
        measureList.add(measure6);

        // Measure 7
        List<SongNote> songNotes7 = new ArrayList<>();
        songNotes7.add(new SongNote(13, 1, 13, "B", "A", 5, 1f, 7, 0)); // Quarter note
        songNotes7.add(new SongNote(14, 1, 14, "C", "D", 4, 0.5f, 7, 1)); // Quarter note
        songNotes7.add(new SongNote(15, 1, 15, "C", "D", 4, 0.5f, 7, 1)); // Quarter note
        Measure measure7 = new Measure(7, 1, 7, songNotes7);
        measureList.add(measure7);

        // Measure 8
        List<SongNote> songNotes8 = new ArrayList<>();
        songNotes8.add(new SongNote(15, 1, 15, "D", "D", 4, 2f, 8, 0)); // Eighth note
        Measure measure8 = new Measure(8, 1, 8, songNotes8);
        measureList.add(measure8);
        // Measure 9
        List<SongNote> songNotes9 = new ArrayList<>();
        songNotes9.add(new SongNote(17, 1, 17, "D", "D", 4, 1f, 9, 0)); // Quarter note
        songNotes9.add(new SongNote(18, 1, 18, "D", "D", 4, 1f, 9, 1)); // Quarter note
        Measure measure9 = new Measure(9, 1, 9, songNotes9);
        measureList.add(measure9);

        // Measure 10
        List<SongNote> songNotes10 = new ArrayList<>();
        songNotes10.add(new SongNote(19, 1, 19, "A", "A", 5, 1f, 10, 0)); // Eighth note
        songNotes10.add(new SongNote(20, 1, 20, "A", "A", 5, 0.5f, 10, 1)); // Dotted quarter note
        songNotes10.add(new SongNote(21, 1, 21, "D", "D", 4, 0.5f, 10, 1)); // Dotted quarter note
        Measure measure10 = new Measure(10, 1, 10, songNotes10);
        measureList.add(measure10);

        // Measure 11
        List<SongNote> songNotes11 = new ArrayList<>();
        songNotes11.add(new SongNote(21, 1, 21, "D", "D", 4, 1f, 11, 0)); // Quarter note
        songNotes11.add(new SongNote(22, 1, 22, "D", "D", 4, 1f, 11, 1)); // Quarter note
        Measure measure11 = new Measure(11, 1, 11, songNotes11);
        measureList.add(measure11);

        // Measure 12
        List<SongNote> songNotes12 = new ArrayList<>();
        songNotes12.add(new SongNote(23, 1, 23, "A", "A", 5, 2f, 12, 0)); // Eighth note
        Measure measure12 = new Measure(12, 1, 12, songNotes12);
        measureList.add(measure12);

        // Measure 13
        List<SongNote> songNotes13 = new ArrayList<>();
        songNotes13.add(new SongNote(25, 1, 25, "A", "A", 5, 1f, 13, 0)); // Quarter note
        songNotes13.add(new SongNote(26, 1, 26, "F", "F", 4, 1f, 13, 1)); // Quarter note
        Measure measure13 = new Measure(13, 1, 13, songNotes13);
        measureList.add(measure13);
        // Measure 14
        List<SongNote> songNotes14 = new ArrayList<>();
        songNotes14.add(new SongNote(27, 1, 27, "D", "D", 4, 1f, 14, 0)); // Eighth note
        songNotes14.add(new SongNote(27, 1, 27, "A", "A", 5, 0.5f, 14, 0)); // Eighth note
        songNotes14.add(new SongNote(27, 1, 27, "A", "A", 5, 0.5f, 14, 0)); // Eighth note
        Measure measure14 = new Measure(14, 1, 14, songNotes14);
        measureList.add(measure14);
        // Set the measures to the music view
        musicView.setMeasures(measureList);
        // Measure 15
        List<SongNote> songNotes15 = new ArrayList<>();
        songNotes15.add(new SongNote(29, 1, 29, "A", "A", 5, 1f, 15, 0)); // Quarter note
        songNotes15.add(new SongNote(30, 1, 30, "G", "G", 4, 1f, 15, 1)); // Quarter note
        Measure measure15 = new Measure(15, 1, 15, songNotes15);
        measureList.add(measure15);

        // Measure 16
        List<SongNote> songNotes16 = new ArrayList<>();
        songNotes16.add(new SongNote(31, 1, 31, "F", "F", 4, 2f, 16, 0)); // Eighth note
        Measure measure16 = new Measure(16, 1, 16, songNotes16);
        measureList.add(measure16);
        // Initialize ExoPlayer
        player = new ExoPlayer.Builder(this).build();
        int resourceId = R.raw.theduck; // Replace 'your_audio_file' with your actual file name
        Uri audioUri = Uri.parse("android.resource://" + getPackageName() + "/" + resourceId);
        MediaItem mediaItem = MediaItem.fromUri(audioUri);
        player.setMediaItem(mediaItem);
        player.prepare();

        // Play button click listener
        // Track playback position

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
