package com.example.pianotutorial.features.song.eventhandlers;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.example.pianotutorial.features.song.servicehandlers.SongServiceHandler;
import com.example.pianotutorial.features.song.viewmodels.SongViewModel;

public class SongEventHandler {
    private final SongViewModel songViewModel;
    private final SongServiceHandler songServiceHandler;
    private final Context context;

    public SongEventHandler(SongViewModel songViewModel, Context context) {
        this.songViewModel = songViewModel;
        this.songServiceHandler = new SongServiceHandler(context, songViewModel);
        this.context = context;
    }

    public void onInitial() {
        songServiceHandler.getSongsByGenre(null, 1, songViewModel.pageSize, "");
        songServiceHandler.getAllGenre();
    }

    public AdapterView.OnItemSelectedListener onGenreSelected() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                songViewModel.currentPage = 1; // Reset to first page
                songViewModel.getSelectedGenreIndex().setValue(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                songViewModel.currentPage = 1;
                songViewModel.getSelectedGenreIndex().setValue(0);
            }
        };
    }

    public void FilterSong(Integer genreId, Integer pageNum, Integer pageSize, String keyword) {
        songServiceHandler.getSongsByGenre(genreId, pageNum, pageSize, keyword);
    }
}
