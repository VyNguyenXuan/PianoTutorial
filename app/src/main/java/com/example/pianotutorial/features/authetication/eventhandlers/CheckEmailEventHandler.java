package com.example.pianotutorial.features.authetication.eventhandlers;

import android.content.Context;
import android.view.View;

import com.example.pianotutorial.features.authetication.fragments.CheckEmailFragment;
import com.example.pianotutorial.features.authetication.viewmodels.CheckEmailViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.MainMenuViewModel;

public class CheckEmailEventHandler {
    private final CheckEmailViewModel checkEmailViewModel;
    private final MainMenuViewModel mainMenuViewModel;
    Context context;
    public CheckEmailEventHandler(CheckEmailViewModel viewModel, MainMenuViewModel mainMenuViewModel, Context context) {
        this.checkEmailViewModel = viewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.context = context;
    }
    public void navigateBack(View view) {
        mainMenuViewModel.getAuthFragment().setValue(mainMenuViewModel.getPreviousFragment());
        mainMenuViewModel.setPreviousFragment(new CheckEmailFragment());
    }
    public void navigateToLogin(View view){
        mainMenuViewModel.getAuthFragment().setValue(mainMenuViewModel.getPreviousFragment());
        mainMenuViewModel.setPreviousFragment(new CheckEmailFragment());
    }
}
