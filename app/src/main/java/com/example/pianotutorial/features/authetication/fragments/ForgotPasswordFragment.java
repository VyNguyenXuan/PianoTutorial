package com.example.pianotutorial.features.authetication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentForgotpasswordBinding;
import com.example.pianotutorial.features.authetication.eventhandlers.ForgotPasswordEventHandler;
import com.example.pianotutorial.features.authetication.viewmodels.AuthViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.ForgotPasswordViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgotPasswordFragment extends Fragment {

    private FragmentForgotpasswordBinding Binding;
    private AuthViewModel authViewModel;
    private ForgotPasswordViewModel viewModel;
    private ForgotPasswordEventHandler eventHandler;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final String TAG = "ForgotPassActivity";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgotpassword, container, false);
        viewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        eventHandler = new ForgotPasswordEventHandler(viewModel, authViewModel,getContext());
        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);
        Binding.setEventHandler(eventHandler);

        viewModel.getIsValid().observe(getViewLifecycleOwner(), isValid -> {
            if (isValid) {
                mAuth.sendPasswordResetEmail(Objects.requireNonNull(viewModel.getEmail().getValue())).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Chúng tôi đã gửi mail đến hộp thư của bạn để đổi mật khẩu!", Toast.LENGTH_SHORT).show();
                            authViewModel.getAuthFragment().setValue(new LoginFragment());
                            authViewModel.setPreviousFragment(new ForgotPasswordFragment());

                    } else {
                            Log.e(TAG, "Failed to send password reset email", task.getException());
                            Toast.makeText(getActivity(), "Không thể gửi mail. Hãy kiểm tra lại địa chỉ email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return Binding.getRoot();
    }
}
