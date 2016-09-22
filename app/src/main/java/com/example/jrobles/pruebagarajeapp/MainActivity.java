package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    String User, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras=getIntent().getExtras();
        User=extras.getString("User");
        Email=extras.getString("Email");

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
                startActivity(intent);
                finish();
                break;
            case R.id.mClasificacion:
                Intent intent2=new Intent(this,ClasificacionActivity.class);
                intent2.putExtra("User",User);
                intent2.putExtra("Email",Email);
                startActivity(intent2);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}