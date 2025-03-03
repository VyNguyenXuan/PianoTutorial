package com.example.pianotutorial.features.authetication.eventhandlers;

import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.pianotutorial.features.authetication.fragments.CheckEmailFragment;
import com.example.pianotutorial.features.authetication.fragments.ForgotPasswordFragment;
import com.example.pianotutorial.features.authetication.fragments.LoginFragment;
import com.example.pianotutorial.features.authetication.viewmodels.ForgotPasswordViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.AuthViewModel;

public class ForgotPasswordEventHandler {
    private final ForgotPasswordViewModel forgotPasswordViewModel;
    private final AuthViewModel authViewModel;
    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    Context context;
    public ForgotPasswordEventHandler(ForgotPasswordViewModel viewModel, AuthViewModel authViewModel, Context context) {
        this.forgotPasswordViewModel = viewModel;
        this.authViewModel = authViewModel;
        this.context = context;
    }
    public void navigateBack(View view) {
        authViewModel.getAuthFragment().setValue(new LoginFragment());
        authViewModel.setPreviousFragment(new ForgotPasswordFragment());
    }
    public void navigateToLogin(View view){
        String email = forgotPasswordViewModel.getEmail().getValue();
        if(email==null) email="";

        if (email.isEmpty()) {
            Toast.makeText(context, "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(context, "Địa chỉ email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        forgotPasswordViewModel.getIsValid().setValue(Boolean.TRUE);
    }

}
