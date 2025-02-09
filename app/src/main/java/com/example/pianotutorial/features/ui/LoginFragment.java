package com.example.pianotutorial.features.ui;

import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;


import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentLoginBinding;
import com.example.pianotutorial.features.navigation_bar.activities.NavigationBarActivity;
import com.example.pianotutorial.features.ui.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    private LoginViewModel viewModel;
    private FragmentLoginBinding Binding;
    //
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);
        viewModel.getNavigateBackToMainMenu().observe(getViewLifecycleOwner(), navigate -> {
            if (navigate) {
                getActivity().onBackPressed();
                viewModel.doneNavigatingBack();
            }
        });

        viewModel.getNavigateToForgotPassword().observe(getViewLifecycleOwner(), navigate -> {
            if (navigate != null && navigate){
                navigateToForgotPassword();
                viewModel.doneNavigateToForgotPassword();
            }
        });
        viewModel.getNavigateToNavbar().observe(getViewLifecycleOwner(), navigate -> {
            if (navigate != null && navigate){
                navigateToNavbar();
                viewModel.doneNavigateToNavbar();
            }
        });
//        String text = getString(R.string.not_have_account);
//        SpannableString spannableString = new SpannableString(text);
//        ClickableSpan clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View widget) {
//                viewModel.onRegisterLinkClicked();
//            }
//        };
//
//        int startIndex = text.indexOf("Đăng kí ngay");
//        int endIndex = startIndex + "Đăng kí ngay".length();
//
//        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        Binding.txtChuacotk.setText(spannableString);
//
//        viewModel.navigateToRegister.observe(getViewLifecycleOwner(), navigate -> {
//            if(navigate != null && navigate){
//                NavHostFragment.findNavController(LoginFragment.this)
//                        .navigate(R.id.action_loginFragment_to_registerFragment);
//                viewModel.doneNavigatingToRegister();
//            }
//        });
//
        return Binding.getRoot();
    }
    private void navigateToForgotPassword() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ForgotPasswordFragment())
                .addToBackStack(null)
                .commit();
    }
    private void navigateToNavbar() {
        Intent intent = new Intent(getActivity(), NavigationBarActivity.class);
        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Binding = null;
    }

}