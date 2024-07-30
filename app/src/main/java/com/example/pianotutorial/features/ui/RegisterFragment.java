package com.example.pianotutorial.features.ui;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding fragmentRegisterBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        fragmentRegisterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);


        fragmentRegisterBinding.backBtn.setOnClickListener(v -> {
            if (getActivity() != null){
                getActivity().onBackPressed();
            }

        });

//        TextView CoTk = view.findViewById(R.id.co_tk);
//        String text = getString(R.string.have_account);
//        SpannableString spannableString = new SpannableString(text);
//
//        ClickableSpan clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View widget) {
//                if(getActivity() != null){
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.registerfragment, new LoginFragment()).addToBackStack(null).commit();
//                }
//            }
//        };
//
//        int startIndex = text.indexOf("Đăng nhập");
//        int endIndex = startIndex + "Đăng nhập".length();
//
//        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        CoTk.setText(spannableString);
//        CoTk.setMovementMethod(LinkMovementMethod.getInstance());
        return fragmentRegisterBinding.getRoot();
    }
}
