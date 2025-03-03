package com.example.pianotutorial.features.authetication.eventhandlers;

import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.pianotutorial.features.authetication.fragments.LoginFragment;
import com.example.pianotutorial.features.authetication.fragments.MainMenuFragment;
import com.example.pianotutorial.features.authetication.fragments.RegisterFragment;
import com.example.pianotutorial.features.authetication.viewmodels.LoginViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.AuthViewModel;
import com.example.pianotutorial.features.authetication.fragments.ForgotPasswordFragment;
import com.google.firebase.auth.FirebaseAuth;

public class LoginEventHandler {
    private static final String TAG = "LoginActivity";
    private final LoginViewModel loginViewModel;
    private final AuthViewModel authViewModel;
    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    Context context;

    public LoginEventHandler(LoginViewModel viewModel, AuthViewModel authViewModel, Context context) {
        this.loginViewModel = viewModel;
        this.authViewModel = authViewModel;
        this.context = context;
    }

    public void navigateToNavigationBar(View view) {
        String password = loginViewModel.getPassword().getValue();
        String email = loginViewModel.getEmail().getValue();
        if(email==null) email="";
        if(password==null) password="";

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(context, "Địa chỉ email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        loginViewModel.getIsValid().setValue(Boolean.TRUE);
    }

    public void navigateToForgotPassword(View view) {
        authViewModel.getAuthFragment().setValue(new ForgotPasswordFragment());
        authViewModel.setPreviousFragment(new LoginFragment());
    }
    public void navigateToRegister(View view){
        authViewModel.getAuthFragment().setValue(new RegisterFragment());
        authViewModel.setPreviousFragment(new LoginFragment());
    }
    public void navigateBack(View view) {
        authViewModel.getAuthFragment().setValue(new MainMenuFragment());
        authViewModel.setPreviousFragment(new ForgotPasswordFragment());

    }
}