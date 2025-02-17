package com.example.pianotutorial.features.authetication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentCheckEmailBinding;
import com.example.pianotutorial.features.authetication.eventhandlers.CheckEmailEventHandler;
import com.example.pianotutorial.features.authetication.viewmodels.CheckEmailViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.MainMenuViewModel;


public class CheckEmailFragment extends Fragment {
    private FragmentCheckEmailBinding Binding;
    private CheckEmailViewModel viewModel;
    private CheckEmailEventHandler eventHandler;
    private MainMenuViewModel mainMenuViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_email, container, false);
        viewModel = new ViewModelProvider(this).get(CheckEmailViewModel.class);
        mainMenuViewModel = new ViewModelProvider(requireActivity()).get(MainMenuViewModel.class);
        eventHandler = new CheckEmailEventHandler(viewModel,mainMenuViewModel ,getContext());
        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);
        Binding.setEventHandler(eventHandler);

        return Binding.getRoot();
    }
}
