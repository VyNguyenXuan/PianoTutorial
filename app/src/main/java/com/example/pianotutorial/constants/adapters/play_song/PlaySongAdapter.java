package com.example.pianotutorial.constants.adapters.play_song;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.popup_sheet.PopupSheetAdapter;
import com.example.pianotutorial.databinding.ItemPlaySongBinding;
import com.example.pianotutorial.databinding.PopupSongDetailBinding;
import com.example.pianotutorial.models.Song;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaySongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;

    private final Context context;
    private final List<Song> songList;
    private boolean isLoadingAdded = false;
    private final PlaySongViewModel playSongViewModel;
    private final PlaySongServiceHandler playSongServiceHandler;

    public PlaySongAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList != null ? songList : new ArrayList<>();
        this.playSongViewModel = new PlaySongViewModel();
        this.playSongServiceHandler = new PlaySongServiceHandler(context, playSongViewModel);
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

            // Set click listener for the item
            playSongViewHolder.itemView.setOnClickListener(v -> {
                if (context instanceof FragmentActivity) {
                    FragmentActivity activity = (FragmentActivity) context;
                    showPopup(activity, song);
                }
            });
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

    private void showPopup(FragmentActivity activity, Song song) {
        // Inflate the popup layout using DataBindingUtil
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PopupSongDetailBinding popupBinding = DataBindingUtil.inflate(inflater, R.layout.popup_song_detail, null, false);

        // Set the song details in the popup
        popupBinding.songTitle.setText(song.getTitle());
        popupBinding.authorName.setText(song.getComposer());

        // Fetch the sheet data based on the song ID
        playSongServiceHandler.getListSheetBySongId(song.getId());

        // Observe the data from the ViewModel and update the RecyclerView when the data is available
        playSongViewModel.getSheetList().observe(activity, sheetList -> {
            if (sheetList != null) {
                PopupSheetAdapter adapter = new PopupSheetAdapter(context, sheetList);
                popupBinding.recyclerViewSheetPopup.setLayoutManager(new LinearLayoutManager(context));
                popupBinding.recyclerViewSheetPopup.setAdapter(adapter);
            }
        });

        // Setup the PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupBinding.getRoot(),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);

        // Create a dimmed background overlay
        View rootView = activity.findViewById(android.R.id.content);
        ViewGroup rootViewGroup = (ViewGroup) rootView;

        View dimOverlay = new View(activity);
        dimOverlay.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        dimOverlay.setBackgroundColor(0x80000000); // Black color with 50% opacity

        // Add the dim overlay to the root view
        rootViewGroup.addView(dimOverlay);

        // Close button action
        popupBinding.closePopup.setOnClickListener(v -> {
            popupWindow.dismiss();
            rootViewGroup.removeView(dimOverlay); // Remove the dim overlay when popup is dismissed
        });

        // Set dismiss listener to remove dim overlay when clicking outside the popup
        popupWindow.setOnDismissListener(() -> rootViewGroup.removeView(dimOverlay));

        // Show the popup
        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
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
