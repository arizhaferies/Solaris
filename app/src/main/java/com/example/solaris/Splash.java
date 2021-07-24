package com.example.solaris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openActivity1();
            }
        });

        Button signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openActivity2();
            }
        });
    }
    public void openActivity1(){
        Intent intent = new Intent (this, Login.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent h = new Intent (this, Signup.class);
        startActivity(h);
    }

}