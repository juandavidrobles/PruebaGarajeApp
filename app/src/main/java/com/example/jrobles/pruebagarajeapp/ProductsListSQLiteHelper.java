package com.example.jrobles.pruebagarajeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JRobles on 19/10/2016.
 */

public class ProductsListSQLiteHelper extends SQLiteOpenHelper {

    private String DATA_BASE_NAME = "ProductsListBD";
    private int DATA_VERSION=1;

    String sqlCreate = "CREATE TABLE ProductsList (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, " +
            "descripcion TEXT, " +
            "precio INTEGER," +
            "imgid INTEGER)";

    public ProductsListSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

        ContentValues contentValues = new ContentValues();

        contentValues.put("nombre", "Promo 1: Campesina Rock Burguer");
        contentValues.put("descripcion", "Nada aun");
        contentValues.put("precio", 4500);
        contentValues.put("imgid", R.drawable.promocion);
        db.insert("ProductsList", null, contentValues);

        contentValues.put("nombre", "Promo 2: Glam Rock & Burger");
        contentValues.put("descripcion", "Nada aun");
        contentValues.put("precio", 4500);
        contentValues.put("imgid", R.drawable.promocion2);
        db.insert("ProductsList", null, contentValues);

        contentValues.put("nombre", "Promo 3: Día del padre");
        contentValues.put("descripcion", "Nada aun");
        contentValues.put("precio", 4500);
        contentValues.put("imgid", R.drawable.promocion3);
        db.insert("ProductsList", null, contentValues);

        contentValues.put("nombre", "Promo 4: Santander vs Verona");
        contentValues.put("descripcion", "Nada aun");
        contentValues.put("precio", 4500);
        contentValues.put("imgid", R.drawable.promocion4);
        db.insert("ProductsList", null, contentValues);

        contentValues.put("nombre", "Promo 5: El garaje en Medellín");
        contentValues.put("descripcion", "Nada aun");
        contentValues.put("precio", 4500);
        contentValues.put("imgid", R.drawable.promocion5);
        db.insert("ProductsList", null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ProductsList");
        db.execSQL(sqlCreate);
    }
}
