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
import androidx.lifecycle.ViewModelProvider;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentRegisterBinding;
import com.example.pianotutorial.features.ui.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;
    private FragmentRegisterBinding Binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);

        viewModel.getNavigateBackToMainMenu().observe(getViewLifecycleOwner(), navigate ->{
            if(navigate) {
                getActivity().onBackPressed();
                viewModel.doneNavigatingBack();
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
        return Binding.getRoot();
    }
}
