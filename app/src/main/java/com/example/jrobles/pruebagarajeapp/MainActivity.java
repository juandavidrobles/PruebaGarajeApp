package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    String User, Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras=getIntent().getExtras();
        User=extras.getString("User");
        Email=extras.getString("Email");
        Password=extras.getString("Password");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.mMiPerfil:
                Intent intent=new Intent(this,MiPerfilActivity.class);
                intent.putExtra("User",User);
                intent.putExtra("Email",Email);
                intent.putExtra("Password",Password);
                startActivity(intent);
                finish();
                break;
            case R.id.mClasificacion:
                Intent intent2=new Intent(this,ClasificacionActivity.class);
                intent2.putExtra("User",User);
                intent2.putExtra("Email",Email);
                intent2.putExtra("Password",Password);
                startActivity(intent2);
                finish();
                break;
            case R.id.mClasificacion2:
                Intent intent3=new Intent(this,Clasificacion2Activity.class);
                intent3.putExtra("User",User);
                intent3.putExtra("Email",Email);
                intent3.putExtra("Password",Password);
                startActivity(intent3);
                finish();
                break;
            case R.id.mSignout:
                Intent intent4=new Intent(this,LoginActivity.class);
                intent4.putExtra("User",User);
                intent4.putExtra("Email",Email);
                intent4.putExtra("Password",Password);
                startActivity(intent4);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}