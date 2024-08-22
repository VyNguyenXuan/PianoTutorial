package com.example.pianotutorial.features.song.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.play_song.PlaySongAdapter;
import com.example.pianotutorial.databinding.FragmentSongBinding;
import com.example.pianotutorial.databinding.PopupSongDetailBinding;
import com.example.pianotutorial.features.song.eventhandlers.SongEventHandler;
import com.example.pianotutorial.features.song.viewmodels.SongViewModel;
import com.example.pianotutorial.models.Genre;
import com.example.pianotutorial.models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SongFragment extends Fragment {

    private FragmentSongBinding _fragmentSongBinding;
    private PlaySongAdapter playSongAdapter;
    private SongEventHandler songEventHandler;
    private SongViewModel songViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        _fragmentSongBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_song, container, false);
        songViewModel = new ViewModelProvider(this).get(SongViewModel.class);
        songEventHandler = new SongEventHandler(songViewModel, getContext());
        _fragmentSongBinding.setEventHandler(songEventHandler);
        _fragmentSongBinding.setViewModel(songViewModel);

        songEventHandler.onInitial();

        playSongAdapter = new PlaySongAdapter(getContext(), new ArrayList<>());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        _fragmentSongBinding.recyclerViewPlaySong.setLayoutManager(layoutManager);
        _fragmentSongBinding.recyclerViewPlaySong.setAdapter(playSongAdapter);

        // Observe songRespond LiveData
        songViewModel.getSongRespond().observe(getViewLifecycleOwner(), songResponse -> {
            if (songResponse != null && songResponse.getData() != null) {
                List<Song> songs = songResponse.getData().getSongResponseByGenre();
                playSongAdapter.removeLoadingFooter();
                playSongAdapter.addSongs(songs); // Make sure addSongs method exists in PlaySongAdapter
            }
        });

        songViewModel.getGenreResponse().observe(getViewLifecycleOwner(), genreResponse -> {
            List<Genre> genres = genreResponse.getData();
            List<String> genreNames = new ArrayList<>();
            genreNames.add("Tất cả");
            for (Genre genre : genres) {
                genreNames.add(genre.getName()); // Giả sử có phương thức getName() trong Genre
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.item_dropdown, genreNames);
            adapter.setDropDownViewResource(R.layout.item_dropdown);

            _fragmentSongBinding.genreSpinner.setAdapter(adapter);
        });

        songViewModel.getSelectedGenreIndex().observe(getViewLifecycleOwner(), selectedGenreIndex -> {
            String keyword = _fragmentSongBinding.searchBar.getQuery().toString();
            int pageSize = songViewModel.pageSize;
            playSongAdapter = new PlaySongAdapter(getContext(), new ArrayList<>());
            _fragmentSongBinding.recyclerViewPlaySong.setLayoutManager(layoutManager);
            _fragmentSongBinding.recyclerViewPlaySong.setAdapter(playSongAdapter);
            if (selectedGenreIndex > 0) {
                Integer genreId = Objects.requireNonNull(songViewModel.getGenreResponse().getValue()).getData().get(selectedGenreIndex - 1).getId();
                songEventHandler.FilterSong(genreId, 1, pageSize, keyword);
            } else {
                songEventHandler.FilterSong(null, 1, pageSize, keyword);
            }

        });

        _fragmentSongBinding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Integer selectedGenreIndex = songViewModel.getSelectedGenreIndex().getValue();
                int pageSize = songViewModel.pageSize;
                Integer genreId = null;

                if (selectedGenreIndex != null && selectedGenreIndex > 0) {
                    genreId = Objects.requireNonNull(songViewModel.getGenreResponse().getValue())
                            .getData()
                            .get(selectedGenreIndex - 1)
                            .getId();
                }
                playSongAdapter = new PlaySongAdapter(getContext(), new ArrayList<>());
                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (playSongAdapter.getItemViewType(position) == PlaySongAdapter.VIEW_TYPE_LOADING) {
                            return 2; // Chiếm toàn bộ chiều rộng (2 cột)
                        } else {
                            return 1; // Chiếm 1 cột
                        }
                    }
                });
                _fragmentSongBinding.recyclerViewPlaySong.setLayoutManager(layoutManager);
                _fragmentSongBinding.recyclerViewPlaySong.setAdapter(playSongAdapter);
                songEventHandler.FilterSong(genreId, 1, pageSize, newText);
                return true;
            }

        });

        // Handle scroll event for pagination
        _fragmentSongBinding.recyclerViewPlaySong.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (!songViewModel.isLoading && layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == playSongAdapter.getItemCount() - 1) {
                    // End has been reached, load more data with delay
                    loadMoreItemsWithDelay();
                }
            }
        });

        return _fragmentSongBinding.getRoot();
    }

    private void loadMoreItemsWithDelay() {
        songViewModel.isLoading = true;
        playSongAdapter.addLoadingFooter();

        // Delay of 1 second before loading more items
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Load more data here (e.g., fetch more items from the ViewModel)
            int currentPage = songViewModel.currentPage;
            int pageSize = songViewModel.pageSize;
            Integer selectedGenreIndex = songViewModel.getSelectedGenreIndex().getValue();
            String keyword = _fragmentSongBinding.searchBar.getQuery().toString();
            Integer genreId = null;

            if (selectedGenreIndex != null && selectedGenreIndex > 0) {
                genreId = Objects.requireNonNull(songViewModel.getGenreResponse().getValue())
                        .getData()
                        .get(selectedGenreIndex - 1)
                        .getId();
            }

            // Simulating network call or data fetching
            songEventHandler.FilterSong(genreId, currentPage + 1, pageSize, keyword);

            // Update isLoading and remove loading footer
            songViewModel.isLoading = false;
            playSongAdapter.removeLoadingFooter();
            songViewModel.currentPage++;
        }, 1000); // 1 second delay
    }
}
