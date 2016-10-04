package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PromocionActivity extends AppCompatActivity {

    //------Variables de menu Navigation Drawer-----
    private String[] opciones = new String[]{"Principal", "Hamburguesas", "Bebidas", "Mi Perfil"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    //----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocion);

        //-------------Icono menu action bar--------------
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //------------------------------------------------

        //----------------------------------Ajuste del menu--------------------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipal);

        listView = (ListView) findViewById(R.id.menuIzq);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Fragment fragment=null;
                boolean otherAct=false;
                Intent intent=null;
                switch (i){

                    case 0:
                        //fragment=new ThaiFragment();
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        otherAct=true;
                        break;

                    case 1:
                        //fragment=new ThaiFragment();
                        intent = new Intent(getApplicationContext(), ClasificacionActivity.class);
                        otherAct=true;
                        break;
                    case 2:
                        //fragment=new VeggieFragment();
                        intent = new Intent(getApplicationContext(), Clasificacion2Activity.class);
                        otherAct=true;
                        break;
                    case 3:
                        //fragment=new VeronaFragment();
                        intent = new Intent(getApplicationContext(), MiPerfilActivity.class);
                        otherAct=true;
                        break;
                }
                if (otherAct==true){
                    intent.putExtra("User","NombreNada");
                    intent.putExtra("Password","PasswordNada");
                    intent.putExtra("Email","CorreoNada");
                    startActivity(intent);
                    finish();
                }


                /*FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedorFrame, fragment).commit();*/
                listView.setItemChecked(i,true);
                drawerLayout.closeDrawer(listView);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Abierto, R.string.cerrado);

        drawerLayout.setDrawerListener(drawerToggle);

        //-------------------------------------------------------

    }

    //---------Accion del menu------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else{
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //-------------------------------------------------------
}
