package com.example.pianotutorial.features.song.services;

import com.example.pianotutorial.models.GenreResponse;
import com.example.pianotutorial.models.SongResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongService {
    @GET("api/Songs/genre")
    Call<SongResponse> getSongsByGenre(
            @Query("id") Integer genreId,
            @Query("pageNum") Integer pageNum,
            @Query("pageSize") Integer pageSize,
            @Query("keyword") String keyword
    );
    @GET("api/genre/get-all-genre")
    Call<GenreResponse> getAllGenre();
}
