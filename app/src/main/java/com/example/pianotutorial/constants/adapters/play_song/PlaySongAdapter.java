package com.example.pianotutorial.constants.adapters.play_song;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ItemPlaySongBinding;
import com.example.pianotutorial.features.playscreen.activities.PlayScreenActivity;

import java.util.List;

public class PlaySongAdapter extends RecyclerView.Adapter<PlaySongAdapter.PlaySongViewHolder> {
    private final Context context;
    private final List<Integer> integerList;

    public PlaySongAdapter(Context context, List<Integer> integerList) {
        this.context = context;
        this.integerList = integerList;
    }

    @NonNull
    @Override
    public PlaySongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlaySongBinding binding = ItemPlaySongBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlaySongViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaySongViewHolder holder, int position) {
        int value = integerList.get(position);
        holder.binding.courseButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayScreenActivity.class);
            intent.putExtra("ITEM_VALUE", value);
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return integerList.size();
    }

    public static class PlaySongViewHolder extends RecyclerView.ViewHolder {
        private final ItemPlaySongBinding binding;

        public PlaySongViewHolder(@NonNull ItemPlaySongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
