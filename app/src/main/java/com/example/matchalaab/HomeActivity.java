package com.example.matchalaab;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Menampilkan layout activity_home.xml
        setContentView(R.layout.activity_home);

        // Memaksa status bar menjadi putih dan ikon menjadi gelap
        getWindow().setStatusBarColor(android.graphics.Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // 1. Mengenali DrawerLayout dan Tombol Menu dari XML
        drawerLayout = findViewById(R.id.drawerLayout);

        // Memastikan background status bar di DrawerLayout juga putih
        drawerLayout.setStatusBarBackgroundColor(android.graphics.Color.WHITE);

        ImageButton btnMenu = findViewById(R.id.btnMenu);

        // 2. Memberikan perintah agar tombol bisa diklik
        btnMenu.setOnClickListener(v -> {
            // Membuka Drawer dari sebelah kiri (START)
            drawerLayout.openDrawer(GravityCompat.START);
        });

        // Setup Navigasi Drawer (Klik Menu di Samping)
        com.google.android.material.navigation.NavigationView navView = findViewById(R.id.navView);
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Sudah di Home, cukup tutup drawer
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (id == R.id.nav_items) {
                android.content.Intent intent = new android.content.Intent(this, ItemActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_branch) {
                android.content.Intent intent = new android.content.Intent(this, BranchActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_logout) {
                android.content.Intent intent = new android.content.Intent(this, LoginActivity.class);
                intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Setup Tombol Bell
        ImageButton btnBell = findViewById(R.id.btnBell);
        if (btnBell != null) {
            btnBell.setOnClickListener(v -> {
                // Aksi saat notifikasi diklik
            });
        }

        // --- TAMBAHKAN LOGIKA NAVIGASI DI SINI ---
        
        // Klik Card "Items" untuk pindah ke ItemActivity
        findViewById(R.id.cardItems).setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(HomeActivity.this, ItemActivity.class);
            startActivity(intent);
        });

        // Klik Card "Branch" untuk pindah ke BranchActivity
        findViewById(R.id.cardBranch).setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(HomeActivity.this, BranchActivity.class);
            startActivity(intent);
        });

        // Klik Card "Log Out" untuk kembali ke LoginActivity
        findViewById(R.id.cardLogout).setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}