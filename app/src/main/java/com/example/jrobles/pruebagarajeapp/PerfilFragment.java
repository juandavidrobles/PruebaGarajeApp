package com.example.jrobles.pruebagarajeapp;


import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


    private TextView textView1, textView2;

    //---------Implementacion SQLite------------
    UserDataSQLiteHelper userdata;
    SQLiteDatabase dbUserData;
    //------------------------------------------

    String usuario, correo;

    SharedPreferences preferences;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        //---------Implementacion SQLite------------
        userdata = new UserDataSQLiteHelper(getActivity(), "UserData", null, 1);
        dbUserData = userdata.getWritableDatabase();
        //------------------------------------------

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        usuario=preferences.getString("User","");

        correo=preferences.getString("Email","");




        // Inflate the layout for this fragment
        textView1 = (TextView) view.findViewById(R.id.tName);
        textView2 = (TextView) view.findViewById(R.id.tMail);

        textView1.setText(usuario);

        setTextEmail(correo);

        return view;
    }

    @Override
    public void onResume() {

        /*textView1 = (TextView) getView().findViewById(R.id.tName);
        textView2 = (TextView) getView().findViewById(R.id.tMail);*/

        super.onResume();
    }

    public void setTextUser(String text){
        //textView1 = (TextView) getView().findViewById(R.id.tName);
        this.textView1.setText(text);
    }

    public void setTextEmail(String text){
        //textView2 = (TextView) getView().findViewById(R.id.tMail);
        this.textView2.setText(text);
    }


}
