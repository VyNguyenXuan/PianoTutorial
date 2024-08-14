package com.example.pianotutorial.features.sheetsceen.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pianotutorial.R;
import com.example.pianotutorial.constants.adapters.sheet.SheetAdapter;
import com.example.pianotutorial.databinding.ActivitySheetScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class SheetActivity extends AppCompatActivity {

    private ActivitySheetScreenBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize view binding
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_sheet_screen
        );

        // Initialize data
        List<String> imageUrlList = new ArrayList<>();
        // Add sample data to the list
        imageUrlList.add("https://firebasestorage.googleapis.com/v0/b/pianoaiapi.appspot.com/o/Img%2Fz5730364656077_1fe15aea8687ae24ada1c89b7dbb7f88.jpg?alt=media&token=9b5deebf-c94f-4f65-9daa-e94deb6da8e2");
        imageUrlList.add("https://firebasestorage.googleapis.com/v0/b/pianoaiapi.appspot.com/o/Img%2Fz5730364656266_1d590c848b1ee1cb0c336b939d4de9da.jpg?alt=media&token=38ae9257-fd16-4a43-85f9-51ea20ddd308");
        imageUrlList.add("https://firebasestorage.googleapis.com/v0/b/pianoaiapi.appspot.com/o/Img%2Fz5730367051104_a080c83ad1c99d61ded15273bec1f11a.jpg?alt=media&token=6fa38e29-8728-4575-89ad-0f77d46631ba");

        // Initialize the adapter
        SheetAdapter sheetAdapter = new SheetAdapter(this, imageUrlList);

        // Set the adapter and GridLayoutManager with 2 columns
        binding.recyclerViewSheet.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewSheet.setAdapter(sheetAdapter);

    }

}
