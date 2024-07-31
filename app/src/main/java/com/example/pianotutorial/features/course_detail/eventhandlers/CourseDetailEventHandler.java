package com.example.pianotutorial.features.course_detail.eventhandlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

import com.example.pianotutorial.R;
import com.example.pianotutorial.features.navigation_bar.activities.NavigationBarActivity;

public class CourseDetailEventHandler {
    private final Context _context;

    public CourseDetailEventHandler(Context context) {
        _context = context;
    }

    public void navigateToCourse(View view) {
        ProgressBar progressBar = ((Activity) _context).findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(_context, NavigationBarActivity.class);
            _context.startActivity(intent);
            ((Activity) _context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            ((Activity) _context).finish();
        }, 1000);
    }
}
