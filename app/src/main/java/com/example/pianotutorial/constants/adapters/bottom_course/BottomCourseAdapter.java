package com.example.pianotutorial.constants.adapters.bottom_course;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.databinding.ItemCourseBottomBinding;

import java.util.List;

public class BottomCourseAdapter extends RecyclerView.Adapter<BottomCourseAdapter.BottomCourseViewHolder> {

    private final List<String> bottomCourseList;
    private int selectedPosition = 0;

    private static final String[] COLORS = {
            "#FF7A7A", "#FFD14B", "#82B6FF", "#FD94F6", "#ADF99E", "#7CE0FC"
    };

    public BottomCourseAdapter(List<String> bottomCourseList, Context context) {
        this.bottomCourseList = bottomCourseList;
    }

    @NonNull
    @Override
    public BottomCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCourseBottomBinding itemBinding = ItemCourseBottomBinding.inflate(layoutInflater, parent, false);
        return new BottomCourseViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomCourseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return bottomCourseList.size();
    }

    public class BottomCourseViewHolder extends RecyclerView.ViewHolder {
        private final ItemCourseBottomBinding binding;

        public BottomCourseViewHolder(ItemCourseBottomBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                int previousPosition = selectedPosition;
                selectedPosition = getAdapterPosition();
                notifyItemChanged(previousPosition);
                notifyItemChanged(selectedPosition);
            });
        }

        public void bind(int position) {
            int index = position % COLORS.length;
            String color = COLORS[index];

            if (position == selectedPosition) {
                binding.activeView.setVisibility(View.VISIBLE);
            } else {
                binding.activeView.setVisibility(View.GONE);

            }

            binding.courseButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            binding.lessionTitle.setTextColor(Color.parseColor("#FFFFFF"));
            binding.lessionTitle.setText("Bài học " + (index + 1));
        }
    }
}
