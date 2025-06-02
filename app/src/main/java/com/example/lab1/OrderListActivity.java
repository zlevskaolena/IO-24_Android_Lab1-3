package com.example.lab1;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OrderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        TextView textView = findViewById(R.id.ordersTextView);
        OrderDatabase db = new OrderDatabase(this);
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM orders", null);

        if (cursor.getCount() == 0) {
            textView.setText("Замовлень не знайдено");
        } else {
            StringBuilder builder = new StringBuilder();
            while (cursor.moveToNext()) {
                builder.append("Ім'я: ").append(cursor.getString(1)).append("\n")
                        .append("Стіл: ").append(cursor.getString(2)).append("\n")
                        .append("Вино: ").append(cursor.getString(3)).append("\n")
                        .append("Розмір: ").append(cursor.getString(4)).append("\n")
                        .append("Лід: ").append(cursor.getInt(5) == 1 ? "Так" : "Ні").append("\n\n");
            }
            textView.setText(builder.toString());
        }

        cursor.close();
    }
}
