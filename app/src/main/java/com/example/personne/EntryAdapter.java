package com.example.personne;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    private List<String> entries;
    private List<String> dates;

    public EntryAdapter(Cursor cursor) {
        ArrayList<String> tempEntries = new ArrayList<>();
        ArrayList<String> tempDates = new ArrayList<>();
        while (cursor.moveToNext()) {
            tempEntries.add(cursor.getString(0));
            tempDates.add(cursor.getString(1));
        }
        this.entries = tempEntries;
        this.dates = tempDates;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_item, parent, false);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        holder.entryTextView.setText(entries.get(position));
        holder.dateTextView.setText(dates.get(position));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {
        public TextView entryTextView;
        public TextView dateTextView;

        public EntryViewHolder(View itemView) {
            super(itemView);
            entryTextView = itemView.findViewById(R.id.entry_text);
            dateTextView = itemView.findViewById(R.id.entry_date);
        }
    }
}
