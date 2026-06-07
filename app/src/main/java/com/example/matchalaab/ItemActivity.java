package com.example.matchalaab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowInsetsController;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ItemAdapter adapter;
    private final List<MatchaItem> allItems = new ArrayList<>(); //details in MatchaItem.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //white status bar with dark icons
        getWindow().setStatusBarColor(Color.WHITE);
        WindowInsetsController controller = getWindow().getInsetsController();
        if (controller != null) {
            controller.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.setStatusBarBackgroundColor(Color.WHITE);

        setupDrawer();
        setupItems();
        setupChipFilter();
    }

    private void setupDrawer() {
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        NavigationView navView = findViewById(R.id.navView);

        //show username in drawer header
        String username = getSharedPreferences("chi_matcha", MODE_PRIVATE)
                .getString("username", "Guest");
        android.widget.TextView tvNavUsername = navView.getHeaderView(0).findViewById(R.id.tvNavUsername);
        tvNavUsername.setText(username);

        //drawer navigation, finish() used to avoid stacking activities
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                finish();
            } else if (id == R.id.nav_branch) {
                startActivity(new Intent(this, BranchActivity.class));
                finish();
            } else if (id == R.id.nav_logout) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void setupItems() {
        //full product catalog
        allItems.add(new MatchaItem(1, "Matcha Latte",
                "Stone-ground excellence",
                "Whisked-to-order ceremonial grade matcha paired with perfectly steamed oat milk. Earthy, smooth, and warming in every sip.",
                25000, R.drawable.img_matcha_latte, "Hot", "Drink"));

        allItems.add(new MatchaItem(2, "Ceremonial Matcha",
                "Single origin",
                "Pure ceremonial grade matcha, whisked traditionally with hot water. Simple, clean, and deeply satisfying.",
                30000, R.drawable.img_ceremonial_matcha, "Hot", "Drink"));

        allItems.add(new MatchaItem(3, "Iced Matcha Latte",
                "Cold brew",
                "Slow-shaken ceremonial matcha over creamy oat milk, finished with a whisper of cane. A clean, vegetal lift. The kind of cup that makes the afternoon yours.",
                28000, R.drawable.img_matcha_iced, "Iced", "Drink"));

        allItems.add(new MatchaItem(4, "Iced Brown Sugar Matcha",
                "Brown sugar blend",
                "Our iced matcha layered with house-made brown sugar syrup and fresh milk. Sweet, earthy, and effortlessly refreshing.",
                32000, R.drawable.img_iced_brown_sugar, "Iced", "Drink"));

        allItems.add(new MatchaItem(5, "Matcha Mochi",
                "Soft & chewy",
                "House-made mochi filled with ceremonial grade matcha paste and sweet red bean. A comforting bite of tradition.",
                18000, R.drawable.img_matcha_mochi, "Desserts", "Dessert"));

        allItems.add(new MatchaItem(6, "Matcha Affogato",
                "Espresso meets matcha",
                "A double shot of ceremonial matcha poured over premium vanilla gelato. Bold, bitter, sweet — all at once.",
                35000, R.drawable.img_matcha_affogato, "Desserts", "Dessert"));

        allItems.add(new MatchaItem(7, "Matcha Beans",
                "Roasted & coated",
                "Whole roasted coffee beans coated in ceremonial grade matcha white chocolate. A crunchy, energizing snack.",
                22000, R.drawable.img_matcha_beans, "Beans", "Snack"));

        //2-column grid layout
        RecyclerView rvItems = findViewById(R.id.rvItems);
        rvItems.setLayoutManager(new GridLayoutManager(this, 2));
        rvItems.setHasFixedSize(false);

        adapter = new ItemAdapter(new ArrayList<>(allItems));
        rvItems.setAdapter(adapter);
    }

    private void setupChipFilter() {
        //filter items by category tag on chip selection
        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;
            int chipId = checkedIds.get(0);

            String filter;
            if (chipId == R.id.chipHot) filter = "Hot";
            else if (chipId == R.id.chipIced) filter = "Iced";
            else if (chipId == R.id.chipDesserts) filter = "Desserts";
            else if (chipId == R.id.chipBeans) filter = "Beans";
            else filter = "All";

            List<MatchaItem> filtered = new ArrayList<>();
            for (MatchaItem item : allItems) {
                if (filter.equals("All") || item.getTag().equals(filter)) {
                    filtered.add(item);
                }
            }
            adapter.updateItems(filtered);
        });
    }
}
