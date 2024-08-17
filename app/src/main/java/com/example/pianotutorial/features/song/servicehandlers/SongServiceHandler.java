package com.example.pianotutorial.features.song.servicehandlers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pianotutorial.constants.ErrorHandling;
import com.example.pianotutorial.constants.RetrofitClient;
import com.example.pianotutorial.features.playscreen.services.PlayScreenService;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;
import com.example.pianotutorial.features.song.services.SongService;
import com.example.pianotutorial.features.song.viewmodels.SongViewModel;
import com.example.pianotutorial.models.SongRespond;

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
        Call<SongRespond> call = songService.getSongsByGenre(genreId, pageNum, pageSize, keyword);

        call.enqueue(new Callback<SongRespond>() {
            @Override
            public void onResponse(@NonNull Call<SongRespond> call, @NonNull Response<SongRespond> response) {
                ErrorHandling.httpErrorHandler(response, _context, () -> {
                    SongRespond songRespond = response.body();
                    if (songRespond != null) {
                        // Assuming you have a method in your ViewModel to set the list of songs
                        songViewModel.getSongRespond().setValue(songRespond);
                    }
                });
            }
            @Override
            public void onFailure(@NonNull Call<SongRespond> call, @NonNull Throwable throwable) {
                Toast.makeText(_context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
