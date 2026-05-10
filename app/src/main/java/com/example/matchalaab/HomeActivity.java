package com.example.matchalaab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnItem = findViewById(R.id.btnHomeToItem);
        btnItem.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, ItemActivity.class)));

        Button btnBranch = findViewById(R.id.btnHomeToBranch);
        btnBranch.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, BranchActivity.class)));

        Button btnLogout = findViewById(R.id.btnHomeToLogin);
        btnLogout.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, LoginActivity.class)));
    }
}