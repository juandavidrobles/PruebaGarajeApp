package com.example.jrobles.pruebagarajeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PromocionesActivity extends AppCompatActivity {

    int frag;

    //---------Implementacion SQLite------------
    FavoritosSQLiteHelper favoritos;
    SQLiteDatabase dbFavoritos;

    UserDataSQLiteHelper userdata;
    SQLiteDatabase dbUserData;

    ContentValues dataBD;
    //------------------------------------------

    String usuario;

    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);

        //---------Implementacion SQLite------------
        userdata = new UserDataSQLiteHelper(this, "UserData", null, 1);
        dbUserData = userdata.getWritableDatabase();
        //------------------------------------------

        usuario=CargarPreferencias();

        //Toast.makeText(getBaseContext(), usuario, Toast.LENGTH_LONG).show();

        Cursor c = dbUserData.rawQuery("SELECT * FROM UserData WHERE nombre='"+usuario+"'", null);

        if (c.moveToFirst()){
            idUsuario=c.getInt(0);
        }


        //---------Implementacion SQLite------------
        favoritos = new FavoritosSQLiteHelper(this, "Favoritos", null, 1);
        dbFavoritos = favoritos.getWritableDatabase();
        //------------------------------------------

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Bundle extras = getIntent().getExtras();



        Fragment fragment=null;
        switch (extras.getInt("Promo")){
            case 0:
                frag=1;
                fragment=new Promo1Fragment();
                break;
            case 1:
                frag=2;
                fragment=new Promo2Fragment();
                break;
            case 2:
                frag=3;
                fragment=new Promo3Fragment();
                break;
            case 3:
                frag=4;
                fragment=new Promo4Fragment();
                break;
            case 4:
                frag=5;
                fragment=new Promo5Fragment();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_promociones, fragment).commit();


    }

    public String CargarPreferencias(){
        String s;
        //SharedPreferences sharedPreferences=getSharedPreferences("Mis Preferencias",MODE_PRIVATE);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        s=sharedPreferences.getString("User","");
        return s;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Cursor cursor = null;

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.chckFavorito:
                //switch (frag){
                    //case 0:
                        cursor = dbFavoritos.rawQuery("SELECT * FROM Favoritos WHERE idusuario='"+idUsuario+"' AND idproducto='"+frag+"'", null);

                        if (cursor.moveToFirst()){
                            Toast.makeText(getBaseContext(), "Borrado de favoritos", Toast.LENGTH_SHORT).show();
                            dbFavoritos.delete("Favoritos","idusuario='"+idUsuario+"' AND idproducto='"+frag+"'",null);
                        } else {
                            Toast.makeText(getBaseContext(), "Agregado a favoritos", Toast.LENGTH_SHORT).show();
                            dataBD = new ContentValues();
                            dataBD.put("idusuario", idUsuario);
                            dataBD.put("idproducto", frag);

                            dbFavoritos.insert("Favoritos", null, dataBD);
                        }

                    /*    break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }*/


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_promociones, menu);
        return true;
    }



}
