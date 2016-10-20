package com.example.jrobles.pruebagarajeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JRobles on 16/10/2016.
 */

public class UserDataSQLiteHelper extends SQLiteOpenHelper {

    private String DATA_BASE_NAME = "UserData";
    private int DATA_VERSION=1;

    String sqlCreate = "CREATE TABLE UserData (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, " +
            "contrasena TEXT, " +
            "correo TEXT)";

    public UserDataSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
