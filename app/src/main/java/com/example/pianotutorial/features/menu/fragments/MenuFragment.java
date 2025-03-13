package com.example.pianotutorial.features.menu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentMenuBinding;
import com.example.pianotutorial.features.menu.eventhandlers.MenuEventHandler;

public class MenuFragment extends Fragment {
    private FragmentMenuBinding _fragmentMenuBiding;
    private MenuEventHandler menuEventHandler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentMenuBiding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);
_fragmentMenuBiding.setEventhandler(menuEventHandler);
        return _fragmentMenuBiding.getRoot();
    }}
