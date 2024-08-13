package com.example.pianotutorial.features.playscreen.eventhandlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.navigation_bar.activities.NavigationBarActivity;
import com.example.pianotutorial.features.playscreen.servicehandlers.PlayScreenServiceHandler;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;
import com.example.pianotutorial.models.Sheet;

public class PlayScreenEventHandler {

    private final PlayScreenViewModel playScreenViewModel;
    private final PlayScreenServiceHandler playScreenServiceHandler;
    private Runnable updateStaff;
    private final Handler handler = new Handler();
    Context context;

    public PlayScreenEventHandler(PlayScreenViewModel viewModel, Context context) {
        this.playScreenViewModel = viewModel;
        this.context = context;
        playScreenServiceHandler = new PlayScreenServiceHandler(context, playScreenViewModel);
    }

    public void onInitial() {
        //playScreenServiceHandler.getAllSheets();
        int songId = 1;
        int topSignature = 4;
        int bottomSignature = 4;
        int keySignature = 2;
        String rightSymbol = "F:F3#G3A3B3-9_0.5 F:D3#C3_0.5 F4#D4-2_0.5 E4#D4_0.5 F:A3_2/D5_2 C5#_2/B4_2 A4_2/B4_2 C5#_2/D5F5#_2 C5#E5_2/B4D5_2 A4C5#_2/G4B4_2 F4A4_2/G4B4_2 A4C5#_2/D4_1 F4_1 A4_1 G4_1/F4_1 D4_1 F4_1 E4_1/D4_1 B3_1 D4_1 A4_1/G4_1 B4_1 A4_1 G4_1/F4_1 D4_1 E4_1 C5#_1/D5_1 F5#_1 A5_1 A4_1/B4_1 G4_1 A4_1 F4_1/D4_1 D5_1 C5#_1 P_1/D5_0.5 C5#_0.5 D5_0.5 D4_0.5 C4_0.5 A4_0.5 E4_0.5 F4_0.5/D4_0.5 D5_0.5 C5#_0.5 B4_0.5 C5#_0.5 F5#_0.5 A5_0.5 B5_0.5/G5_0.5 F5#_0.5 E5_0.5 G5_0.5 F5#_0.5 E5_0.5 D5_0.5 C5#_0.5/B4_0.5 A4_0.5 G4_0.5 F4_0.5 E4_0.5 G4_0.5 F4_0.5 E4_0.5/D4_0.5 E4_0.5 F4_0.5 G4_0.5 A4_0.5 E4_0.5 A4_0.5 G4_0.5/F4_0.5 B4_0.5 A4_0.5 G4_0.5 A4_0.5 G4_0.5 F4_0.5 D4_0.5/D4_0.5 B3_0.5 B4_0.5 C5#_0.5 D5_0.5 C5#_0.5 B4_0.5 C4_0.5/G4_0.5 F4_0.5 E4_0.5 B4_0.5 A4_0.5 B4_0.5 A4_0.5 G4_0.5/F4_1 F5#_1 E5_2/P_1 D5_1 F5#_2/B5_2 A5_2/B5_2 C6_2/D6_1 D5_1 C5#_2/P_1 B4_1 D5_2/D5_2 P_1 D5_1/D5_1 F5#_1 E5_1 A5_1";
        String leftSymbol = "F5_0.25 E5_0.25 P_0.25 P_0.5 F:F3#F3bF3_0.75 P_1 F4_0.25 E4C4D4F4_0.25 P_0.5/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4/P_4";

        Sheet sheet = new Sheet(1, songId, "Nothing", topSignature, bottomSignature, 1, "Piano", "", keySignature, rightSymbol, leftSymbol);
        playScreenViewModel.getCurrentSheet().setValue(sheet);
    }
    /*public void onInitial(int sheetId,int leftSheetId) {
        playScreenServiceHandler.getSheetById(sheetId);
        playScreenServiceHandler.getLeftSheetById(sheetId);
    }*/

    public void onPlayIconClick(View view) {
        boolean isPlaying = Boolean.TRUE.equals(playScreenViewModel.getIsPlayed().getValue());
        playScreenViewModel.getIsPlayed().setValue(!isPlaying);
    }

    public void onPlayAgainClick(View view) {
        boolean isDone = Boolean.TRUE.equals(playScreenViewModel.getIsDone().getValue());
        playScreenViewModel.getIsDone().setValue(!isDone);

    }

    public void onSpeed1Click(View view) {
        playScreenViewModel.getSpeed().setValue(0.6f);
    }

    public void onSpeed2Click(View view) {
        playScreenViewModel.getSpeed().setValue(0.8f);
    }

    public void onSpeed3Click(View view) {
        playScreenViewModel.getSpeed().setValue(1f);
    }

    public void navigateToCourseDetail(View view) {
        ProgressBar progressBar = ((Activity) context).findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(context, NavigationBarActivity.class);
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            ((Activity) context).finish();
        }, 1000);
    }
}

