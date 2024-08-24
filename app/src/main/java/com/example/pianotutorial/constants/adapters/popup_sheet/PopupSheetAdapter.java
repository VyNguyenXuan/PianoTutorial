package com.example.pianotutorial.constants.adapters.popup_sheet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ItemSheetPopupBinding;
import com.example.pianotutorial.features.sheetsceen.activities.SheetScreenActivity;
import com.example.pianotutorial.models.Sheet;

import java.util.List;

public class PopupSheetAdapter extends RecyclerView.Adapter<PopupSheetAdapter.PopupSheetViewHolder> {

    private final Context context;
    private final List<Sheet> sheetList;

    public PopupSheetAdapter(Context context, List<Sheet> sheetList) {
        this.context = context;
        this.sheetList = sheetList;
    }

    @NonNull
    @Override
    public PopupSheetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSheetPopupBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_sheet_popup, parent, false);
        return new PopupSheetViewHolder(binding, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PopupSheetViewHolder holder, int position) {
        Sheet sheet = sheetList.get(position);
        holder.bind(sheet);

        // Set click listener for each sheet item
        holder.binding.playButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, SheetScreenActivity.class);
            // Optionally pass data to the next activity (e.g., sheet details)
            intent.putExtra("sheetId", sheet.getId()); // Assuming the Sheet class has an id field
            context.startActivity(intent);
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return sheetList.size();
    }

    static class PopupSheetViewHolder extends RecyclerView.ViewHolder {
        private final ItemSheetPopupBinding binding;
        private final Context context;

        public PopupSheetViewHolder(@NonNull ItemSheetPopupBinding binding, Context context) {
            super(binding.getRoot());
            this.binding = binding;
            this.context = context;
        }

        public void bind(Sheet sheet) {
            // Bind sheet data to the view (e.g., title, composer, etc.)
            binding.sheetName.setText(sheet.getName());

            int color;
            String levelName;
            switch (sheet.getLevel()) {
                case 1:
                    color = ContextCompat.getColor(context, R.color.green);
                    levelName = "Easy";
                    break;
                case 2:
                    color = ContextCompat.getColor(context, R.color.yellow);
                    levelName = "Medium";
                    break;
                case 3:
                    color = ContextCompat.getColor(context, R.color.red);
                    levelName = "Hard";
                    break;
                default:
                    color = ContextCompat.getColor(context, R.color.darkGrey);
                    levelName = "Unknown";
                    break;
            }

            // Set background tint and text color for the UI components
            binding.levelLayout.setBackgroundTintList(ColorStateList.valueOf(color));
            binding.levelTextView.setText(levelName);
            binding.levelTextView.setTextColor(color);
            binding.levelSound.setBackgroundTintList(ColorStateList.valueOf(color));
            binding.playButton.setBackgroundTintList(ColorStateList.valueOf(color));

            // Execute pending bindings for data binding
            binding.executePendingBindings();
        }
    }
}
