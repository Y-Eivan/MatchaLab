package com.example.matchalaab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class BranchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        Button btnHome = findViewById(R.id.btnBranchToHome);
        btnHome.setOnClickListener(v ->
                startActivity(new Intent(BranchActivity.this, HomeActivity.class)));

        Button btnItem = findViewById(R.id.btnBranchToItem);
        btnItem.setOnClickListener(v ->
                startActivity(new Intent(BranchActivity.this, ItemActivity.class)));
    }
}