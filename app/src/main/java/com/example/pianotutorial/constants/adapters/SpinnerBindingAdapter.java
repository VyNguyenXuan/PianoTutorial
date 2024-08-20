package com.example.pianotutorial.constants.adapters;

import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;

public class SpinnerBindingAdapter {

    @BindingAdapter("onItemSelected")
    public static void setOnItemSelectedListener(Spinner spinner, AdapterView.OnItemSelectedListener listener) {
        if (listener != null) {
            spinner.setOnItemSelectedListener(listener);
        }
    }
}
