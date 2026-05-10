package com.example.matchalaab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnHome = findViewById(R.id.btnLoginToHome);
        btnHome.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, HomeActivity.class)));

        Button btnRegister = findViewById(R.id.btnLoginToRegister);
        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }
}