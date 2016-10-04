package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //------Variables de menu Navigation Drawer-----
    private String[] opciones = new String[]{"Principal", "Hamburguesas", "Bebidas", "Mi Perfil"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    //----------------------------------------------

    String User, Email, Password;

    String[] listaPromociones = new String[] {"Promo 1: Campesina Rock Burguer", "Promo 2: Glam Rock & Burger", "Promo 3: Día del padre", "Promo 4: Santander vs Verona", "Promo 5: El garaje en Medellín"};
    ListView listView2;

    ListView list;

    Integer[] imgid={R.drawable.promocion, R.drawable.promocion2, R.drawable.promocion3, R.drawable.promocion4, R.drawable.promocion5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListaPersonalizada adapter=new ListaPersonalizada(this, listaPromociones, imgid);
        list=(ListView)findViewById(R.id.lView2);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                /*String Slecteditem= listaPromociones[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();*/
                Intent intent = null;
                intent = new Intent(getBaseContext(), PromocionesActivity.class);
                int n;
                switch (position){
                    case 0:
                        n=0;
                        intent.putExtra("Promo", n);
                        break;
                    case 1:
                        n=1;
                        intent.putExtra("Promo", n);
                        break;
                    case 2:
                        n=2;
                        intent.putExtra("Promo", n);
                        break;
                    case 3:
                        n=3;
                        intent.putExtra("Promo", n);
                        break;
                    case 4:
                        n=4;
                        intent.putExtra("Promo", n);
                        break;
                }
                startActivity(intent);
                finish();

            }
        });

        //-------------Icono menu action bar--------------
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //------------------------------------------------

        /*Bundle extras=getIntent().getExtras();
        if (extras!=null){
            User=extras.getString("User");
            Email=extras.getString("Email");
            Password=extras.getString("Password");
        }*/


        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listaPromociones);*/

        //listView2=(ListView) findViewById(R.id.lView2);

        //listView2.setAdapter(adapter);

        //----------------------------------Ajuste del menu--------------------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipalMain);

        listView = (ListView) findViewById(R.id.menuIzqMain);

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

                        otherAct=false;
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
                    /*intent.putExtra("User","NombreNada");
                    intent.putExtra("Password","PasswordNada");
                    intent.putExtra("Email","CorreoNada");*/
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            /*case R.id.mMiPerfil:
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
                break;*/
            case R.id.mSignout:
                SharedPreferences sharedPreferences=getSharedPreferences("Mis Preferencias",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Auto", "Off");
                editor.commit();
                Intent intent4=new Intent(this,LoginActivity.class);
                /*intent4.putExtra("User",User);
                intent4.putExtra("Email",Email);
                intent4.putExtra("Password",Password);*/
                startActivity(intent4);
                finish();
                break;
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

}