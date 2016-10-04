package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jrobles.pruebagarajeapp.R;

public class LoginActivity extends AppCompatActivity {

    Button bRegistrarse, bAceptar;
    EditText eUser, ePassword;
    String User, Password, Email;
    //Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Bundle extras=getIntent().getExtras();



        eUser=(EditText) findViewById(R.id.eUser);
        ePassword=(EditText) findViewById(R.id.ePassword);

        bRegistrarse=(Button) findViewById(R.id.bRegistrarse);

        bAceptar=(Button) findViewById(R.id.bAceptar);
        //CargarPreferencias();
        eUser.setText(getUser());
        if (extras != null){
            if(extras.getString("Pre").equals("Splash")){
                CargarPreferencias();
            }
        }
    }

    public void CargarPreferencias(/*String s*/){
        SharedPreferences sharedPreferences=getSharedPreferences("Mis Preferencias",MODE_PRIVATE);
        eUser.setText(sharedPreferences.getString("User",""));
        if (sharedPreferences.getString("Auto","Off").equals("On")){
            ePassword.setText(sharedPreferences.getString("Password",""));
            clickAceptar(getCurrentFocus());
        }

    }

    public void GuardarPreferencias(){
        SharedPreferences sharedPreferences=getSharedPreferences("Mis Preferencias",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("User", User);
        editor.putString("Password", Password);
        editor.putString("Email", Email);
        editor.commit();
    }

    public String getUser(){
        SharedPreferences sharedPreferences=getSharedPreferences("Mis Preferencias",MODE_PRIVATE);
        return sharedPreferences.getString("User","");
    }

    public String getPassword(){
        SharedPreferences sharedPreferences=getSharedPreferences("Mis Preferencias",MODE_PRIVATE);
        return sharedPreferences.getString("Password","");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1234 && resultCode==RESULT_OK){
            /*extras=data.getExtras();

            User=extras.getString("User");
            Password=extras.getString("Password");
            Email=extras.getString("Email");*/
            //GuardarPreferencias();
            //CargarPreferencias();
            eUser.setText(getUser());
            ePassword.setText(getPassword());

        }
    }

    public void clickAceptar(View view){
        if (getUser().equals("") || getPassword().equals("")){
            Toast.makeText(getBaseContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();
        } else {
            if (getUser().equals(eUser.getText().toString()) && getPassword().equals(ePassword.getText().toString())){
                SharedPreferences sharedPreferences=getSharedPreferences("Mis Preferencias",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Auto", "On");
                editor.commit();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);

                /*intent.putExtra("User",User);
                intent.putExtra("Password",Password);
                intent.putExtra("Email", Email);*/
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getBaseContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void clickRegistrarse(View view){
        Intent intent=new Intent(getApplicationContext(),RegistroActivity.class);
        startActivityForResult(intent,1234);
    }

}
