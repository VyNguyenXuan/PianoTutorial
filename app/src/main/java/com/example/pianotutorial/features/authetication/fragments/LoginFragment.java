package com.example.pianotutorial.features.authetication.fragments;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.FragmentLoginBinding;
import com.example.pianotutorial.features.authetication.viewmodels.AuthViewModel;
import com.example.pianotutorial.features.authetication.viewmodels.LoginViewModel;
import com.example.pianotutorial.features.authetication.eventhandlers.LoginEventHandler;
import com.example.pianotutorial.features.navigation_bar.activities.NavigationBarActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginFragment extends Fragment {
    private LoginViewModel viewModel;
    private LoginEventHandler eventHandler;
    private FragmentLoginBinding Binding;
    private AuthViewModel authViewModel;
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";
    //
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance(); // Khởi tạo FirebaseAuth
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        eventHandler = new LoginEventHandler(viewModel, authViewModel,getContext());
        Binding.setEventhandler(eventHandler);
        Binding.setViewModel(viewModel);
        Binding.setLifecycleOwner(this);

        viewModel.getIsValid().observe(getViewLifecycleOwner(), isValid -> {
            if (isValid) {
                mAuth.signInWithEmailAndPassword(Objects.requireNonNull(viewModel.getEmail().getValue()), Objects.requireNonNull(viewModel.getPassword().getValue()))
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getActivity(), "Đăng Nhập Thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), NavigationBarActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getActivity(), "Sai Tài Khoản Hoặc Mật khẩu!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        return Binding.getRoot();
    }
}