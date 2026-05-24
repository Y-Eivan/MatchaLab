package com.example.matchalaab; // Pastikan package name sesuai

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        // Memaksa status bar menjadi putih dan ikon menjadi gelap
        getWindow().setStatusBarColor(android.graphics.Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // 1. Setup Drawer Navigation
        drawerLayout = findViewById(R.id.drawerLayout);
        // Memastikan background status bar di DrawerLayout juga putih
        drawerLayout.setStatusBarBackgroundColor(android.graphics.Color.WHITE);

        ImageButton btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Setup Klik Menu di dalam Drawer
        com.google.android.material.navigation.NavigationView navView = findViewById(R.id.navView);
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Kembali ke Home (tutup activity ini)
                finish();
            } else if (id == R.id.nav_branch) {
                // Pindah ke BranchActivity
                android.content.Intent intent = new android.content.Intent(this, BranchActivity.class);
                startActivity(intent);
                finish(); // Opsional: tutup ItemActivity agar tidak menumpuk
            } else if (id == R.id.nav_logout) {
                // Keluar ke LoginActivity
                android.content.Intent intent = new android.content.Intent(this, LoginActivity.class);
                intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // 2. Setup RecyclerView
        RecyclerView rvItems = findViewById(R.id.rvItems);
        rvItems.setLayoutManager(new GridLayoutManager(this, 2)); // 2 kolom

        // 3. Menyiapkan Data Produk (Dummy Data)
        List<MatchaItem> itemList = new ArrayList<>();
        // Pastikan nama file drawable di bawah sudah ada di res/drawable kamu
        itemList.add(new MatchaItem(1, "Matcha Classic", "Signature drink", 25000, R.drawable.img_matcha_classic, "Hot", "Drink"));
        itemList.add(new MatchaItem(2, "Matcha Iced", "Cool refreshing", 28000, R.drawable.img_matcha_iced, "Cold", "Drink"));

        // 4. Hubungkan Adapter dengan RecyclerView
        ItemAdapter adapter = new ItemAdapter(itemList);
        rvItems.setAdapter(adapter);
    }
}