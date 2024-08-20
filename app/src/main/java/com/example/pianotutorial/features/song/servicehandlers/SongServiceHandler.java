package com.example.pianotutorial.features.song.servicehandlers;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pianotutorial.constants.ErrorHandling;
import com.example.pianotutorial.constants.RetrofitClient;
import com.example.pianotutorial.features.song.services.SongService;
import com.example.pianotutorial.features.song.viewmodels.SongViewModel;
import com.example.pianotutorial.models.GenreResponse;
import com.example.pianotutorial.models.SongResponse;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongServiceHandler {
    private final Context _context;
    private final SongService songService;
    private final SongViewModel songViewModel;

    public SongServiceHandler(Context context, SongViewModel songViewModel) {
        songService = RetrofitClient.getRetrofitInstance().create(SongService.class);
        _context = context;
        this.songViewModel = songViewModel;
    }

    public void getSongsByGenre(Integer genreId, Integer pageNum, Integer pageSize, String keyword) {
        Call<SongResponse> call = songService.getSongsByGenre(genreId, pageNum, pageSize, keyword);

        call.enqueue(new Callback<SongResponse>() {
            @Override
            public void onResponse(@NonNull Call<SongResponse> call, @NonNull Response<SongResponse> response) {
                ErrorHandling.httpErrorHandler(response, _context, () -> {
                    SongResponse songResponse = response.body();
                    if (songResponse != null) {
                        songViewModel.getSongRespond().setValue(songResponse);
                    }
                });
            }
            @Override
            public void onFailure(@NonNull Call<SongResponse> call, @NonNull Throwable throwable) {
                Toast.makeText(_context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getAllGenre() {
        Call<GenreResponse> call = songService.getAllGenre();
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenreResponse> call, @NonNull Response<GenreResponse> response) {
                ErrorHandling.httpErrorHandler(response, _context, () -> {
                    GenreResponse genreResponse = response.body();
                    if (genreResponse != null) {
                        // Assuming you have a method in your ViewModel to set the list of songs
                        songViewModel.getGenreResponse().setValue(genreResponse);
                    }
                });
            }
            @Override
            public void onFailure(@NonNull Call<GenreResponse> call, @NonNull Throwable throwable) {
                Toast.makeText(_context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
