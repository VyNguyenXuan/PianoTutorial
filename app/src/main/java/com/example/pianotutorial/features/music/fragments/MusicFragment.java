package com.example.pianotutorial.features.music.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentMusicBinding;

public class MusicFragment extends Fragment {

    private FragmentMusicBinding _fragmentMusicBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentMusicBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_music, container, false);

        return _fragmentMusicBinding.getRoot();
    }
}
