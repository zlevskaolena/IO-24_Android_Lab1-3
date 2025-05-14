package com.example.lab1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OrderFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_TABLE = "table";
    private static final String ARG_ICE = "ice";
    private static final String ARG_SIZE = "size";
    private static final String ARG_ITEMS = "items";

    private String name;
    private String table;
    private boolean ice;
    private String size;
    private String items;

    public OrderFragment() {}

    public static OrderFragment newInstance(String name, String table, boolean ice, String size, String items) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_TABLE, table);
        args.putBoolean(ARG_ICE, ice);
        args.putString(ARG_SIZE, size);
        args.putString(ARG_ITEMS, items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            table = getArguments().getString(ARG_TABLE);
            ice = getArguments().getBoolean(ARG_ICE);
            size = getArguments().getString(ARG_SIZE);
            items = getArguments().getString(ARG_ITEMS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.order_fragment, container, false);
        TextView resultTextView = view.findViewById(R.id.result);

        String result = "Ім'я: " + name +
                "\nСтіл: " + table +
                "\nЛід: " + (ice ? "Так" : "Ні") +
                "\nРозмір: " + size +
                "\nЗамовлено: " + items;

        resultTextView.setText(result);

        Button btnOk = view.findViewById(R.id.btn_ok);
        Button btnOrderMore = view.findViewById(R.id.btn_order_more);
        Button btnReorder = view.findViewById(R.id.btn_reorder);

        btnOk.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        btnOrderMore.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new FormFragment())
                    .addToBackStack(null)
                    .commit();
        });

        btnReorder.setOnClickListener(v -> {

            Bundle args = getArguments();
            if (args != null) {
                FormFragment formFragment = new FormFragment();
                formFragment.setArguments(args);
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, formFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
