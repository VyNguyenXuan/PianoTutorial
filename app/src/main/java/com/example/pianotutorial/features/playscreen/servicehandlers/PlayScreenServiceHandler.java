package com.example.pianotutorial.features.playscreen.servicehandlers;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.pianotutorial.constants.ErrorHandling;
import com.example.pianotutorial.constants.RetrofitClient;
import com.example.pianotutorial.features.playscreen.services.PlayScreenService;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;
import com.example.pianotutorial.models.Sheet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayScreenServiceHandler {
    private final Context _context;
    private final PlayScreenService _playScreenService;
    private final PlayScreenViewModel _playScreenViewModel;

    public PlayScreenServiceHandler(Context context, PlayScreenViewModel playScreenViewModel) {
        _playScreenService = RetrofitClient.getRetrofitInstance().create(PlayScreenService.class);
        _context = context;
        _playScreenViewModel = playScreenViewModel;
    }

    public void getAllSheets() {
        Call<List<Sheet>> call = _playScreenService.getAllSheets();
        call.enqueue(new Callback<List<Sheet>>() {
            @Override
            public void onResponse(@NonNull Call<List<Sheet>> call, @NonNull Response<List<Sheet>> response) {
                ErrorHandling.httpErrorHandler(response, _context, () -> {
                    List<Sheet> sheetListResponse = response.body();
                    if (sheetListResponse != null) {
                        _playScreenViewModel.getSheetList().setValue(sheetListResponse);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Sheet>> call, @NonNull Throwable throwable) {
                Toast.makeText(_context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
