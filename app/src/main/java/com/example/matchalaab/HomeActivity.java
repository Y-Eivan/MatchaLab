package com.example.matchalaab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ViewPager2 viewPager;
    private Handler autoAdvanceHandler;
    private Runnable autoAdvanceRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.setStatusBarBackgroundColor(Color.WHITE);

        setupGreeting();
        setupDrawer();
        setupCarousel();
        setupTodaysPick();
        setupQuickMenu();
    }

    private void setupGreeting() {
        String username = getSharedPreferences("chi_matcha", MODE_PRIVATE)
                .getString("username", "Guest");

        TextView tvGreeting = findViewById(R.id.tvGreeting);
        TextView tvTimeGreeting = findViewById(R.id.tvTimeGreeting);

        tvGreeting.setText("Welcome, " + username);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour < 12) {
            tvTimeGreeting.setText("Good morning...");
        } else if (hour < 17) {
            tvTimeGreeting.setText("Good afternoon...");
        } else {
            tvTimeGreeting.setText("Good evening...");
        }
    }

    private void setupDrawer() {
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        NavigationView navView = findViewById(R.id.navView);

        String username = getSharedPreferences("chi_matcha", MODE_PRIVATE)
                .getString("username", "Guest");
        android.widget.TextView tvNavUsername = navView.getHeaderView(0).findViewById(R.id.tvNavUsername);
        tvNavUsername.setText(username);

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_items) {
                startActivity(new Intent(this, ItemActivity.class));
            } else if (id == R.id.nav_branch) {
                startActivity(new Intent(this, BranchActivity.class));
            } else if (id == R.id.nav_logout) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        ImageButton btnBell = findViewById(R.id.btnBell);
        if (btnBell != null) btnBell.setOnClickListener(v -> { });
    }

    private void setupCarousel() {
        List<BannerItem> banners = new ArrayList<>();
        banners.add(new BannerItem("Summer Special", "Cool matcha blends", "New", Color.parseColor("#3D5640")));
        banners.add(new BannerItem("Classic Range", "Stone-ground excellence", "Classic", Color.parseColor("#74A177")));
        banners.add(new BannerItem("Sweet Treats", "Matcha desserts & more", "Seasonal", Color.parseColor("#B6748D")));

        viewPager = findViewById(R.id.viewPager);
        ImageButton btnPrev = findViewById(R.id.btnPrev);
        ImageButton btnNext = findViewById(R.id.btnNext);

        View[] dots = {
            findViewById(R.id.dot0),
            findViewById(R.id.dot1),
            findViewById(R.id.dot2)
        };

        BannerAdapter adapter = new BannerAdapter(banners);
        viewPager.setAdapter(adapter);

        final int bannerCount = banners.size();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dots.length; i++) {
                    dots[i].setBackgroundResource(
                        i == position ? R.drawable.dot_active : R.drawable.dot_inactive
                    );
                }
            }
        });

        autoAdvanceHandler = new Handler(Looper.getMainLooper());
        autoAdvanceRunnable = () -> {
            int next = (viewPager.getCurrentItem() + 1) % bannerCount;
            viewPager.setCurrentItem(next, true);
            autoAdvanceHandler.postDelayed(autoAdvanceRunnable, 3000);
        };

        btnPrev.setOnClickListener(v -> {
            autoAdvanceHandler.removeCallbacks(autoAdvanceRunnable);
            int cur = viewPager.getCurrentItem();
            viewPager.setCurrentItem(cur > 0 ? cur - 1 : 0);
            autoAdvanceHandler.postDelayed(autoAdvanceRunnable, 3000);
        });

        btnNext.setOnClickListener(v -> {
            autoAdvanceHandler.removeCallbacks(autoAdvanceRunnable);
            int cur = viewPager.getCurrentItem();
            viewPager.setCurrentItem(cur < bannerCount - 1 ? cur + 1 : bannerCount - 1);
            autoAdvanceHandler.postDelayed(autoAdvanceRunnable, 3000);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoAdvanceHandler != null && autoAdvanceRunnable != null) {
            autoAdvanceHandler.removeCallbacks(autoAdvanceRunnable);
            autoAdvanceHandler.postDelayed(autoAdvanceRunnable, 3000);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (autoAdvanceHandler != null) {
            autoAdvanceHandler.removeCallbacks(autoAdvanceRunnable);
        }
    }

    private void setupQuickMenu() {
        findViewById(R.id.cardItems).setOnClickListener(v ->
                startActivity(new Intent(this, ItemActivity.class)));

        findViewById(R.id.cardBranch).setOnClickListener(v ->
                startActivity(new Intent(this, BranchActivity.class)));

        findViewById(R.id.cardLogout).setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void setupTodaysPick() {
        MatchaItem pick1 = new MatchaItem(1, "Classic Ceremonial",
                "Stone-ground excellence",
                "Pure ceremonial grade matcha, whisked traditionally. Simple, clean, and deeply satisfying.",
                25000, R.drawable.img_matcha_classic, "Hot", "Drink");

        MatchaItem pick2 = new MatchaItem(3, "Iced Matcha Latte",
                "Cold brew",
                "Slow-shaken ceremonial matcha over creamy oat milk, finished with a whisper of cane. A clean, vegetal lift. The kind of cup that makes the afternoon yours.",
                28000, R.drawable.img_matcha_iced, "Iced", "Drink");

        findViewById(R.id.cardPick1).setOnClickListener(v -> openDetail(pick1));
        findViewById(R.id.cardPick2).setOnClickListener(v -> openDetail(pick2));

        TextView tvSeeAll = findViewById(R.id.tvSeeAll);
        if (tvSeeAll != null) {
            tvSeeAll.setOnClickListener(v -> startActivity(new Intent(this, ItemActivity.class)));
        }
    }

    private void openDetail(MatchaItem item) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("name", item.getName());
        intent.putExtra("price", item.getPrice());
        intent.putExtra("desc", item.getDescription());
        intent.putExtra("detailDesc", item.getDetailDescription());
        intent.putExtra("image", item.getImageResId());
        intent.putExtra("tag", item.getTag());
        startActivity(intent);
    }
}
