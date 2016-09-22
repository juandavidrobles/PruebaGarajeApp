package com.example.jrobles.pruebagarajeapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ClasificacionActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    String User, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);

        Bundle extras=getIntent().getExtras();
        User=extras.getString("User");
        Email=extras.getString("Email");

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(pagerAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };

        ActionBar.Tab tab = actionBar.newTab().setText("Thai").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Veggie").setTabListener(tabListener);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Verona").setTabListener(tabListener);
        actionBar.addTab(tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            public void onPageSelected(int position){
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });

    }

    public class PagerAdapter extends FragmentPagerAdapter{

        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new ThaiFragment();
                case 1: return new VeggieFragment();
                case 2: return new VeronaFragment();
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
                startActivity(intent);
                break;

            case R.id.mPrincipal:
                Intent intent2=new Intent(this,MainActivity.class);
                intent2.putExtra("User",User);
                intent2.putExtra("Email",Email);
                startActivity(intent2);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
