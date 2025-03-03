package com.example.pianotutorial.features.authetication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForgotPasswordViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _navigateBackToLogin = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _navigateToCheckEmail = new MutableLiveData<>();

    public LiveData<Boolean> getNavigateBackToLogin(){
        return _navigateBackToLogin;
    }
    public void doneNavigatingToLogin(){
        _navigateBackToLogin.setValue(false);
    }

    public LiveData<Boolean> getNavigateToCheckEmail() {
        return _navigateToCheckEmail;
    }
    public void doneNavigatingToCheckEmail(){
        _navigateToCheckEmail.setValue(false);
    }

    public MutableLiveData<String> email = new MutableLiveData<>();

    public MutableLiveData<String> getEmail() {
        return email;
    }
    public MutableLiveData<Boolean> isValid = new MutableLiveData<>(Boolean.FALSE);
    public MutableLiveData<Boolean> getIsValid(){
        return isValid;
    }
}
