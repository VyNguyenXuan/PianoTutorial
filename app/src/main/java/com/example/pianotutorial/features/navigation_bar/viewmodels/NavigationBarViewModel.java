package com.example.pianotutorial.features.navigation_bar.viewmodels;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.features.course.fragments.CourseFragment;

public class NavigationBarViewModel extends ViewModel {
    private final MutableLiveData<Fragment> customerFragment = new MutableLiveData<>(new CourseFragment());

    public MutableLiveData<Fragment> getCustomerFragment() {
        return customerFragment;
    }
}
