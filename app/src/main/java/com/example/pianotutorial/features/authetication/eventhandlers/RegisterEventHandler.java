package com.example.pianotutorial.features.authetication.eventhandlers;

import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.pianotutorial.features.authetication.fragments.ForgotPasswordFragment;
import com.example.pianotutorial.features.authetication.fragments.LoginFragment;
import com.example.pianotutorial.features.authetication.fragments.RegisterFragment;
import com.example.pianotutorial.features.authetication.viewmodels.AuthViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.RegisterViewModel;

import java.util.Objects;

public class RegisterEventHandler {
    private final RegisterViewModel registerViewModel;
    private final AuthViewModel authViewModel;
    private boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    Context context;
    public RegisterEventHandler(RegisterViewModel viewModel, AuthViewModel authViewModel, Context context) {
        this.registerViewModel = viewModel;
        this.authViewModel = authViewModel;
        this.context = context;
    }
    public void navigateToLogin(View view) {
        String password = registerViewModel.getPassword().getValue();
        String email = registerViewModel.getEmail().getValue();
        String repass = registerViewModel.getRepass().getValue();

        if(email==null) email="";
        if(password==null) password="";
        if(repass==null) repass="";

        if(email.isEmpty() || password.isEmpty() || repass.isEmpty()){
            Toast.makeText(context, "vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(repass)){
            Toast.makeText(context, "mật khẩu không khớp nhau!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidEmail(email)) {
            Toast.makeText(context, "Địa chỉ email không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6 || !password.matches(".*[A-Z].*")) {
            Toast.makeText(context, "Mật khẩu phải có ít nhất 6 kí tự và chứa ít nhất một chữ cái viết hoa!", Toast.LENGTH_SHORT).show();
            return;
        }


        registerViewModel.getIsValid().setValue(Boolean.TRUE);


}
    public void navigateBack(View view) {
        authViewModel.getAuthFragment().setValue(authViewModel.getPreviousFragment());
        authViewModel.setPreviousFragment(new ForgotPasswordFragment());

    }
}
