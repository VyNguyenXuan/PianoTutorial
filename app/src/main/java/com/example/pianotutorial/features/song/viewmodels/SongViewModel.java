package com.example.pianotutorial.features.song.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.models.GenreResponse;
import com.example.pianotutorial.models.Song;
import com.example.pianotutorial.models.SongResponse;
import com.example.pianotutorial.models.SongResponseByGenre;

import java.util.List;

public class SongViewModel extends ViewModel {
    private final MutableLiveData<SongResponse> songRespond = new MutableLiveData<>();
    private final MutableLiveData<SongResponseByGenre> songResponseByGenre = new MutableLiveData<>();
    private final MutableLiveData<GenreResponse> genreResponse = new MutableLiveData<>();
    private final MutableLiveData<Integer> selectedGenreIndex = new MutableLiveData<>();

    // Pagination attributes
    public int currentPage = 1;
    public boolean isLoading = false;
    public boolean isLastPage = false;
    public int pageSize = 20;

    public MutableLiveData<SongResponse> getSongRespond() {
        return songRespond;
    }

    public MutableLiveData<SongResponseByGenre> getSongResponseByGenre() {
        return songResponseByGenre;
    }

    public MutableLiveData<GenreResponse> getGenreResponse() {
        return genreResponse;
    }

    public MutableLiveData<Integer> getSelectedGenreIndex() {
        return selectedGenreIndex;
    }
}
