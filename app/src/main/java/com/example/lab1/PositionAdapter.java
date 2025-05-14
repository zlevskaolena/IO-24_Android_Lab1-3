package com.example.lab1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PositionAdapter extends RecyclerView.Adapter<PositionAdapter.PositionViewHolder> {

    private final List<PositionItem> positionList;

    public PositionAdapter(List<PositionItem> positionList) {
        this.positionList = positionList;
    }

    @NonNull
    @Override
    public PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_position, parent, false);
        return new PositionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionViewHolder holder, int position) {
        PositionItem item = positionList.get(position);
        holder.checkBox.setText(item.getName());
        holder.checkBox.setChecked(item.isSelected());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setSelected(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return positionList.size();
    }

    public List<PositionItem> getSelectedItems() {
        List<PositionItem> selected = new java.util.ArrayList<>();
        for (PositionItem item : positionList) {
            if (item.isSelected()) {
                selected.add(item);
            }
        }
        return selected;
    }

    static class PositionViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public PositionViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.positionCheckBox);
        }
    }
}

