package com.example.pianotutorial.features.edit_profile.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ActivityEditProfileBinding;
import com.example.pianotutorial.features.course.fragments.CourseFragment;
import com.example.pianotutorial.features.menu.fragments.MenuFragment;
import com.example.pianotutorial.features.music.fragments.MusicFragment;
import com.example.pianotutorial.features.navigation_bar.eventhandlers.NavigationBarEventHandler;
import com.example.pianotutorial.features.navigation_bar.viewmodels.NavigationBarViewModel;
import com.example.pianotutorial.features.song.fragments.SongFragment;

public class EditProfileActivity extends AppCompatActivity {
    private ActivityEditProfileBinding activityEditProfileBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditProfileBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_edit_profile
        );


    }
}
