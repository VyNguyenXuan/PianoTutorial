package com.example.pianotutorial.constants.adapters.play_song;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ItemPlaySongBinding;
import com.example.pianotutorial.models.Song;

import java.util.ArrayList;
import java.util.List;

public class PlaySongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;

    private final Context context;
    private final List<Song> songList;
    private boolean isLoadingAdded = false;

    public PlaySongAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList != null ? songList : new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == songList.size() - 1 && isLoadingAdded) ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            ItemPlaySongBinding binding = ItemPlaySongBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new PlaySongViewHolder(binding);
        } else {
            // Inflate the loading view layout
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            Song song = songList.get(position);
            PlaySongViewHolder playSongViewHolder = (PlaySongViewHolder) holder;
            playSongViewHolder.binding.songTitle.setText(song.getTitle());
            playSongViewHolder.binding.authorName.setText(song.getComposer());
            playSongViewHolder.binding.artistName.setText(song.getComposer());
            playSongViewHolder.binding.executePendingBindings();
        }
        // No binding is required for loading view holder
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public void addSongs(List<Song> newSongs) {
        int startPosition = songList.size();
        songList.addAll(newSongs);
        notifyItemRangeInserted(startPosition, newSongs.size());
    }

    public void addLoadingFooter() {
        if (!isLoadingAdded) {
            isLoadingAdded = true;
            songList.add(new Song()); // Adding a dummy item
            notifyItemInserted(songList.size() - 1);
        }
    }

    public void removeLoadingFooter() {
        if (isLoadingAdded) {
            isLoadingAdded = false;

            int position = songList.size() - 1;
            if (position >= 0) {
                songList.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public void clearSongs() {
        songList.clear();
        notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }

    private Song getItem(int position) {
        return songList.get(position);
    }

    public static class PlaySongViewHolder extends RecyclerView.ViewHolder {
        private final ItemPlaySongBinding binding;

        public PlaySongViewHolder(@NonNull ItemPlaySongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
