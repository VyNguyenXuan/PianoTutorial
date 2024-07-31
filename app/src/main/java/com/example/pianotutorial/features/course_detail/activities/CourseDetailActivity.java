package com.example.pianotutorial.features.course_detail.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.course_detail.CourseDetailAdapter;
import com.example.pianotutorial.databinding.ActivityCourseDetailBinding;
import com.example.pianotutorial.features.course_detail.eventhandlers.CourseDetailEventHandler;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

    private ActivityCourseDetailBinding activityCourseDetailBinding;
    private CourseDetailEventHandler courseDetailEventHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        activityCourseDetailBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_course_detail
        );

        // Initialize data
        List<Integer> integerList = new ArrayList<>();
        // Add sample data to the list
        for (int i = 1; i <= 20; i++) {
            integerList.add(i);
        }

        // Initialize the adapter
        CourseDetailAdapter adapter = new CourseDetailAdapter(this, integerList);
        courseDetailEventHandler = new CourseDetailEventHandler(this);
        activityCourseDetailBinding.setEventHandler(courseDetailEventHandler);

        // Set up the ViewPager2
        setupViewPager2(integerList, this);
    }

    private void setupViewPager2(List<Integer> integerList, Context context) {
        ViewPager2 viewPager2 = activityCourseDetailBinding.viewPager2;
        CourseDetailAdapter adapter = new CourseDetailAdapter(context, integerList);
        viewPager2.setAdapter(adapter);

        LinearLayout indicatorLayout = activityCourseDetailBinding.indicatorLayout;
        setupIndicators(indicatorLayout, integerList.size(), context);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateIndicators(indicatorLayout, position);
            }
        });
    }

    private void setupIndicators(LinearLayout indicatorLayout, int itemCount, Context context) {
        for (int i = 0; i < itemCount; i++) {
            ImageView indicator = new ImageView(context);
            indicator.setImageResource(R.drawable.tab_indicator_unselected);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(16, 16, 16, 24);
            indicatorLayout.addView(indicator, params);
        }
        updateIndicators(indicatorLayout, 0); // set the first indicator as selected
    }

    private void updateIndicators(LinearLayout indicatorLayout, int position) {
        for (int i = 0; i < indicatorLayout.getChildCount(); i++) {
            ImageView indicator = (ImageView) indicatorLayout.getChildAt(i);
            if (i == position) {
                indicator.setImageResource(R.drawable.tab_indicator_selected);
            } else {
                indicator.setImageResource(R.drawable.tab_indicator_unselected);
            }
        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            activityCourseDetailBinding.progressBar.setVisibility(View.GONE);
        }
    }
}
