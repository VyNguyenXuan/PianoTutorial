package com.example.pianotutorial.features.playscreen.viewmodels;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

import com.example.pianotutorial.models.Sheet;

import java.util.List;

public class PlayScreenViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> isPlayed = new MutableLiveData<>(false);
    private final MutableLiveData<List<Sheet>> sheetList = new MutableLiveData<>();

    private final ExoPlayer player;
    private long playbackPosition = 0;

    public PlayScreenViewModel(@NonNull Application application) {
        super(application);
        player = new ExoPlayer.Builder(application).build();
    }

    public void preparePlayer(int resourceId, String packageName) {
        Uri audioUri = Uri.parse("android.resource://" + packageName + "/" + resourceId);
        MediaItem mediaItem = MediaItem.fromUri(audioUri);
        player.setMediaItem(mediaItem);
        player.prepare();
    }

    public MutableLiveData<Boolean> getIsPlayed() {
        return isPlayed;
    }

    public MutableLiveData<List<Sheet>> getSheetList() {
        return sheetList;
    }

    public ExoPlayer getPlayer() {
        return player;
    }

    public long getPlaybackPosition() {
        return playbackPosition;
    }

    public void setPlaybackPosition(long playbackPosition) {
        this.playbackPosition = playbackPosition;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        player.release();
    }
}
