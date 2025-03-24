package com.example.pianotutorial.constants.adapters.play_song;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        holder.binding.songTitle.setText(song.getTitle()); // Hiển thị tên bài hát
        holder.binding.authorName.setText(song.getComposer()); // Hiển thị tên tác giả

        Glide.with(holder.itemView.getContext())
                .load(song.getSheets().get(0).getBackgroundMusicFile())  // URL hình ảnh
                .into(holder.binding.roundedImageView);  // Gán vào ImageView

        holder.binding.courseButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayScreenActivity.class);
            intent.putExtra("SHEET_ID", song.getSheets().get(0).getId()); // Truyền ID bài hát thay vì số nguyên
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

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
}
