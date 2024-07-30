package com.example.pianotutorial.features.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentForgotpasswordBinding;

public class ForgotPasswordFragment extends Fragment {

    private FragmentForgotpasswordBinding fragmentForgotpasswordBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentForgotpasswordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgotpassword, container, false);

        return fragmentForgotpasswordBinding.getRoot();
    }
}
