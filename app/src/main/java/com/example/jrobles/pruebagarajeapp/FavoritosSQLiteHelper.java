package com.example.jrobles.pruebagarajeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JRobles on 19/10/2016.
 */

public class FavoritosSQLiteHelper extends SQLiteOpenHelper {

    private String DATA_BASE_NAME = "FavoritosBD";
    private int DATA_VERSION=1;

    String sqlCreate = "CREATE TABLE Favoritos (" +
            "idfavorito INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idusuario INTEGER, " +
            "idproducto INTEGER)";

    public FavoritosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserData");
        db.execSQL(sqlCreate);
    }
}
