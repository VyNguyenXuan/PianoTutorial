package com.example.pianotutorial.constants.adapters.play_song;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.models.Sheet;

import java.util.List;

public class PlaySongViewModel extends ViewModel {
    private final MutableLiveData<List<Sheet>> sheetList = new MutableLiveData<>();

    public MutableLiveData<List<Sheet>> getSheetList() {
        return sheetList;
    }
}
