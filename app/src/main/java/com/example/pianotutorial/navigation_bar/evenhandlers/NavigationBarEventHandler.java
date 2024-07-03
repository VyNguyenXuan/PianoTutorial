package com.example.pianotutorial.navigation_bar.evenhandlers;
import android.content.Context;

import com.example.pianotutorial.course.fragments.CourseFragment;
import com.example.pianotutorial.navigation_bar.viewmodels.NavigationBarViewModel;
import android.view.View;


public class NavigationBarEventHandler {
    private final NavigationBarViewModel _navigationBarViewModel;
    private final Context _context;

    public NavigationBarEventHandler(NavigationBarViewModel navigationBarViewModel, Context context) {
        _navigationBarViewModel = navigationBarViewModel;
        _context = context;
    }

    public void navigateToCourtFragment(View view) {
        if(!(_navigationBarViewModel.getCustomerFragment().getValue() instanceof CourseFragment)){
            _navigationBarViewModel.getCustomerFragment().setValue(new CourseFragment());
        }
    }

}
