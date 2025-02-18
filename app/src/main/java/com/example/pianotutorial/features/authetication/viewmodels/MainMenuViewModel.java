package com.example.pianotutorial.features.authetication.viewmodels;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.features.authetication.fragments.LoginFragment;

public class MainMenuViewModel extends ViewModel {
    private Fragment previousFragment= new Fragment();
    public MutableLiveData<Fragment> authFragment = new MutableLiveData<>(new LoginFragment());
    public MutableLiveData<Fragment> getAuthFragment(){
        return authFragment;
    }
    public Fragment getPreviousFragment(){
        return previousFragment;
    }
    public void setPreviousFragment(Fragment previousFragment){
        this.previousFragment = previousFragment;
    }

}
