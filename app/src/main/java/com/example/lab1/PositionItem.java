package com.example.lab1;

public class PositionItem {
    private String name;
    private boolean isChecked;

    public PositionItem(String name) {
        this.name = name;
        this.isChecked = false;
    }

    public String getName() { return name; }
    public boolean isChecked() { return isChecked; }
    public void setChecked(boolean checked) { isChecked = checked; }
}
