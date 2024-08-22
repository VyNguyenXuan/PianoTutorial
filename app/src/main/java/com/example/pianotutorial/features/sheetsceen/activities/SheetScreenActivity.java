package com.example.pianotutorial.features.sheetsceen.activities;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.sheet.SheetAdapter;
import com.example.pianotutorial.databinding.ActivitySheetScreenBinding;
import com.example.pianotutorial.features.sheetsceen.eventhandlers.SheetScreenEventHandler;
import com.example.pianotutorial.features.sheetsceen.viewmodels.SheetScreenViewModel;

import java.util.ArrayList;
import java.util.List;

public class SheetScreenActivity extends AppCompatActivity {

    private ActivitySheetScreenBinding activitySheetScreenBinding;
    private SheetScreenEventHandler sheetScreenEventHandler;
    private SheetScreenViewModel sheetScreenViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        activitySheetScreenBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_sheet_screen
        );

        // Initialize ViewModel
        sheetScreenViewModel = new ViewModelProvider(this).get(SheetScreenViewModel.class);

        // Initialize EventHandler (corrected)
        sheetScreenEventHandler = new SheetScreenEventHandler(sheetScreenViewModel, this);
        activitySheetScreenBinding.setEventHandler(sheetScreenEventHandler);
        activitySheetScreenBinding.setLifecycleOwner(this);

        // Get the sheetId passed from the previous activity
        if (getIntent().getExtras() != null) {
            int sheetId = getIntent().getExtras().getInt("sheetId", -1); // Default to -1 if not found
            sheetScreenEventHandler.getSheetById(sheetId);
        }

        // Use sheetId as needed in your activity (e.g., to load sheet details)
        sheetScreenViewModel.getCurrentSheet().observe(this, currentSheet -> {
            activitySheetScreenBinding.title1.setText(currentSheet.getInstrumentName());
        });

        sheetScreenViewModel.getIsShowTopBar().observe(this, isShowTopBar -> {
            if (isShowTopBar) {
                activitySheetScreenBinding.topBar.setVisibility(View.VISIBLE);
                Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
                activitySheetScreenBinding.topBar.startAnimation(slideIn);
            } else {
                Animation slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
                activitySheetScreenBinding.topBar.startAnimation(slideOut);
                slideOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        activitySheetScreenBinding.topBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
            }
        });

        sheetScreenViewModel.getIsShowMusicSeek().observe(this, isShowMusicSeek -> {
            if (isShowMusicSeek) {
                activitySheetScreenBinding.listenToMusicButton.setBackgroundTintList(
                        ContextCompat.getColorStateList(this, R.color.darkGreen)
                );
                activitySheetScreenBinding.musicSeekBarLayout.setVisibility(View.VISIBLE);
                Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                activitySheetScreenBinding.musicSeekBarLayout.startAnimation(fadeIn);
            } else {
                activitySheetScreenBinding.listenToMusicButton.setBackgroundTintList(
                        ContextCompat.getColorStateList(this, R.color.green)
                );
                Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
                activitySheetScreenBinding.musicSeekBarLayout.startAnimation(fadeOut);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        activitySheetScreenBinding.musicSeekBarLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
                sheetScreenViewModel.getIsPlayed().setValue(false);
            }
        });

        sheetScreenViewModel.getIsAutoScroll().observe(this, isAutoScroll -> {
            if (isAutoScroll) {
                activitySheetScreenBinding.autoScrollButton.setBackgroundResource(R.drawable.vector_scroll_speed_pause);
            } else {
                activitySheetScreenBinding.autoScrollButton.setBackgroundResource(R.drawable.vector_scroll_speed_play);
            }
        });

        sheetScreenViewModel.getIsPlayed().observe(this, isPlayed -> {
            if (isPlayed) {
                activitySheetScreenBinding.playIcon.setBackgroundResource(R.drawable.vector_pause);
            } else {
                activitySheetScreenBinding.playIcon.setBackgroundResource(R.drawable.vector_play);
            }
        });

        activitySheetScreenBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Convert progress to a value between 0.5 and 2
                float min = 0.5f;
                float max = 2.0f;
                float normalizedProgress = min + (float) progress / 100 * (max - min);

                // Update scrollSpeed in ViewModel
                sheetScreenViewModel.getScrollSpeed().setValue(normalizedProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        sheetScreenViewModel.getScrollSpeed().observe(this, scrollSpeed -> {
            String formattedScrollSpeed = String.format("x%.1f", scrollSpeed);
            activitySheetScreenBinding.scollValue.setText(formattedScrollSpeed);
        });

        // Initialize data
        List<String> imageUrlList = new ArrayList<>();
        // Add sample data to the list
        imageUrlList.add("https://firebasestorage.googleapis.com/v0/b/pianoaiapi.appspot.com/o/Img%2Fz5730364656077_1fe15aea8687ae24ada1c89b7dbb7f88.jpg?alt=media&token=9b5deebf-c94f-4f65-9daa-e94deb6da8e2");
        imageUrlList.add("https://firebasestorage.googleapis.com/v0/b/pianoaiapi.appspot.com/o/Img%2Fz5730364656266_1d590c848b1ee1cb0c336b939d4de9da.jpg?alt=media&token=38ae9257-fd16-4a43-85f9-51ea20ddd308");
        imageUrlList.add("https://firebasestorage.googleapis.com/v0/b/pianoaiapi.appspot.com/o/Img%2Fz5730367051104_a080c83ad1c99d61ded15273bec1f11a.jpg?alt=media&token=6fa38e29-8728-4575-89ad-0f77d46631ba");

        // Initialize the adapter
        SheetAdapter sheetAdapter = new SheetAdapter(this, imageUrlList);

        // Set the adapter and LinearLayoutManager
        activitySheetScreenBinding.recyclerViewSheet.setLayoutManager(new LinearLayoutManager(this));
        activitySheetScreenBinding.recyclerViewSheet.setAdapter(sheetAdapter);
    }
}
