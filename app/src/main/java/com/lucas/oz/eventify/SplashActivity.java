package com.lucas.oz.eventify;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Runnable ejecutable = new Runnable() {
            @Override
            public void run() {
                siguenteActividad();
                finish();
            }
        };

        Handler miHandler = new Handler();
        miHandler.postDelayed(ejecutable,1500);

    }

    public void siguenteActividad(){

        Intent intento = new Intent(this,MainActivity.class);
        startActivity(intento);
    }

}
