package com.example.pianotutorial.features.authetication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentRegisterBinding;
import com.example.pianotutorial.features.authetication.eventhandlers.LoginEventHandler;
import com.example.pianotutorial.features.authetication.eventhandlers.RegisterEventHandler;
import com.example.pianotutorial.features.authetication.viewmodels.MainMenuViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;
    private FragmentRegisterBinding Binding;
    private RegisterEventHandler eventHandler;
    private MainMenuViewModel mainMenuViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        mainMenuViewModel = new ViewModelProvider(requireActivity()).get(MainMenuViewModel.class);
        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);
        eventHandler = new RegisterEventHandler(viewModel,mainMenuViewModel,getContext());
        Binding.setEventHandler(eventHandler);



        return Binding.getRoot();
    }
}
