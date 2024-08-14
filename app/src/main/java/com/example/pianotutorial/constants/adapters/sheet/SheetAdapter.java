package com.example.pianotutorial.constants.adapters.sheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ItemPracticeBinding;
import com.example.pianotutorial.databinding.ItemSheetBinding;

import java.util.List;

public class SheetAdapter extends RecyclerView.Adapter<SheetAdapter.SheetViewHolder> {
    private final Context context;
    private final List<String> urlList;

    public SheetAdapter(Context context, List<String> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @NonNull
    @Override
    public SheetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSheetBinding binding = ItemSheetBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SheetViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SheetViewHolder holder, int position) {
        String imageUrl = urlList.get(position);

        // Load image into ImageView using Glide
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.play_background) // Optional placeholder image
                .error(R.drawable.play_background) // Optional error image
                .into(holder.binding.sheetImage); // Assuming courseImage is an ImageView in your ItemPractice layout

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public static class SheetViewHolder extends RecyclerView.ViewHolder {
        private final ItemSheetBinding binding;

        public SheetViewHolder(@NonNull ItemSheetBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
