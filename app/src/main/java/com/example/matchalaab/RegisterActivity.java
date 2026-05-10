package com.example.matchalaab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnHome = findViewById(R.id.btnRegisterToHome);
        btnHome.setOnClickListener(v ->
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class)));

        Button btnLogin = findViewById(R.id.btnRegisterToLogin);
        btnLogin.setOnClickListener(v ->
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }
}