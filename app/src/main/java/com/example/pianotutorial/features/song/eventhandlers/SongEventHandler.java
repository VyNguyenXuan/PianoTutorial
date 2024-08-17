package com.example.pianotutorial.features.song.eventhandlers;

import android.content.Context;

import com.example.pianotutorial.features.song.servicehandlers.SongServiceHandler;
import com.example.pianotutorial.features.song.viewmodels.SongViewModel;

public class SongEventHandler {
    private final SongViewModel songViewModel;
    private final SongServiceHandler songServiceHandler;
    Context context;

    public SongEventHandler(SongViewModel songViewModel, Context context) {
        this.songViewModel = songViewModel;
        this.songServiceHandler = new SongServiceHandler(context, songViewModel);
        this.context = context;

    }

    public void onInitial() {
        songServiceHandler.getSongsByGenre(null, 1, 100, "");
    }
}
