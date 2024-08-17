package com.example.pianotutorial.features.song.services;

import com.example.pianotutorial.models.SongRespond;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongService {
    @GET("api/Songs/genre")
    Call<SongRespond> getSongsByGenre(
            @Query("id") Integer genreId,
            @Query("pageNum") Integer pageNum,
            @Query("pageSize") Integer pageSize,
            @Query("keyword") String keyword
    );
}
