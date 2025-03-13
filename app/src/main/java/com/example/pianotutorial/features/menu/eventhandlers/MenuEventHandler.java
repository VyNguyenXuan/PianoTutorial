package com.example.pianotutorial.features.menu.eventhandlers;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.pianotutorial.features.edit_profile.activities.EditProfileActivity;
import com.example.pianotutorial.features.navigation_bar.viewmodels.NavigationBarViewModel;

public class MenuEventHandler {
    Context context;
    public MenuEventHandler(Context context) {
        this.context=context;
    }
    public void onEditProfileClick(View view){
        Intent intent = new Intent(context, EditProfileActivity.class);
        context.startActivity(intent);
    }
}
