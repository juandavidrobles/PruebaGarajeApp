package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    Button bAceptar, bCancelar;
    EditText eUser, ePassword, ePasswordAgain, eEmail;
    boolean vacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        bAceptar=(Button) findViewById(R.id.bAceptar);
        bCancelar=(Button) findViewById(R.id.bCancelar);

        eUser=(EditText) findViewById(R.id.eUser);
        ePassword=(EditText) findViewById(R.id.ePassword);
        ePasswordAgain=(EditText) findViewById(R.id.ePasswordAgain);
        eEmail=(EditText) findViewById(R.id.eEmail);

    }

    public void clickAceptar(View view){

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
                Intent intent=new Intent();
                intent.putExtra("User",eUser.getText().toString());
                intent.putExtra("Password",ePassword.getText().toString());
                intent.putExtra("Email",eEmail.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            } else {
                Toast.makeText(getBaseContext(),"Contraseñas no coinciden",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getBaseContext(),"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
        }

    }

    public void clickCancelar(View view){

        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
        setResult(RESULT_CANCELED,intent);
        finish();

    }



}
