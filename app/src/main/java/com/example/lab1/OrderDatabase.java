package com.example.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wine_orders.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "orders";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TABLE = "table_number";
    public static final String COLUMN_WINE = "wine";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_ICE = "ice";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_TABLE + " TEXT, " +
                    COLUMN_WINE + " TEXT, " +
                    COLUMN_SIZE + " TEXT, " +
                    COLUMN_ICE + " INTEGER" + ");";

    public OrderDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addOrder(String name, String table, String wine, String size, boolean ice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TABLE, table);
        values.put(COLUMN_WINE, wine);
        values.put(COLUMN_SIZE, size);
        values.put(COLUMN_ICE, ice ? 1 : 0);  // SQLite не має типу BOOLEAN, тому використовуємо 1/0

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
