package com.example.pianotutorial.features.sheetsceen.servicehandlers;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pianotutorial.constants.ErrorHandling;
import com.example.pianotutorial.constants.RetrofitClient;
import com.example.pianotutorial.features.sheetsceen.services.SheetScreenService;
import com.example.pianotutorial.features.sheetsceen.viewmodels.SheetScreenViewModel;
import com.example.pianotutorial.models.Sheet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SheetScreenServiceHandler {
    private final Context context;
    private final SheetScreenService sheetScreenService;
    private final SheetScreenViewModel sheetScreenViewModel;

    public SheetScreenServiceHandler(Context context, SheetScreenViewModel sheetScreenViewModel) {
        this.sheetScreenService = RetrofitClient.getRetrofitInstance().create(SheetScreenService.class);
        this.context = context;
        this.sheetScreenViewModel = sheetScreenViewModel;
    }

    public void getSheetById(int sheetId) {
        Call<Sheet> call = sheetScreenService.getSheetById(sheetId);

        call.enqueue(new Callback<Sheet>() {
            @Override
            public void onResponse(@NonNull Call<Sheet> call, @NonNull Response<Sheet> response) {
                ErrorHandling.httpErrorHandler(response, context, () -> {
                    Sheet sheetResponse = response.body();
                    if (sheetResponse != null) {
                        sheetScreenViewModel.getCurrentSheet().setValue(sheetResponse);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<Sheet> call, @NonNull Throwable throwable) {
                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
