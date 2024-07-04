package com.example.pianotutorial.features.playscreen.activities;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.components.apdapters.MeasureAdapter;
import com.example.pianotutorial.models.Measure;
import com.example.pianotutorial.models.SongNote;

import java.util.ArrayList;
import java.util.List;

public class PlayScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.pianotutorial.databinding.ActivityPlayscreenBinding activityPlayScreenBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_playscreen
        );

        //Initialize RecyclerView
        RecyclerView measureItemRecyclerView = activityPlayScreenBinding.recyclerViewMeasures;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        measureItemRecyclerView.setLayoutManager(linearLayoutManager);
        measureItemRecyclerView.setHasFixedSize(true);

        //Set adapter to RecyclerView
        MeasureAdapter measureAdapter = new MeasureAdapter(createDummyData(), this);
        measureItemRecyclerView.setAdapter(measureAdapter);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if (count < measureAdapter.getItemCount()) {
                    measureItemRecyclerView.smoothScrollToPosition(count++);
                    handler.postDelayed(this, 1000); // Adjust delay as needed (milliseconds)
                }
            }
        };
        handler.postDelayed(runnable, 1000);


    }

    //Create demo measure data
    private List<Measure> createDummyData() {
        List<Measure> measures = new ArrayList<>();

        List<SongNote> notes1 = new ArrayList<>();
        notes1.add(new SongNote(1, 0, 1, "C", "C", 4, "4", 1, 1));
        notes1.add(new SongNote(2, 0, 2, "D", "D", 4, "2", 1, 2));

        List<SongNote> notes2 = new ArrayList<>();
        notes2.add(new SongNote(3, 0, 3, "E", "E", 4, "1", 2, 1));
        notes2.add(new SongNote(4, 0, 4, "F", "F", 4, "1", 2, 2));

        measures.add(new Measure(1, 1, 1, notes1));
        measures.add(new Measure(2, 1, 2, notes2));

        return measures;
    }
}
