package com.example.pianotutorial.navigation_bar.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ActivityNavigationBarBinding;

public class NavigationBarActivity extends AppCompatActivity {
    private ActivityNavigationBarBinding _activityNavigationBarBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _activityNavigationBarBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_navigation_bar
        );
    }
}
