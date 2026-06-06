package com.example.matchalaab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowInsetsController;
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

        //white status bar with dark icons
        getWindow().setStatusBarColor(Color.WHITE);
        WindowInsetsController controller = getWindow().getInsetsController();
        if (controller != null) {
            controller.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }

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
            //show username in drawer header
            String username = getSharedPreferences("chi_matcha", MODE_PRIVATE)
                    .getString("username", "Guest");
            TextView tvNavUsername = navView.getHeaderView(0).findViewById(R.id.tvNavUsername);
            if (tvNavUsername != null) tvNavUsername.setText(username);

            //drawer navigation
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
                "Jl. Sutera No. 25, Tangerang",
                "07:00 - 22:00"));
        branchList.add(new BranchItem(
                "Chi Matcha Jakarta Pusat",
                "Jl. Sudirman No. 10, Jakarta Pusat",
                "08:00 - 21:00"));
        branchList.add(new BranchItem(
                "Chi Matcha Medan",
                "Jl. Gatot Subroto No. 50, Medan",
                "09:00 - 22:00"));

        RecyclerView rvBranches = findViewById(R.id.rvBranches);
        if (rvBranches != null) {
            rvBranches.setLayoutManager(new LinearLayoutManager(this));
            rvBranches.setAdapter(new BranchAdapter(branchList));
        }
    }
}
