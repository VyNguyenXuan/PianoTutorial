package com.example.pianotutorial.features.playscreen.services;

import com.example.pianotutorial.models.Sheet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlayScreenService {
    @GET("api/Sheets")
    Call<List<Sheet>> getAllSheets();
}
