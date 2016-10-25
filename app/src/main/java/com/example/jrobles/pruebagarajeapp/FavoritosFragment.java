package com.example.jrobles.pruebagarajeapp;


import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {

    String[] nombres;

    ListView listView;

    //---------Implementacion SQLite------------
    FavoritosSQLiteHelper favoritos;
    SQLiteDatabase dbFavoritos;

    UserDataSQLiteHelper userdata;
    SQLiteDatabase dbUserData;

    ProductsListSQLiteHelper productslist;
    SQLiteDatabase dbProductsList;

    ContentValues dataBD;

    String usuario;

    String[] fav = new String[] {"", "", "", "", ""};

    int idUsuario;

    SharedPreferences preferences;

    ArrayList<String> favs = new ArrayList<String>();

    ArrayList<Integer> f = new ArrayList<Integer>();

    String string = "Hola";
    //------------------------------------------

    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        //---------Implementacion SQLite------------
        userdata = new UserDataSQLiteHelper(getActivity(), "UserData", null, 1);
        dbUserData = userdata.getWritableDatabase();

        favoritos = new FavoritosSQLiteHelper(getActivity(), "Favoritos", null, 1);
        dbFavoritos = favoritos.getWritableDatabase();

        productslist = new ProductsListSQLiteHelper(getActivity(), "ProductsList", null, 1);
        dbProductsList = productslist.getWritableDatabase();
        //------------------------------------------

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        usuario=preferences.getString("User","");

        Cursor c = dbUserData.rawQuery("SELECT * FROM UserData WHERE nombre='"+usuario+"'", null);

        if (c.moveToFirst()){
            idUsuario=c.getInt(0);
            //Toast.makeText(getActivity(), "Toast 0 "+String.valueOf(idUsuario), Toast.LENGTH_LONG).show();
        }

        Cursor cursor = dbFavoritos.rawQuery("SELECT * FROM Favoritos WHERE idusuario='"+idUsuario+"'", null);

        Cursor cursor2;

        int i=0;
        /*for(cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()){
            String name = cur.getString(nameColumn);
            String phoneNumber = cur.getString(phoneColumn);
        }*/
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            f.add(i++, cursor.getInt(2));
        }

        /*if (cursor.moveToFirst()) {

            do {
                f.add(i, cursor.getInt(2));
                i++;
            } while (c.moveToNext());
            Toast.makeText(getActivity(), "Tamaño de f: "+String.valueOf(f.size()), Toast.LENGTH_SHORT).show();

        }*/
        i = 0;
        //do {
            //c = dbProductsList.rawQuery("SELECT * FROM ProductsList ORDER BY id ASC LIMIT "+5, null);
            //c = dbProductList.rawQuery("SELECT * FROM ProductList WHERE id='"+f.get(i)+"'", null);
        int j;
        for (j=0;j<f.size();j++){
            cursor2 = dbProductsList.rawQuery("SELECT * FROM ProductsList WHERE id='"+f.get(j)+"'", null);
            if (cursor2.moveToFirst()){
                do {
                    favs.add(i, cursor2.getString(1));
                    i++;
                } while (cursor2.moveToNext());
            }
        }
        i=0;

            //} while (i<f.size());


                /*if (c.moveToFirst()){
                    favs.add(i,c.getString(1));
                }*/



        //i=favs.size();
        //favsId.add(1, "Holi");

        //Toast.makeText(getActivity(), "Toast prueba: "+favs.get(0), Toast.LENGTH_LONG).show();
        //Toast.makeText(getActivity(), "Toast prueba: "+String.valueOf(favs.size()), Toast.LENGTH_LONG).show();

        //favs.add(0, "j");

        /*int limit = 0;

        limit= favs.size();

        fav = new String[limit];

        for (i=0; i<limit; i++){
            fav[i]=favs.get(i);
        }*/




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, favs);

        listView = (ListView) view.findViewById(R.id.lFavoritos);

        listView.setAdapter(adapter);

        return view;
    }

}
