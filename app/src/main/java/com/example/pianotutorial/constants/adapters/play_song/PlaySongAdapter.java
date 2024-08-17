package com.example.pianotutorial.constants.adapters.play_song;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ItemPlaySongBinding;
import com.example.pianotutorial.features.playscreen.activities.PlayScreenActivity;
import com.example.pianotutorial.models.Song;

import java.util.List;

public class PlaySongAdapter extends RecyclerView.Adapter<PlaySongAdapter.PlaySongViewHolder> {
    private final Context context;
    private final List<Song> songList;

    public PlaySongAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
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
        Song song = songList.get(position);
        Bitmap bitmap = decodeBase64(song.getImage());
        Glide.with(context)
                .load(bitmap)
                .placeholder(R.drawable.img_default) // Optional placeholder
                .into(holder.binding.songImage);
        holder.binding.songTitle.setText(song.getTitle());
        holder.binding.authorName.setText(song.getComposer());
        holder.binding.artistName.setText(song.getComposer());
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class PlaySongViewHolder extends RecyclerView.ViewHolder {
        private final ItemPlaySongBinding binding;

        public PlaySongViewHolder(@NonNull ItemPlaySongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    // Helper method to decode a Base64 string into a Bitmap
    private Bitmap decodeBase64(String base64Str) {
        byte[] decodedBytes = Base64.decode(base64Str, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
