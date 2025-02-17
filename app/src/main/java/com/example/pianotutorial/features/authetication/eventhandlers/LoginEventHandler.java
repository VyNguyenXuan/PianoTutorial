package com.example.pianotutorial.features.authetication.eventhandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.pianotutorial.features.authetication.fragments.LoginFragment;
import com.example.pianotutorial.features.authetication.fragments.RegisterFragment;
import com.example.pianotutorial.features.authetication.viewmodels.LoginViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.MainMenuViewModel;
import com.example.pianotutorial.features.navigation_bar.activities.NavigationBarActivity;
import com.example.pianotutorial.features.authetication.fragments.ForgotPasswordFragment;

public class LoginEventHandler {
    private final LoginViewModel loginViewModel;
    private final MainMenuViewModel mainMenuViewModel;
    Context context;

    public LoginEventHandler(LoginViewModel viewModel, MainMenuViewModel mainMenuViewModel, Context context) {
        this.loginViewModel = viewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.context = context;
    }

    public void navigateToNavigationBar(View view){
        Intent intent = new Intent(context, NavigationBarActivity.class);
        context.startActivity(intent);}
    public void navigateToForgotPassword(View view) {
        mainMenuViewModel.getAuthFragment().setValue(new ForgotPasswordFragment());
        mainMenuViewModel.setPreviousFragment(new LoginFragment());
    }
    public void navigateToRegister(View view){
        mainMenuViewModel.getAuthFragment().setValue(new RegisterFragment());
        mainMenuViewModel.setPreviousFragment(new LoginFragment());
    }
    public void navigateBack(View view) {
        mainMenuViewModel.getAuthFragment().setValue(mainMenuViewModel.getPreviousFragment());
        mainMenuViewModel.setPreviousFragment(new ForgotPasswordFragment());

    }

}