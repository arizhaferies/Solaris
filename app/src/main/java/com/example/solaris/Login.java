package com.example.solaris;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email_masuk,pw_masuk;
    Button masuk_lin;
    TextView bikin_baru;
    ProgressBar progressBar2;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ly);

        email_masuk = findViewById(R.id.etEmail);
        pw_masuk = findViewById(R.id.etPassword);
        progressBar2 = findViewById(R.id.pb2);
        fAuth = FirebaseAuth.getInstance();
        masuk_lin = findViewById(R.id.btnLogin);
        bikin_baru = findViewById(R.id.buat_baru);

        masuk_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_masuk.getText().toString().trim();
                String password = pw_masuk.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    email_masuk.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    pw_masuk.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    pw_masuk.setError("Password Must be >= 6 Characters");
                    return;
                }

                progressBar2.setVisibility(View.VISIBLE);

                // authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),main_menu.class));
                        }else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });

        bikin_baru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });
    }
}
