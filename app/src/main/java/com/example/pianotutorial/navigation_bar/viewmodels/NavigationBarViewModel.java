package com.example.pianotutorial.navigation_bar.viewmodels;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pianotutorial.course.fragments.CourseFragment;

public class NavigationBarViewModel extends ViewModel {
    private MutableLiveData<Fragment> customerFragment = new MutableLiveData<>(new CourseFragment());

    public MutableLiveData<Fragment> getCustomerFragment() {
        return customerFragment;
    }
}
