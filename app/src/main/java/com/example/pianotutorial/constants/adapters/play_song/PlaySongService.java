package com.example.pianotutorial.constants.adapters.play_song;

import com.example.pianotutorial.models.Sheet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaySongService {
    @GET("/api/Sheets/Song/{songId}")
    Call<List<Sheet>> getListSheetBySongId(@Path("songId") int songId);
}
