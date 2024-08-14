package com.example.pianotutorial.features.ui.viewmodel;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

public class MainMenuViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _navigateToLogin = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _navigateToRegister = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public LiveData<Boolean> getNavigateToLogin() {
        return _navigateToLogin;
    }

    public LiveData<Boolean> getNavigateToRegister() {
        return _navigateToRegister;
    }

    public LiveData<Boolean> getIsLoading(){
        return _isLoading;
    }

    public void onLoginClicked() {
        _isLoading.setValue(true);
        _navigateToLogin.setValue(true);
    }

    public void onRegisterClicked() {
        _isLoading.setValue(true);
        _navigateToRegister.setValue(true);
    }

    public void doneNavigating() {
        _navigateToLogin.setValue(false);
        _navigateToRegister.setValue(false);
        _isLoading.setValue(false);
    }

}
