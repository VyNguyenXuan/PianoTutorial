package com.example.pianotutorial.features.navigation_bar.viewmodels;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.features.course.fragments.CourseFragment;

public class NavigationBarViewModel extends ViewModel {
    private final MutableLiveData<Fragment> customerFragment = new MutableLiveData<>(new CourseFragment());

    public MutableLiveData<Fragment> getCustomerFragment() {
        return customerFragment;
    }

    private final MutableLiveData<Boolean> isMenuVisible = new MutableLiveData<>(false);

    public MutableLiveData<Boolean> getIsMenuVisible() {
        return isMenuVisible;
    }
}