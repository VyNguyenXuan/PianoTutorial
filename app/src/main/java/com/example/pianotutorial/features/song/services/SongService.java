package com.example.pianotutorial.features.song.services;

import com.example.pianotutorial.models.Sheet;
import com.example.pianotutorial.models.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SongService {
    @GET("api/Songs")
    Call<List<Song>> getAllSongs(
            @Query("pageNum") int pageNum,
            @Query("pageSize") int pageSize
    );
}
