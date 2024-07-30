package com.example.pianotutorial.features.course_detail.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.course_detail.CourseDetailAdapter;
import com.example.pianotutorial.databinding.ActivityCourseDetailBinding;
import com.example.pianotutorial.features.course_detail.eventhandlers.CourseDetailEventHandler;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

    private ActivityCourseDetailBinding activityCourseDetailBinding;
    private CourseDetailEventHandler courseDetailEventHandler;
    PagerSnapHelper snapHelper = new PagerSnapHelper();


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

        // Set up the RecyclerView
        displayCarouselBannerView(integerList, this);
    }

    private void displayCarouselBannerView(List<Integer> integerList, Context context) {
        RecyclerView _carouselRecyclerView = activityCourseDetailBinding.carouselRecyclerView;

        CourseDetailAdapter adapter = new CourseDetailAdapter(context, integerList);

        _carouselRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(_carouselRecyclerView);
        _carouselRecyclerView.setHasFixedSize(true);
        _carouselRecyclerView.setAdapter(adapter);
    }
}
