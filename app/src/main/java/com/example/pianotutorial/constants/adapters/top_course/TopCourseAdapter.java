package com.example.pianotutorial.constants.adapters.top_course;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ItemCourseTopBinding;
import com.example.pianotutorial.features.course_detail.activities.CourseDetailActivity;
import com.example.pianotutorial.features.playscreen.activities.PlayScreenActivity;

import java.util.List;

public class TopCourseAdapter extends RecyclerView.Adapter<TopCourseAdapter.TopCourseViewHolder> {

    private List<String> topCourseList;
    private Context context;
    private ItemCourseTopBinding itemBinding;

    public TopCourseAdapter(List<String> topCourseList, Context context) {
        this.topCourseList = topCourseList;
        this.context = context;
    }

    @NonNull
    @Override
    public TopCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        itemBinding = ItemCourseTopBinding.inflate(layoutInflater, parent, false);
        return new TopCourseViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopCourseViewHolder holder, int position) {
        holder.binding.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CourseDetailActivity.class);
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

    }

    @Override
    public int getItemCount() {
        return topCourseList.size();
    }

    public static class TopCourseViewHolder extends RecyclerView.ViewHolder {
        private ItemCourseTopBinding binding;

        public TopCourseViewHolder(ItemCourseTopBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
