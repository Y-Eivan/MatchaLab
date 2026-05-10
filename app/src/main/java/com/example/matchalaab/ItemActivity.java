package com.example.matchalaab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Button btnDetail = findViewById(R.id.btnItemToDetail);
        btnDetail.setOnClickListener(v ->
                startActivity(new Intent(ItemActivity.this, ItemDetailActivity.class)));

        Button btnHome = findViewById(R.id.btnItemToHome);
        btnHome.setOnClickListener(v ->
                startActivity(new Intent(ItemActivity.this, HomeActivity.class)));

        Button btnBranch = findViewById(R.id.btnItemToBranch);
        btnBranch.setOnClickListener(v ->
                startActivity(new Intent(ItemActivity.this, BranchActivity.class)));
    }
}