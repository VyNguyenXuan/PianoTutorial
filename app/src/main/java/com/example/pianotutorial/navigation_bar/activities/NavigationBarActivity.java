package com.example.pianotutorial.navigation_bar.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ActivityNavigationBarBinding;
import com.example.pianotutorial.navigation_bar.evenhandlers.NavigationBarEventHandler;
import com.example.pianotutorial.navigation_bar.viewmodels.NavigationBarViewModel;

public class NavigationBarActivity extends AppCompatActivity {
    private ActivityNavigationBarBinding _activityNavigationBarBinding;
    private NavigationBarViewModel _navigationBarViewModel;
    private NavigationBarEventHandler _navigationBarEventHandler;
    private FragmentTransaction _transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _activityNavigationBarBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_navigation_bar
        );
        _navigationBarViewModel = new ViewModelProvider(this).get(NavigationBarViewModel.class);

        _navigationBarEventHandler = new NavigationBarEventHandler(_navigationBarViewModel, this);

        _activityNavigationBarBinding.setActivityNavigationBarEventHandler(_navigationBarEventHandler);

        _navigationBarViewModel.getCustomerFragment().observe(this, customerFragment -> {
            _transaction = getSupportFragmentManager().beginTransaction();
            _transaction.replace(R.id.fragment_container, customerFragment);
            _transaction.addToBackStack(null);
            _transaction.commit();
        });

    }


}
