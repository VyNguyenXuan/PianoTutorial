package com.example.pianotutorial.features.sheetsceen.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.models.Sheet;

public class SheetScreenViewModel extends ViewModel {
    private final MutableLiveData<Sheet> currentSheet = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isShowTopBar = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isShowMusicSeek = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isAutoScroll = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isPlayed = new MutableLiveData<>(false);
    private final MutableLiveData<Float> scrollSpeed = new MutableLiveData<>(1f);

    public MutableLiveData<Boolean> getIsShowTopBar() {
        return isShowTopBar;
    }

    public MutableLiveData<Boolean> getIsShowMusicSeek() {
        return isShowMusicSeek;
    }

    public MutableLiveData<Boolean> getIsAutoScroll() {
        return isAutoScroll;
    }

    public MutableLiveData<Boolean> getIsPlayed() {
        return isPlayed;
    }

    public MutableLiveData<Float> getScrollSpeed() {
        return scrollSpeed;
    }

    public MutableLiveData<Sheet> getCurrentSheet() {
        return currentSheet;
    }
}
