package com.example.pianotutorial.features.authetication.viewmodels;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.features.authetication.fragments.MainMenuFragment;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public MutableLiveData<String> getEmail(){
        return email;
    }
    public MutableLiveData<String> getPassword(){
        return password;
    }
    public MutableLiveData<Boolean> isValid = new MutableLiveData<>(Boolean.FALSE);
    public MutableLiveData<Boolean> getIsValid(){
        return isValid;
    }
}



