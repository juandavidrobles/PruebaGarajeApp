package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
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
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle extras=getIntent().getExtras();

        if (extras != null){
            User=extras.getString("User");
            Password=extras.getString("Password");
            Email=extras.getString("Email");
        }

        eUser=(EditText) findViewById(R.id.eUser);
        ePassword=(EditText) findViewById(R.id.ePassword);

        bRegistrarse=(Button) findViewById(R.id.bRegistrarse);

        bAceptar=(Button) findViewById(R.id.bAceptar);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1234 && resultCode==RESULT_OK){
            extras=data.getExtras();

            User=extras.getString("User");
            Password=extras.getString("Password");
            Email=extras.getString("Email");
        }
    }

    public void clickAceptar(View view){
        if (User.equals("") || Password.equals("")){
            Toast.makeText(getBaseContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
        } else {
            if (User.equals(eUser.getText().toString()) && Password.equals(ePassword.getText().toString())){
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("User",User);
                intent.putExtra("Password",Password);
                intent.putExtra("Email", Email);
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
