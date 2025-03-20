package com.example.pianotutorial.features.song.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.models.Song;

import java.util.List;

public class SongViewmodel extends ViewModel {
    private final MutableLiveData<List<Song>> songList = new MutableLiveData<>();
    public MutableLiveData<List<Song>> getSongList() {
        return songList;
    }

}
