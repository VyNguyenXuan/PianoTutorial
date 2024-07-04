package com.example.pianotutorial.features.components.apdapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pianotutorial.databinding.ItemNoteBinding;
import com.example.pianotutorial.models.SongNote;

import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<SongNote> songNotes;

    public NoteAdapter(List<SongNote> songNotes) {
        this.songNotes = songNotes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(songNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return songNotes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        private final ItemNoteBinding binding;

        public NoteViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SongNote songNote) {
            binding.textViewNoteName.setText(songNote.getNoteName());
        }
    }
}
