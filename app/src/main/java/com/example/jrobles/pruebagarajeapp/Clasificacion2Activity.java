package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Clasificacion2Activity extends AppCompatActivity {

    private ViewPager mViewPager2;

    String User, Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion2);

        Bundle extras=getIntent().getExtras();
        User=extras.getString("User");
        Email=extras.getString("Email");
        Password=extras.getString("Password");

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager2 = (ViewPager) findViewById(R.id.pager);
        mViewPager2.setAdapter(pagerAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

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

        ActionBar.Tab tab = actionBar.newTab().setText("Capuccino").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Malteada de café").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Malteada de Oreo").setTabListener(tabListener);
        actionBar.addTab(tab);

        mViewPager2.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            public void onPageSelected(int position){
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });

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
            case R.id.mMiPerfil:
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

