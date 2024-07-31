package com.example.pianotutorial.features.course.fragments;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.bottom_course.BottomCourseAdapter;
import com.example.pianotutorial.constants.adapters.top_course.TopCourseAdapter;
import com.example.pianotutorial.databinding.FragmentCourseBinding;
import java.util.Arrays;
import java.util.List;

public class CourseFragment extends Fragment {

    private FragmentCourseBinding _fragmentCourseBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentCourseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false);

        setupViewPager2();
        setupRecyclerView();

        return _fragmentCourseBinding.getRoot();
    }

    private void setupViewPager2() {
        List<String> topCourses = Arrays.asList("Course 1", "Course 2", "Course 3");
        TopCourseAdapter topCourseAdapter = new TopCourseAdapter(topCourses, getContext());
        _fragmentCourseBinding.viewPager2Top.setAdapter(topCourseAdapter);

        _fragmentCourseBinding.viewPager2Top.setClipToPadding(false);
        _fragmentCourseBinding.viewPager2Top.setClipChildren(false);
        _fragmentCourseBinding.viewPager2Top.setOffscreenPageLimit(3);

        float nextItemVisiblePx = getResources().getDimension(R.dimen.viewpager_next_item_visible);
        float currentItemHorizontalMarginPx = getResources().getDimension(R.dimen.viewpager_current_item_horizontal_margin);
        float paddingPx = getResources().getDimension(R.dimen.viewpager2_padding);
        float pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx;

        _fragmentCourseBinding.viewPager2Top.setPadding((int)paddingPx, 0, (int)paddingPx, 0);

        ViewPager2.PageTransformer pageTransformer = new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                page.setTranslationX(pageTranslationX * -position);
                page.setScaleY(1 - (0.25f * Math.abs(position)));
                page.setAlpha(0.5f + (1 - Math.abs(position)));
            }
        };

        _fragmentCourseBinding.viewPager2Top.setPageTransformer(pageTransformer);

        HorizontalMarginItemDecoration itemDecoration = new HorizontalMarginItemDecoration(
                getContext(),
                R.dimen.viewpager_current_item_horizontal_margin
        );
        _fragmentCourseBinding.viewPager2Top.addItemDecoration(itemDecoration);
    }

    private void setupRecyclerView() {
        List<String> bottomCourses = Arrays.asList("Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 5");
        BottomCourseAdapter bottomCourseAdapter = new BottomCourseAdapter(bottomCourses, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        _fragmentCourseBinding.recyclerViewBottom.setLayoutManager(layoutManager);
        _fragmentCourseBinding.recyclerViewBottom.setAdapter(bottomCourseAdapter);
    }

    // HorizontalMarginItemDecoration class for adding horizontal margin to items in ViewPager2
    public static class HorizontalMarginItemDecoration extends RecyclerView.ItemDecoration {
        private final int horizontalMargin;

        public HorizontalMarginItemDecoration(Context context, int horizontalMarginResource) {
            this.horizontalMargin = context.getResources().getDimensionPixelSize(horizontalMarginResource);
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.right = horizontalMargin;
            outRect.left = horizontalMargin;
        }
    }
}