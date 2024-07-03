package com.example.pianotutorial.features.navigation_bar.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.example.pianotutorial.R;
import com.example.pianotutorial.features.course.fragments.CourseFragment;
import com.example.pianotutorial.databinding.ActivityNavigationBarBinding;
import com.example.pianotutorial.features.music.fragments.MusicFragment;
import com.example.pianotutorial.features.navigation_bar.evenhandlers.NavigationBarEventHandler;
import com.example.pianotutorial.features.navigation_bar.viewmodels.NavigationBarViewModel;
import com.example.pianotutorial.features.song.fragments.SongFragment;
import androidx.appcompat.content.res.AppCompatResources;

public class NavigationBarActivity extends AppCompatActivity {
    private ActivityNavigationBarBinding _activityNavigationBarBinding;
    private NavigationBarViewModel _navigationBarViewModel;
    private NavigationBarEventHandler _navigationBarEventHandler;
    private FragmentTransaction _transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _activityNavigationBarBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_navigation_bar
        );

        _navigationBarViewModel = new ViewModelProvider(this).get(NavigationBarViewModel.class);
        _navigationBarEventHandler = new NavigationBarEventHandler(_navigationBarViewModel, this);

        _activityNavigationBarBinding.setActivityNavigationBarEventHandler(_navigationBarEventHandler);

        //Observe fragment changes
        _navigationBarViewModel.getCustomerFragment().observe(this, customerFragment -> {
            _transaction = getSupportFragmentManager().beginTransaction();
            _transaction.replace(R.id.fragment_container, customerFragment);
            _transaction.addToBackStack(null);
            _transaction.commit();

            // Update UI based on fragment type
            if (customerFragment instanceof CourseFragment) {
                updateUIForCourseFragment();
            } else if (customerFragment instanceof MusicFragment) {
                updateUIForMusicFragment();
            } else if (customerFragment instanceof SongFragment) {
                updateUIForSongFragment();
            }
        });
    }

    private void updateUIForCourseFragment() {
        _activityNavigationBarBinding.courseButton.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_green_radius_8));
        _activityNavigationBarBinding.courseImage.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_music_note_active));
        _activityNavigationBarBinding.courseTitle.setTextColor(ContextCompat.getColor(this, R.color.black));

        _activityNavigationBarBinding.musicButton.setBackground(null); // Clear previous background
        _activityNavigationBarBinding.musicImage.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_library_music));
        _activityNavigationBarBinding.musicTitle.setTextColor(ContextCompat.getColor(this, R.color.white));

        _activityNavigationBarBinding.songButton.setBackground(null); // Clear previous background
        _activityNavigationBarBinding.songImage.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_play_circle_outline));
        _activityNavigationBarBinding.songTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    private void updateUIForMusicFragment() {
        _activityNavigationBarBinding.courseButton.setBackground(null); // Clear previous background
        _activityNavigationBarBinding.courseImage.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_music_note));
        _activityNavigationBarBinding.courseTitle.setTextColor(ContextCompat.getColor(this, R.color.white));

        _activityNavigationBarBinding.musicButton.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_green_radius_8));
        _activityNavigationBarBinding.musicImage.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_library_music_active));
        _activityNavigationBarBinding.musicTitle.setTextColor(ContextCompat.getColor(this, R.color.black));

        _activityNavigationBarBinding.songButton.setBackground(null); // Clear previous background
        _activityNavigationBarBinding.songImage.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_play_circle_outline));
        _activityNavigationBarBinding.songTitle.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    private void updateUIForSongFragment() {
        _activityNavigationBarBinding.courseButton.setBackground(null); // Clear previous background
        _activityNavigationBarBinding.courseImage.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_music_note));
        _activityNavigationBarBinding.courseTitle.setTextColor(ContextCompat.getColor(this, R.color.white));

        _activityNavigationBarBinding.musicButton.setBackground(null); // Clear previous background
        _activityNavigationBarBinding.musicImage.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_library_music));
        _activityNavigationBarBinding.musicTitle.setTextColor(ContextCompat.getColor(this, R.color.white));

        _activityNavigationBarBinding.songButton.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_green_radius_8));
        _activityNavigationBarBinding.songImage.setBackground(AppCompatResources.getDrawable(this, R.drawable.vector_play_circle_outline_active));
        _activityNavigationBarBinding.songTitle.setTextColor(ContextCompat.getColor(this, R.color.black));
    }
}
