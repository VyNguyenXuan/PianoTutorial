package com.example.pianotutorial.features.song.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.play_song.PlaySongAdapter;
import com.example.pianotutorial.databinding.FragmentSongBinding;
import com.example.pianotutorial.features.song.eventhandlers.SongEventHandler;
import com.example.pianotutorial.features.song.viewmodels.SongViewModel;
import com.example.pianotutorial.models.Song;

import java.util.Arrays;
import java.util.List;

public class SongFragment extends Fragment {

    private FragmentSongBinding _fragmentSongBinding;
    private PlaySongAdapter playSongAdapter;
    private SongEventHandler songEventHandler;
    private SongViewModel songViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentSongBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_song, container, false);
        songViewModel = new ViewModelProvider(this).get(SongViewModel.class);
        songEventHandler = new SongEventHandler(songViewModel, getContext());
        _fragmentSongBinding.setEventHandler(songEventHandler);

        songEventHandler.onInitial();

        songViewModel.getSongRespond().observe(getViewLifecycleOwner(), songRespond -> {
            List<Song> songList = songRespond.getData().getSongResponseByGenre();

            playSongAdapter = new PlaySongAdapter(getContext(), songList);

            // Set the adapter and GridLayoutManager with 2 columns
            _fragmentSongBinding.recyclerViewPlaySong.setLayoutManager(new GridLayoutManager(getContext(), 2));
            _fragmentSongBinding.recyclerViewPlaySong.setAdapter(playSongAdapter);
        });



        return _fragmentSongBinding.getRoot();
    }
}
