package com.example.matchalaab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class BranchActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(
                android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        drawerLayout = findViewById(R.id.drawerLayout);
        if (drawerLayout != null) drawerLayout.setStatusBarBackgroundColor(Color.WHITE);

        setupDrawer();
        setupBranches();
    }

    private void setupDrawer() {
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        }

        NavigationView navView = findViewById(R.id.navView);
        if (navView != null) {
            String username = getSharedPreferences("chi_matcha", MODE_PRIVATE)
                    .getString("username", "Guest");
            TextView tvNavUsername = navView.getHeaderView(0).findViewById(R.id.tvNavUsername);
            if (tvNavUsername != null) tvNavUsername.setText(username);

            navView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    finish();
                } else if (id == R.id.nav_items) {
                    startActivity(new Intent(this, ItemActivity.class));
                    finish();
                } else if (id == R.id.nav_branch) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (id == R.id.nav_logout) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            });
        }
    }

    private void setupBranches() {
        List<BranchItem> branchList = new ArrayList<>();
        branchList.add(new BranchItem(
                "Chi Matcha Alam Sutera",
                "Jl. Alam Sutera Blvd No. 8, Tangerang",
                "Mon-Sun 07:00 - 22:00",
                "Flagship",
                -6.2271, 106.6508));
        branchList.add(new BranchItem(
                "Chi Matcha Cigaten",
                "Jl. Raya Serpong No. 12, Tangerang Selatan",
                "Mon-Sun 08:00 - 21:00",
                "New",
                -6.2650, 106.7100));
        branchList.add(new BranchItem(
                "Chi Matcha BSD",
                "Jl. BSD Raya Utama No. 5, Tangerang Selatan",
                "Mon-Sun 09:00 - 22:00",
                "",
                -6.3015, 106.6533));

        TextView tvHeadline = findViewById(R.id.tvBranchHeadline);
        if (tvHeadline != null) {
            tvHeadline.setText(getString(R.string.headline_branch_count, branchList.size()));
        }

        RecyclerView rvBranches = findViewById(R.id.rvBranches);
        if (rvBranches != null) {
            rvBranches.setLayoutManager(new LinearLayoutManager(this));
            rvBranches.setAdapter(new BranchAdapter(branchList));
        }
    }
}
