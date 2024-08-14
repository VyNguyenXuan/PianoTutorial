package com.example.pianotutorial.features.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentForgotpasswordBinding;
import com.example.pianotutorial.features.ui.viewmodel.ForgotPasswordViewModel;

public class ForgotPasswordFragment extends Fragment {

    private FragmentForgotpasswordBinding Binding;
    private ForgotPasswordViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgotpassword, container, false);
        viewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);

        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);

        viewModel.getNavigateBackToLogin().observe(getViewLifecycleOwner(), navigate -> {
            if (navigate) {
                getActivity().onBackPressed();
                viewModel.doneNavigatingToLogin();
            }
        });

        viewModel.getNavigateToCheckEmail().observe(getViewLifecycleOwner(), navigate -> {
            if (navigate != null && navigate){
                navigateToCheckEmail();
                viewModel.doneNavigatingToCheckEmail();
            }
        });

        return Binding.getRoot();
    }
    private void navigateToCheckEmail() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CheckEmailFragment())
                .addToBackStack(null)
                .commit();
    }
}
