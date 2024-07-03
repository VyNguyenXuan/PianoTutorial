package com.example.pianotutorial.course.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentCourseBinding;

public class CourseFragment extends Fragment {

    private FragmentCourseBinding _fragmentCourseBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentCourseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false);

        return _fragmentCourseBinding.getRoot();
    }
}
