package com.example.pianotutorial.features.song.eventhandlers;

import android.content.Context;
import android.os.Handler;

import com.example.pianotutorial.features.playscreen.eventhandlers.SheetList;
import com.example.pianotutorial.features.playscreen.servicehandlers.PlayScreenServiceHandler;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;
import com.example.pianotutorial.features.song.servicehandlers.SongServiceHandler;
import com.example.pianotutorial.features.song.viewmodels.SongViewmodel;
import com.example.pianotutorial.models.Sheet;
import com.example.pianotutorial.models.Song;

import java.util.ArrayList;
import java.util.List;

public class SongEventHandler {

    private final SongViewmodel songViewmodel;
    private final SongServiceHandler songServiceHandler;
    Context context;

    public SongEventHandler(SongViewmodel songViewmodel, Context context) {
        this.songViewmodel = songViewmodel;
        this.context = context;
        songServiceHandler = new SongServiceHandler(context, songViewmodel);
    }

    public void onInitial() {
        songServiceHandler.getAllSongs(1, 100);
    }
}
