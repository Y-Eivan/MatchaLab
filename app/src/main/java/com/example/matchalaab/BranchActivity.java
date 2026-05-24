package com.example.matchalaab;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BranchActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        // Memaksa status bar menjadi putih dan ikon menjadi gelap
        getWindow().setStatusBarColor(android.graphics.Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // 1. Setup Drawer Navigation
        drawerLayout = findViewById(R.id.drawerLayout);
        if (drawerLayout != null) {
            drawerLayout.setStatusBarBackgroundColor(android.graphics.Color.WHITE);
        }
        
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        }

        // Setup Klik Menu di dalam Drawer
        com.google.android.material.navigation.NavigationView navView = findViewById(R.id.navView);
        if (navView != null) {
            navView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    finish(); // Kembali ke Home (tutup activity ini)
                } else if (id == R.id.nav_items) {
                    android.content.Intent intent = new android.content.Intent(this, ItemActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.nav_logout) {
                    android.content.Intent intent = new android.content.Intent(this, LoginActivity.class);
                    intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            });
        }

        // 2. Setup RecyclerView
        RecyclerView rvBranches = findViewById(R.id.rvBranches);
        if (rvBranches != null) {
            rvBranches.setLayoutManager(new LinearLayoutManager(this));

            // 3. Menyiapkan Data Cabang
            List<BranchItem> branchList = new ArrayList<>();
            branchList.add(new BranchItem("MatchaLab Alam Sutera", "Jl. Jalur Sutera No. 25, Tangerang"));
            branchList.add(new BranchItem("MatchaLab Jakarta", "Jl. Sudirman No. 10, Jakarta Pusat"));
            branchList.add(new BranchItem("MatchaLab Medan", "Jl. Gatot Subroto No. 50, Medan"));

            // 4. Hubungkan Adapter
            BranchAdapter adapter = new BranchAdapter(branchList);
            rvBranches.setAdapter(adapter);
        }
    }
}