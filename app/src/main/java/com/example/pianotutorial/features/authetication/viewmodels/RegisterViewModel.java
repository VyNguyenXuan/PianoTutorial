package com.example.pianotutorial.features.authetication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _navigateBackToMainMenu = new MutableLiveData<>();

    public LiveData<Boolean> getNavigateBackToMainMenu(){
        return _navigateBackToMainMenu;
    }
    public void onBackClicked(){
        _navigateBackToMainMenu.setValue(true);
    }
    public void doneNavigatingBack(){
        _navigateBackToMainMenu.setValue(false);
    }

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> repass = new MutableLiveData<>();

    public MutableLiveData<String> getEmail(){
        return email;}
    public MutableLiveData<String> getPassword(){
        return password;}
    public MutableLiveData<String> getRepass(){
        return repass;}
    public MutableLiveData<Boolean> isValid = new MutableLiveData<>(Boolean.FALSE);
    public MutableLiveData<Boolean> getIsValid(){
        return isValid;
    }
}
