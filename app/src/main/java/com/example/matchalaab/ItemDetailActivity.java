package com.example.matchalaab;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // Menangkap data dari Intent
        String name = getIntent().getStringExtra("name");
        double price = getIntent().getDoubleExtra("price", 0);
        int image = getIntent().getIntExtra("image", 0);
        String desc = getIntent().getStringExtra("desc");

        // Menampilkan ke layar
        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvPrice = findViewById(R.id.tvDetailPrice);
        ImageView img = findViewById(R.id.imgDetail);
        TextView tvDesc = findViewById(R.id.tvDetailDesc);

        tvName.setText(name);
        tvPrice.setText("Rp " + price);
        img.setImageResource(image);
        tvDesc.setText(desc);

        // Tombol Kembali
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        
        // Warna status bar biar tidak ungu
        getWindow().setStatusBarColor(android.graphics.Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}