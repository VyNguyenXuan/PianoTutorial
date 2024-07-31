package com.example.pianotutorial.features.course_detail.eventhandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.pianotutorial.features.navigation_bar.viewmodels.NavigationBarViewModel;
import com.example.pianotutorial.features.playscreen.activities.PlayScreenActivity;

public class CourseDetailEventHandler {
    private final Context _context;

    public CourseDetailEventHandler(Context context) {
        _context = context;
    }

    public void navigateToPlayScreen(View view) {
        Intent intent = new Intent(_context, PlayScreenActivity.class);
        _context.startActivity(intent);
    }
}
