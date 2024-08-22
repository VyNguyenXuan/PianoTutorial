package com.example.pianotutorial.features.sheetsceen.eventhandlers;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.playscreen.servicehandlers.PlayScreenServiceHandler;
import com.example.pianotutorial.features.sheetsceen.servicehandlers.SheetScreenServiceHandler;
import com.example.pianotutorial.features.sheetsceen.viewmodels.SheetScreenViewModel;

public class SheetScreenEventHandler {
    private final SheetScreenViewModel sheetScreenViewModel;
    private final Context context;
    private final SheetScreenServiceHandler sheetScreenServiceHandler;

    public SheetScreenEventHandler(SheetScreenViewModel sheetScreenViewModel, Context context) {
        this.sheetScreenViewModel = sheetScreenViewModel;
        this.context = context;
        sheetScreenServiceHandler = new SheetScreenServiceHandler(context, sheetScreenViewModel);

    }

    public void getSheetById(int sheetId) {
        sheetScreenServiceHandler.getSheetById(sheetId);
    }

    public void onSildeInClick(View view) {
        sheetScreenViewModel.getIsShowTopBar().setValue(true);
    }

    public void onSlideOutClick(View view) {
        sheetScreenViewModel.getIsShowTopBar().setValue(false);
    }

    public void onAutoScrollClick(View view) {
        boolean isAutoScrollClick = Boolean.TRUE.equals(sheetScreenViewModel.getIsAutoScroll().getValue());
        sheetScreenViewModel.getIsAutoScroll().setValue(!isAutoScrollClick);
    }

    public void onPlayedClick(View view) {
        boolean isPlayedClick = Boolean.TRUE.equals(sheetScreenViewModel.getIsPlayed().getValue());
        sheetScreenViewModel.getIsPlayed().setValue(!isPlayedClick);
    }

    public void onPlayMusicSheetClick(View view) {
        boolean isShowMusicSeek = Boolean.TRUE.equals(sheetScreenViewModel.getIsShowMusicSeek().getValue());
        sheetScreenViewModel.getIsShowMusicSeek().setValue(!isShowMusicSeek);
    }

    public void onNavigateBack(View view) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            ((Activity) context).finish();
            ((Activity) context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }, 1000);
    }
}
