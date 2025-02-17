package com.example.pianotutorial.features.authetication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ActivityMainmenuBinding;
import com.example.pianotutorial.features.authetication.eventhandlers.LoginEventHandler;
import com.example.pianotutorial.features.authetication.eventhandlers.MainMenuEventHandler;
import com.example.pianotutorial.features.authetication.viewmodels.MainMenuViewModel;

public class MainMenuActivity extends AppCompatActivity {

    private ActivityMainmenuBinding Binding;
    private MainMenuViewModel viewModel;
    private MainMenuEventHandler eventHandler;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = DataBindingUtil.setContentView(this, R.layout.activity_mainmenu);
        viewModel = new ViewModelProvider(this).get(MainMenuViewModel.class);
        eventHandler = new MainMenuEventHandler(viewModel,this);
        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);
        Binding.setEventHandler(eventHandler);
    }


}