package com.example.pianotutorial.constants.adapters.popup_sheet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
        return new PopupSheetViewHolder(binding);
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
            ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return sheetList.size();
    }

    static class PopupSheetViewHolder extends RecyclerView.ViewHolder {
        private final ItemSheetPopupBinding binding;

        public PopupSheetViewHolder(@NonNull ItemSheetPopupBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Sheet sheet) {
            // Bind sheet data to the view (e.g., title, composer, etc.)
            binding.sheetName.setText(sheet.getInstrumentName());
            // You can bind other fields of the Sheet class here as needed
            binding.executePendingBindings();
        }
    }
}
