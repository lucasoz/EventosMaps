package com.lucas.oz.eventify;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.SupportMapFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new AdaptadorPager(getSupportFragmentManager()));

    }


    private class AdaptadorPager extends FragmentPagerAdapter{

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return "NOTICIAS";
            }else{
                return "MAPA";
            }
        }

        public AdaptadorPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new NoticiasFragment();
            }else{
                return new MapaFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
