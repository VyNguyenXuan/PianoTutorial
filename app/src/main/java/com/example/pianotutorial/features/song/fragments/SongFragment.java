package com.example.pianotutorial.features.song.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.play_song.PlaySongAdapter;
import com.example.pianotutorial.databinding.FragmentSongBinding;
import com.example.pianotutorial.features.playscreen.eventhandlers.PlayScreenEventHandler;
import com.example.pianotutorial.features.playscreen.viewmodels.PlayScreenViewModel;
import com.example.pianotutorial.features.song.eventhandlers.SongEventHandler;
import com.example.pianotutorial.features.song.viewmodels.SongViewmodel;

import java.util.Arrays;
import java.util.List;

public class SongFragment extends Fragment {

    private FragmentSongBinding _fragmentSongBinding;
    private PlaySongAdapter playSongAdapter;
    private SongViewmodel songViewmodel;
    private SongEventHandler songEventHandler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentSongBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_song, container, false);
        songViewmodel = new ViewModelProvider(this).get(SongViewmodel.class);
        songEventHandler = new SongEventHandler(songViewmodel, getContext());
        _fragmentSongBinding.setSongViewmodel(songViewmodel);
        _fragmentSongBinding.setSongEventHandler(songEventHandler);
        _fragmentSongBinding.setLifecycleOwner(this);

        songEventHandler.onInitial();
        songViewmodel.getSongList().observe(getViewLifecycleOwner(), songList -> {
            if (songList != null) {
                playSongAdapter = new PlaySongAdapter(getContext(), songList);
                // Set the adapter and GridLayoutManager with 2 columns
                _fragmentSongBinding.recyclerViewPlaySong.setLayoutManager(new GridLayoutManager(getContext(), 2));
                _fragmentSongBinding.recyclerViewPlaySong.setAdapter(playSongAdapter);
            }
        });

        return _fragmentSongBinding.getRoot();
    }
}
