package com.example.pianotutorial.features.components.apdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.databinding.ItemMeasureBinding;
import com.example.pianotutorial.models.Measure;

import java.util.List;

public class MeasureAdapter extends RecyclerView.Adapter<MeasureAdapter.MeasureViewHolder> {

    private final Context context;
    private final List<Measure> measures;

    public MeasureAdapter(List<Measure> measures, Context context) {
        this.measures = measures;
        this.context = context;
    }

    @NonNull
    @Override
    public MeasureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item_measure layout
        ItemMeasureBinding itemMeasureBinding = ItemMeasureBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MeasureViewHolder(itemMeasureBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasureViewHolder holder, int position) {
        Measure measure = measures.get(position);
        holder.bind(measure);
    }

    @Override
    public int getItemCount() {
        return measures.size();
    }

    public static class MeasureViewHolder extends RecyclerView.ViewHolder {
        private final ItemMeasureBinding itemMeasureBinding;

        public MeasureViewHolder(@NonNull ItemMeasureBinding itemMeasureBinding) {
            super(itemMeasureBinding.getRoot());
            this.itemMeasureBinding = itemMeasureBinding;
        }

        public void bind(Measure measure) {

            // Create and set up note adapter
            NoteAdapter noteAdapter = new NoteAdapter(measure.getSongNotes());
            itemMeasureBinding.recyclerViewNotes.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            itemMeasureBinding.recyclerViewNotes.setAdapter(noteAdapter);
        }
    }
}
