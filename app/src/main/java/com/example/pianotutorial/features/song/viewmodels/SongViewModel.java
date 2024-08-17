package com.example.pianotutorial.features.song.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.models.SongRespond;
import com.example.pianotutorial.models.SongResponseByGenre;

public class SongViewModel extends ViewModel {
    private final MutableLiveData<SongRespond> songRespond = new MutableLiveData<>();
    private final MutableLiveData<SongResponseByGenre> songResponseByGenre = new MutableLiveData<>();

    public MutableLiveData<SongRespond> getSongRespond() {
        return songRespond;
    }

    public MutableLiveData<SongResponseByGenre> getSongResponseByGenre() {
        return songResponseByGenre;
    }
}
