package com.example.lab1;

public class PositionItem {
    private final String name;
    private boolean isSelected;

    public PositionItem(String name) {
        this.name = name;
        this.isSelected = false;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
