package com.example.pianotutorial.features.song.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.play_song.PlaySongAdapter;
import com.example.pianotutorial.databinding.FragmentSongBinding;

import java.util.Arrays;
import java.util.List;

public class SongFragment extends Fragment {

    private FragmentSongBinding _fragmentSongBinding;
    private PlaySongAdapter playSongAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentSongBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_song, container, false);

        // Sample data for the adapter
        List<Integer> sampleData = Arrays.asList(1, 2, 3, 4, 5);

        playSongAdapter = new PlaySongAdapter(getContext(), sampleData);

        // Set the adapter and GridLayoutManager with 2 columns
        _fragmentSongBinding.recyclerViewPlaySong.setLayoutManager(new GridLayoutManager(getContext(), 2));
        _fragmentSongBinding.recyclerViewPlaySong.setAdapter(playSongAdapter);

        return _fragmentSongBinding.getRoot();
    }
}
