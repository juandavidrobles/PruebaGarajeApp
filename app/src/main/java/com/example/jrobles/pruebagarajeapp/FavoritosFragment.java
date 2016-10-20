package com.example.jrobles.pruebagarajeapp;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

    ContentValues dataBD;

    String usuario;

    String[] fav = new String[] {"H"};

    int idUsuario;

    ArrayList<String> favs, favsId = new ArrayList<String>();
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
        //------------------------------------------

        Cursor c = dbUserData.rawQuery("SELECT * FROM UserData WHERE nombre='"+usuario+"'", null);

        if (c.moveToFirst()){
            idUsuario=c.getInt(0);
        }

        //Toast.makeText(getActivity(), String.valueOf(c.getCount()),Toast.LENGTH_LONG).show();

        Cursor cursor = dbFavoritos.rawQuery("SELECT * FROM Favoritos WHERE idusuario='"+idUsuario+"'", null);

        int i=0;
        if (cursor.moveToFirst()){
            while (c.moveToNext()){
                favsId.add(i,cursor.getString(2));

                c = dbUserData.rawQuery("SELECT * FROM UserData WHERE id='"+favsId.get(i)+"'", null);

                if (c.moveToFirst()){
                    favs.add(i,c.getString(1));
                }
            }
            i++;
        }

        //favs.add(0, "j");

        /*int limit = 0;

        limit= favs.size();

        fav = new String[limit];

        for (i=0; i<limit; i++){
            fav[i]=favs.get(i);
        }*/




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, fav);

        listView = (ListView) view.findViewById(R.id.lFavoritos);

        listView.setAdapter(adapter);

        return view;
    }

}
