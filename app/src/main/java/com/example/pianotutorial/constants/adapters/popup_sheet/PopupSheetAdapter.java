package com.example.pianotutorial.constants.adapters.popup_sheet;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.R;
import com.example.pianotutorial.databinding.ItemSheetPopupBinding;

import java.util.List;

public class PopupSheetAdapter extends RecyclerView.Adapter<PopupSheetAdapter.PopupSheetViewHolder> {

    private final List<Integer> numberList;

    public PopupSheetAdapter(List<Integer> numberList) {
        this.numberList = numberList;
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
        Integer number = numberList.get(position);
        holder.bind(number);
    }

    @Override
    public int getItemCount() {
        return numberList.size();
    }

    static class PopupSheetViewHolder extends RecyclerView.ViewHolder {
        private final ItemSheetPopupBinding binding;

        public PopupSheetViewHolder(@NonNull ItemSheetPopupBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Integer number) {
            binding.executePendingBindings();
        }
    }
}
