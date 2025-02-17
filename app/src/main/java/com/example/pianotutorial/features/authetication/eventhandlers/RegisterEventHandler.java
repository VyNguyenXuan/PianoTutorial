package com.example.pianotutorial.features.authetication.eventhandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.pianotutorial.features.authetication.fragments.ForgotPasswordFragment;
import com.example.pianotutorial.features.authetication.fragments.LoginFragment;
import com.example.pianotutorial.features.authetication.fragments.RegisterFragment;
import com.example.pianotutorial.features.authetication.viewmodels.LoginViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.MainMenuViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.RegisterViewModel;

public class RegisterEventHandler {
    private final RegisterViewModel registerViewModel;
    private final MainMenuViewModel mainMenuViewModel;
    Context context;
    public RegisterEventHandler(RegisterViewModel viewModel, MainMenuViewModel mainMenuViewModel, Context context) {
        this.registerViewModel = viewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.context = context;
    }
    public void navigateToLogin(View view) {
        mainMenuViewModel.getAuthFragment().setValue(new LoginFragment());
        mainMenuViewModel.setPreviousFragment(new RegisterFragment());
    }
    public void navigateBack(View view) {
        mainMenuViewModel.getAuthFragment().setValue(mainMenuViewModel.getPreviousFragment());
        mainMenuViewModel.setPreviousFragment(new ForgotPasswordFragment());

    }
}
