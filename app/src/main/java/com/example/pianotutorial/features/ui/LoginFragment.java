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


import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentLoginBinding;
import com.example.pianotutorial.features.navigation_bar.activities.NavigationBarActivity;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding fragmentLoginBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState){
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        fragmentLoginBinding.backBtn.setOnClickListener(v -> {
            if (getActivity() != null){
                Intent intent = new Intent(getActivity(), MainMenu.class);
                startActivity(intent);
            }
        });

        //
        fragmentLoginBinding.forgotPassword.setOnClickListener(v -> {
                if(getActivity() != null){
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.loginstart, new ForgotPasswordFragment()).addToBackStack(null).commit();
                }
        });

        ///
        TextView txtChuacotk = fragmentLoginBinding.txtChuacotk;
        String text = getString(R.string.not_have_account);
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if(getActivity() != null){
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.loginstart, new RegisterFragment()).addToBackStack(null).commit();
                }
            }
        };

        int startIndex = text.indexOf("Đăng kí ngay");
        int endIndex = startIndex + "Đăng kí ngay".length();

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtChuacotk.setText(spannableString);
        txtChuacotk.setMovementMethod(LinkMovementMethod.getInstance());

        ////
        fragmentLoginBinding.loginBtn.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), NavigationBarActivity.class);
                startActivity(intent);
        });

        return fragmentLoginBinding.getRoot();
    }

}
