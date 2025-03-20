package com.example.pianotutorial.features.song.servicehandlers;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pianotutorial.constants.ErrorHandling;
import com.example.pianotutorial.constants.RetrofitClient;
import com.example.pianotutorial.features.song.services.SongService;
import com.example.pianotutorial.features.song.viewmodels.SongViewmodel;
import com.example.pianotutorial.models.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongServiceHandler {
    private final Context _context;
    private final SongService songService;
    private final SongViewmodel songViewmodel;

    public SongServiceHandler(Context context, SongViewmodel playScreenViewModel) {
        songService = RetrofitClient.getRetrofitInstance().create(SongService.class);
        _context = context;
        songViewmodel = playScreenViewModel;
    }

    public void getAllSongs(int pageNum, int pageSize) {
        Call<List<Song>> call = songService.getAllSongs(pageNum, pageSize);
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(@NonNull Call<List<Song>> call, @NonNull Response<List<Song>> response) {
                ErrorHandling.httpErrorHandler(response, _context, () -> {
                    List<Song> songListResponse = response.body();
                    if (songListResponse != null) {
                        songViewmodel.getSongList().setValue(songListResponse);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Song>> call, @NonNull Throwable throwable) {
                Toast.makeText(_context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}