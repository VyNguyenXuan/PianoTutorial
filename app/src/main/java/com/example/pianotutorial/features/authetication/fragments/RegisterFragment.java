package com.example.pianotutorial.features.authetication.fragments;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.tasks.OnCompleteListener;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentRegisterBinding;
import com.example.pianotutorial.features.authetication.eventhandlers.RegisterEventHandler;
import com.example.pianotutorial.features.authetication.viewmodels.AuthViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.RegisterViewModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;
    private FragmentRegisterBinding Binding;
    private RegisterEventHandler eventHandler;
    private AuthViewModel authViewModel;
    private FirebaseAuth mAuth;
    //
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance(); // Khởi tạo FirebaseAuth
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);
        eventHandler = new RegisterEventHandler(viewModel, authViewModel,getContext());
        Binding.setEventHandler(eventHandler);

        viewModel.getIsValid().observe(getViewLifecycleOwner(), isValid -> {
            if (isValid) {
                mAuth.createUserWithEmailAndPassword(Objects.requireNonNull(viewModel.getEmail().getValue()), Objects.requireNonNull(viewModel.getPassword().getValue()))
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getActivity(), "Đăng Kí Thành Công!",
                                            Toast.LENGTH_SHORT).show();
                                    authViewModel.getAuthFragment().setValue(new LoginFragment());
                                    authViewModel.setPreviousFragment(new RegisterFragment());

                                } else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        return Binding.getRoot();
    }
}
