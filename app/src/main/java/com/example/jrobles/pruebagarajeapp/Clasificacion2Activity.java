package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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

public class Clasificacion2Activity extends AppCompatActivity {

    //------Variables de menu Navigation Drawer-----
    private String[] opciones = new String[]{"Principal", "Hamburguesas", "Bebidas", "Mi Perfil"};
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;
    //----------------------------------------------

    private ViewPager mViewPager2;

    String User, Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion2);

        //-------------Icono menu action bar--------------
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //------------------------------------------------

        /*Bundle extras=getIntent().getExtras();
        User=extras.getString("User");
        Email=extras.getString("Email");
        Password=extras.getString("Password");*/

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager2 = (ViewPager) findViewById(R.id.pager);
        mViewPager2.setAdapter(pagerAdapter);

        ActionBar actionBar2 = getSupportActionBar();
        actionBar2.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                mViewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };

        ActionBar.Tab tab = actionBar2.newTab().setText("Capuccino").setTabListener(tabListener);
        actionBar2.addTab(tab);

        tab = actionBar2.newTab().setText("Malteada de café").setTabListener(tabListener);
        actionBar2.addTab(tab);

        tab = actionBar2.newTab().setText("Malteada de Oreo").setTabListener(tabListener);
        actionBar2.addTab(tab);

        mViewPager2.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            public void onPageSelected(int position){
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });

        //----------------------------------Ajuste del menu--------------------------------
        drawerLayout = (DrawerLayout) findViewById(R.id.contenedorPrincipalActivity2);

        listView = (ListView) findViewById(R.id.menuIzqActivity2);

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
                        //intent = new Intent(getApplicationContext(), Clasificacion2Activity.class);
                        otherAct=false;
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

    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new CapuccinoFragment();
                case 1: return new MalteadacafeFragment();
                case 2: return new MalteadaoreoFragment();
                default: return null;
            }

        }

        @Override
        public int getCount() {
            return 3;

        }
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
                Intent intent = new Intent(this, MiPerfilActivity.class);
                intent.putExtra("User",User);
                intent.putExtra("Email",Email);
                intent.putExtra("Password",Password);
                startActivity(intent);
                break;

            case R.id.mPrincipal:
                Intent intent2=new Intent(this,MainActivity.class);
                intent2.putExtra("User",User);
                intent2.putExtra("Email",Email);
                intent2.putExtra("Password",Password);
                startActivity(intent2);
                finish();
                break;
            case R.id.mClasificacion:
                Intent intent3=new Intent(this,ClasificacionActivity.class);
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

