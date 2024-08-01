package com.example.pianotutorial.features.music.fragments;

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
import com.example.pianotutorial.constants.adapters.practice.PracticeAdapter;
import com.example.pianotutorial.databinding.FragmentMusicBinding;

import java.util.Arrays;
import java.util.List;

public class MusicFragment extends Fragment {

    private FragmentMusicBinding _fragmentMusicBinding;
    private PracticeAdapter practiceAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentMusicBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_music, container, false);

        // Sample data for the adapter
        List<Integer> sampleData = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);

        practiceAdapter = new PracticeAdapter(getContext(), sampleData);

        // Set the adapter and GridLayoutManager with 2 columns
        _fragmentMusicBinding.recyclerViewPractice.setLayoutManager(new GridLayoutManager(getContext(), 2));
        _fragmentMusicBinding.recyclerViewPractice.setAdapter(practiceAdapter);

        return _fragmentMusicBinding.getRoot();
    }

}
