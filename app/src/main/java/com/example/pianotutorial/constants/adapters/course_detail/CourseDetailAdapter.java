package com.example.pianotutorial.constants.adapters.course_detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.databinding.ItemCourseDetailBinding;

import java.util.List;

public class CourseDetailAdapter extends RecyclerView.Adapter<CourseDetailAdapter.CourseDetailViewHolder> {
    private final Context context;
    private final List<Integer> integerList;

    public CourseDetailAdapter(Context context, List<Integer> integerList) {
        this.context = context;
        this.integerList = integerList;
    }

    @NonNull
    @Override
    public CourseDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCourseDetailBinding binding = ItemCourseDetailBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CourseDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseDetailViewHolder holder, int position) {
        int value = integerList.get(position);
    }

    @Override
    public int getItemCount() {
        return integerList.size();
    }

    public static class CourseDetailViewHolder extends RecyclerView.ViewHolder {
        private final ItemCourseDetailBinding binding;

        public CourseDetailViewHolder(@NonNull ItemCourseDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
