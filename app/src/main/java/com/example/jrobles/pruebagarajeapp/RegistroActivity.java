package com.example.jrobles.pruebagarajeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    //---------Implementacion SQLite------------
    UserDataSQLiteHelper userdata;
    SQLiteDatabase dbUserData;

    ContentValues dataBD;

    String usuario, contrasena, correo;
    //------------------------------------------

    Button bAceptar, bCancelar;
    EditText eUser, ePassword, ePasswordAgain, eEmail;
    boolean vacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //---------Implementacion SQLite------------
        userdata = new UserDataSQLiteHelper(this, "UserData", null, 1);
        dbUserData = userdata.getWritableDatabase();
        //------------------------------------------

        bAceptar=(Button) findViewById(R.id.bAceptar);
        bCancelar=(Button) findViewById(R.id.bCancelar);

        eUser=(EditText) findViewById(R.id.eUser);
        ePassword=(EditText) findViewById(R.id.ePassword);
        ePasswordAgain=(EditText) findViewById(R.id.ePasswordAgain);
        eEmail=(EditText) findViewById(R.id.eEmail);

        bAceptar.setOnClickListener(this);
        bCancelar.setOnClickListener(this);


    }

    public void GuardarPreferencias(){
//        SharedPreferences sharedPreferences=getSharedPreferences("Mis Preferencias",MODE_PRIVATE);
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("User", eUser.getText().toString());
        editor.putString("Password", ePassword.getText().toString());
        editor.putString("Email", eEmail.getText().toString());
        editor.commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bAceptar:

                //---------Implementacion SQLite------------
                usuario = eUser.getText().toString();
                contrasena = ePassword.getText().toString();
                correo = eEmail.getText().toString();
                //------------------------------------------

                vacio=TextUtils.isEmpty(eUser.getText().toString()) ||
                        TextUtils.isEmpty(ePassword.getText().toString()) ||
                        TextUtils.isEmpty(ePasswordAgain.getText().toString()) ||
                        TextUtils.isEmpty(eEmail.getText().toString());

                if(TextUtils.isEmpty(eUser.getText().toString())){
                    eUser.setError("Campo no puede quedar vacio");
                }
                if(TextUtils.isEmpty(ePassword.getText().toString())){
                    ePassword.setError("Campo no puede quedar vacio");
                }
                if(TextUtils.isEmpty(ePasswordAgain.getText().toString())){
                    ePasswordAgain.setError("Campo no puede quedar vacio");
                }
                if(TextUtils.isEmpty(eEmail.getText().toString())){
                    eEmail.setError("Campo no puede quedar vacio");
                }

                if (vacio==false){
                    if (ePassword.getText().toString().equals(ePasswordAgain.getText().toString())){

                        //---------Implementacion SQLite------------
                        Cursor c = dbUserData.rawQuery("select * from UserData where nombre='"+usuario+"'",null);

                        if (c != null){
                            c.moveToFirst();
                        }

                        if (c.moveToFirst()){
                            Toast.makeText(getBaseContext(), "Ya hay un registro con ese usuario", Toast.LENGTH_LONG).show();
                            eUser.setError("Ya hay un registro con ese usuario");
                        } else {
                            dataBD = new ContentValues();
                            dataBD.put("usuario", usuario);
                            dataBD.put("contrasena", contrasena);
                            dataBD.put("correo", correo);

                            dbUserData.insert("UserData", null, dataBD);
                            dbUserData.execSQL("INSERT INTO UserData VALUES(null, '"+usuario+"', '"+contrasena+"', " +
                                    "'"+correo+"')");
                            //------------------------------------------

                            GuardarPreferencias();
                            Intent intent=new Intent();
                            intent.putExtra("User",eUser.getText().toString());
                            intent.putExtra("Password",ePassword.getText().toString());
                            intent.putExtra("Email",eEmail.getText().toString());
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(getBaseContext(),"Contraseñas no coinciden",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.bCancelar:

                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                setResult(RESULT_CANCELED,intent);
                finish();

                break;

        }
    }
}