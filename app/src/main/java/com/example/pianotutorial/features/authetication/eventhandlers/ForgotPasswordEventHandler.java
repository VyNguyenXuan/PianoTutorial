package com.example.pianotutorial.features.authetication.eventhandlers;

import android.content.Context;
import android.view.View;

import com.example.pianotutorial.features.authetication.fragments.CheckEmailFragment;
import com.example.pianotutorial.features.authetication.fragments.ForgotPasswordFragment;
import com.example.pianotutorial.features.authetication.viewmodels.ForgotPasswordViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.MainMenuViewModel;

public class ForgotPasswordEventHandler {
    private final ForgotPasswordViewModel forgotPasswordViewModel;
    private final MainMenuViewModel mainMenuViewModel;
    Context context;
    public ForgotPasswordEventHandler(ForgotPasswordViewModel viewModel, MainMenuViewModel mainMenuViewModel, Context context) {
        this.forgotPasswordViewModel = viewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.context = context;
    }
    public void navigateBack(View view) {
        mainMenuViewModel.getAuthFragment().setValue(mainMenuViewModel.getPreviousFragment());
        mainMenuViewModel.setPreviousFragment(new ForgotPasswordFragment());
    }
    public void navigateToCheckEmail(View view){
        mainMenuViewModel.getAuthFragment().setValue(new CheckEmailFragment());
        mainMenuViewModel.setPreviousFragment(new ForgotPasswordFragment());
    }


}
