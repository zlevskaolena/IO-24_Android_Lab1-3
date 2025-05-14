package com.example.lab1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FormFragment extends Fragment {

    public FormFragment() {}

    private PositionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.form_fragment, container, false);

        TextView selectWineText = view.findViewById(R.id.selectWineText);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RadioGroup sizeRadioGroup = view.findViewById(R.id.sizeRadioGroup);
        EditText nameEditText = view.findViewById(R.id.name);
        EditText tableEditText = view.findViewById(R.id.Table);
        CheckBox iceCheckBox = view.findViewById(R.id.iceCheckBox);
        Button orderButton = view.findViewById(R.id.order);



        String[] wineNames = getResources().getStringArray(R.array.wine_names);
        List<PositionItem> positions = new ArrayList<>();
        for (String wine : wineNames) {
            positions.add(new PositionItem(wine));
        }

        adapter = new PositionAdapter(positions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        selectWineText.setOnClickListener(v -> {
            recyclerView.setVisibility(recyclerView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        });

        orderButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String table = tableEditText.getText().toString();
            boolean ice = iceCheckBox.isChecked();

            int checkedId = sizeRadioGroup.getCheckedRadioButtonId();
            String size = "";
            if (checkedId == R.id.RB0_2) size = "0.2";
            else if (checkedId == R.id.RB0_4) size = "0.4";
            else if (checkedId == R.id.RB0_5) size = "0.5";
            else if (checkedId == R.id.RB1) size = "1.0";

            List<String> selectedItems = new ArrayList<>();
            for (PositionItem item : adapter.getSelectedItems()) {
                selectedItems.add(item.getName());
            }
            String items = selectedItems.isEmpty() ? "Нічого не вибрано" : android.text.TextUtils.join(", ", selectedItems);

            OrderFragment orderFragment = OrderFragment.newInstance(name, table, ice, size, items);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, orderFragment)
                    .addToBackStack(null)
                    .commit();
        });

        Bundle args = getArguments();
        if (args != null) {
            String name = args.getString("name", "");
            String table = args.getString("table", "");
            boolean ice = args.getBoolean("ice", false);
            String size = args.getString("size", "");
            ArrayList<String> selectedItems = args.getStringArrayList("items");

            nameEditText.setText(name);
            tableEditText.setText(table);
            iceCheckBox.setChecked(ice);

            switch (size) {
                case "0.2":
                    sizeRadioGroup.check(R.id.RB0_2);
                    break;
                case "0.4":
                    sizeRadioGroup.check(R.id.RB0_4);
                    break;
                case "0.5":
                    sizeRadioGroup.check(R.id.RB0_5);
                    break;
                case "1":
                    sizeRadioGroup.check(R.id.RB1);
                    break;
            }

            if (selectedItems != null) {
                for (PositionItem item : positions) {
                    if (selectedItems.contains(item.getName())) {
                        item.setSelected(true);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }


        return view;
    }
}



