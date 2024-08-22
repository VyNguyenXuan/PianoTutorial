package com.example.pianotutorial.constants.adapters.play_song;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pianotutorial.constants.ErrorHandling;
import com.example.pianotutorial.constants.RetrofitClient;
import com.example.pianotutorial.features.playscreen.services.PlayScreenService;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;
import com.example.pianotutorial.models.Sheet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaySongServiceHandler {
    private final Context context;
    private final PlaySongService playSongService;
    private final PlaySongViewModel playSongViewModel;

    public PlaySongServiceHandler(Context context, PlaySongViewModel playSongViewModel) {
        this.context = context;
        this.playSongService = RetrofitClient.getRetrofitInstance().create(PlaySongService.class);
        this.playSongViewModel = playSongViewModel;
    }

    public void getListSheetBySongId(int songId) {
        Call<List<Sheet>> call = playSongService.getListSheetBySongId(songId);
        call.enqueue(new Callback<List<Sheet>>() {
            @Override
            public void onResponse(@NonNull Call<List<Sheet>> call, @NonNull Response<List<Sheet>> response) {
                ErrorHandling.httpErrorHandler(response, context, () -> {
                    List<Sheet> sheetList = response.body();
                    if (sheetList != null) {
                        playSongViewModel.getSheetList().setValue(sheetList);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Sheet>> call, @NonNull Throwable throwable) {
                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
