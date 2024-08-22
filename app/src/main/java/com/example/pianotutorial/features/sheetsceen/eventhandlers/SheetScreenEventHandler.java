package com.example.pianotutorial.features.sheetsceen.eventhandlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.playscreen.activities.PlayScreenActivity;
import com.example.pianotutorial.features.sheetsceen.servicehandlers.SheetScreenServiceHandler;
import com.example.pianotutorial.features.sheetsceen.viewmodels.SheetScreenViewModel;
import com.example.pianotutorial.models.Sheet;

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
        ProgressBar progressBar = ((Activity) context).findViewById(R.id.sheet_screen_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            ((Activity) context).finish();
            ((Activity) context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }, 1000);
    }

    public void navigateToPlayScreen(View view) {
        Sheet currentSheet = sheetScreenViewModel.getCurrentSheet().getValue();
        if (currentSheet != null
                && (!currentSheet.getLeftHandMeasures().isEmpty() || !currentSheet.getRightHandMeasures().isEmpty())
                && !currentSheet.getSheetFile().isEmpty()) {
            ProgressBar progressBar = ((Activity) context).findViewById(R.id.sheet_screen_progress_bar);
            progressBar.setVisibility(View.VISIBLE);

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent intent = new Intent(context, PlayScreenActivity.class);
                intent.putExtra("sheetId", currentSheet.getId());
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
            }, 3000);
        } else {
            Toast.makeText(context, "Màn hình chơi không có dữ liệu!", Toast.LENGTH_SHORT).show();
        }

    }
}
