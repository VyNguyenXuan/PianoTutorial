package com.example.pianotutorial.song.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentSongBinding;

public class SongFragment extends Fragment {

    private FragmentSongBinding _fragmentSongBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentSongBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_song, container, false);

        return _fragmentSongBinding.getRoot();
    }
}
