package com.example.pianotutorial.features.authetication.eventhandlers;
import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.pianotutorial.features.authetication.fragments.LoginFragment;
import com.example.pianotutorial.features.authetication.fragments.RegisterFragment;
import com.example.pianotutorial.features.authetication.viewmodels.MainMenuViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.RegisterViewModel;

public class MainMenuEventHandler {
    private final MainMenuViewModel mainMenuViewModel;
    Context context;
    public MainMenuEventHandler(MainMenuViewModel viewModel, Context context) {
        this.mainMenuViewModel = viewModel;
        this.context = context;
    }
    public void navigateToRegister(View view){
        mainMenuViewModel.getAuthFragment().setValue(new RegisterFragment());
    }

    public void navigateToLogin(View view){
        mainMenuViewModel.getAuthFragment().setValue(new LoginFragment());
    }
}
