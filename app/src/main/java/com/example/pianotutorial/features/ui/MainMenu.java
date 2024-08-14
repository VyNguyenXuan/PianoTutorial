package com.example.pianotutorial.features.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ActivityMainmenuBinding;
import com.example.pianotutorial.features.navigation_bar.activities.NavigationBarActivity;
import com.example.pianotutorial.features.ui.viewmodel.MainMenuViewModel;
import com.google.android.material.button.MaterialButton;

public class MainMenu extends AppCompatActivity {

    private ActivityMainmenuBinding Binding;
    private MainMenuViewModel viewModel;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = DataBindingUtil.setContentView(this, R.layout.activity_mainmenu);
        viewModel = new ViewModelProvider(this).get(MainMenuViewModel.class);

        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        viewModel.getNavigateToLogin().observe(this, navigate -> {
            if (navigate != null && navigate){
                showLoadingIndicator();
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (!(currentFragment instanceof LoginFragment)) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new LoginFragment()).addToBackStack(null).commit();
                }
                new Handler(Looper.getMainLooper()).postDelayed(this::hideLoadingIndicator, 500);
                viewModel.doneNavigating();
            }
        });



        viewModel.getNavigateToRegister().observe(this, navigate -> {
            if (navigate != null && navigate){
                showLoadingIndicator();
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (!(currentFragment instanceof RegisterFragment)) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new RegisterFragment()).addToBackStack(null).commit();
                }
                new Handler(Looper.getMainLooper()).postDelayed(this::hideLoadingIndicator, 500);
                viewModel.doneNavigating();
            }
        });


    }
    private void showLoadingIndicator() {
        Binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoadingIndicator() {
        Binding.progressBar.setVisibility(View.GONE);
    }


}