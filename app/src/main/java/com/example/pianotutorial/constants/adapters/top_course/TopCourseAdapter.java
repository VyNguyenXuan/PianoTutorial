package com.example.pianotutorial.constants.adapters.top_course;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ItemCourseTopBinding;
import com.example.pianotutorial.features.course_detail.activities.CourseDetailActivity;

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
            ProgressBar progressBar = holder.binding.getRoot().findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

            // Sử dụng Handler để giả lập quá trình tải
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent intent = new Intent(context, CourseDetailActivity.class);
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }, 1000);
            holder.binding.executePendingBindings();
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
