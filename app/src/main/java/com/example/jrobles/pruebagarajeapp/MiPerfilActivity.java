package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MiPerfilActivity extends AppCompatActivity {

    TextView tName, tMail;
    String User, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);



        tName=(TextView) findViewById(R.id.tName);
        tMail=(TextView) findViewById(R.id.tMail);

        Bundle extras=getIntent().getExtras();

        User=extras.getString("User");
        Email=extras.getString("Email");

        tName.setText(User);
        tMail.setText(Email);

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
            case R.id.mClasificacion:
                Intent intent2 = new Intent(this, ClasificacionActivity.class);

                intent2.putExtra("User",User);
                intent2.putExtra("Email",Email);
                startActivity(intent2);
                finish();
                break;


            case R.id.mPrincipal:


                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("User",User);
                intent.putExtra("Email",Email);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
