package com.example.pianotutorial.features.ui.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.navigation_bar.activities.NavigationBarActivity;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _zzzzBackToMainMenu = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _navigateToForgotPassword = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _navigateToRegister = new MutableLiveData<>();

    public LiveData<Boolean> getNavigateBackToMainMenu(){
        return _zzzzBackToMainMenu;
    }
    public void onBackClicked(){
        _zzzzBackToMainMenu.setValue(true);
    }
    public void doneNavigatingBack(){
        _zzzzBackToMainMenu.setValue(false);
    }

    public LiveData<Boolean> getNavigateToForgotPassword(){
        return _navigateToForgotPassword;
    }
    public void onForgotPasswordClicked(){
        _navigateToForgotPassword.setValue(true);
    }
    public void doneNavigateToForgotPassword(){
        _navigateToForgotPassword.setValue(false);
    }

    public LiveData<Boolean> navigateToRegister = _navigateToRegister;
    public void onRegisterLinkClicked(){
        _navigateToRegister.setValue(true);
    }
    public void doneNavigatingToRegister(){
        _navigateToRegister.setValue(false);
    }
}
