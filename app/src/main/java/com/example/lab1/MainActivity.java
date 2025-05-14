package com.example.lab1;
import com.example.lab1.R;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<PositionItem> positionList;
    private PositionAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] wineNames = getResources().getStringArray(R.array.wine_names);

        List<PositionItem> positions = new ArrayList<>();
        for (String wine : wineNames) {
            positions.add(new PositionItem(wine));
        }

        PositionAdapter adapter = new PositionAdapter(positions);

        TextView selectWineText = findViewById(R.id.selectWineText);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RadioGroup sizeRadioGroup = findViewById(R.id.sizeRadioGroup);

        EditText nameEditText = findViewById(R.id.name);
        EditText tableEditText = findViewById(R.id.Table);
        CheckBox iceCheckBox = findViewById(R.id.iceCheckBox);
        Button orderButton = findViewById(R.id.order);
        TextView resultTextView = findViewById(R.id.result);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        selectWineText.setOnClickListener(v -> {
            if (recyclerView.getVisibility() == View.GONE) {
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.GONE);
            }
        });
        sizeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.RB0_2) {
                // дія для 0.2
            } else if (checkedId == R.id.RB0_4) {
                // дія для 0.4
            } else if (checkedId == R.id.RB0_5) {
                // дія для 0.5
            } else if (checkedId == R.id.RB1) {
                // дія для 1
            }
        });
        orderButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String table = tableEditText.getText().toString().trim();

            // Отримання обраного розміру
            int selectedSizeId = sizeRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedSizeButton = findViewById(selectedSizeId);
            String selectedSize = selectedSizeButton.getText().toString();

            // Чи потрібен лід
            boolean withIce = iceCheckBox.isChecked();

            // Отримання обраних вин
            List<String> selectedWines = new ArrayList<>();
            for (PositionItem item : positions) {
                if (item.isChecked()) {
                    selectedWines.add(item.getName());
                }
            }

            // Формування підсумкового повідомлення
            String summary = "Ім’я: " + name + "\n"
                    + "Стіл: " + table + "\n"
                    + "Розмір: " + selectedSize + "\n"
                    + "Лід: " + (withIce ? "Так" : "Ні") + "\n"
                    + "Вина: " + (selectedWines.isEmpty() ? "Не обрано" : String.join(", ", selectedWines));

            // Вивід результату
            resultTextView.setText(summary);
        });

    }
}
