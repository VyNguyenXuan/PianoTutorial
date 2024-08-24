package com.example.pianotutorial.features.sheetsceen.services;

import com.example.pianotutorial.models.Sheet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SheetScreenService {
    @GET("/api/Sheets/{id}")
    Call<Sheet> getSheetById(@Path("id") int sheetId);
}
