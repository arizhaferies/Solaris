package com.example.solaris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private int wait=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() { 
            @Override
            public void run() {
                Intent home=new Intent(MainActivity.this, Splash.class);
                startActivity(home);
                finish();

            }
        },wait);
    }
}