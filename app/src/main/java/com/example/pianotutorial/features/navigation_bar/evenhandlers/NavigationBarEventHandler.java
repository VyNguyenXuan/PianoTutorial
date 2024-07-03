package com.example.pianotutorial.features.navigation_bar.evenhandlers;
import android.content.Context;

import com.example.pianotutorial.features.course.fragments.CourseFragment;
import com.example.pianotutorial.features.music.fragments.MusicFragment;
import com.example.pianotutorial.features.navigation_bar.viewmodels.NavigationBarViewModel;
import com.example.pianotutorial.features.song.fragments.SongFragment;

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
    public void navigateToMusicFragment(View view) {
        if(!(_navigationBarViewModel.getCustomerFragment().getValue() instanceof MusicFragment)){
            _navigationBarViewModel.getCustomerFragment().setValue(new MusicFragment());
        }
    }public void navigateToSongFragment(View view) {
        if(!(_navigationBarViewModel.getCustomerFragment().getValue() instanceof SongFragment)){
            _navigationBarViewModel.getCustomerFragment().setValue(new SongFragment());
        }
    }


}
