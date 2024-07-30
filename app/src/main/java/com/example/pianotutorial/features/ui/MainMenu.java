package com.example.pianotutorial.features.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ActivityMainmenuBinding;
import com.google.android.material.button.MaterialButton;

public class MainMenu extends AppCompatActivity {

    private ActivityMainmenuBinding activityMainmenuBinding;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainmenuBinding = DataBindingUtil.setContentView(this, R.layout.activity_mainmenu);
        setTitle("Menu");

        activityMainmenuBinding.dangnhapBtn.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainmenu_container, new LoginFragment()).addToBackStack(null).commit();
        });

        activityMainmenuBinding.dangkyBtn.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainmenu_container, new RegisterFragment()).addToBackStack(null).commit();
        });
    }
}
