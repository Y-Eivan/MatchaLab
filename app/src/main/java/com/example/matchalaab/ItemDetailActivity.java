package com.example.matchalaab;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowInsetsController;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Locale;

public class ItemDetailActivity extends AppCompatActivity {

    private EditText etQuantity;
    private LinearLayout quantityStepper;
    private TextView tvQtyError;
    private MaterialButton btnOrder;
    private double itemPrice;
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        //colored status bar to match hero image area
        getWindow().setStatusBarColor(getResources().getColor(R.color.secondary, null));
        WindowInsetsController controller = getWindow().getInsetsController();
        if (controller != null) {
            controller.setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }

        //receive item data from intent
        String name       = getIntent().getStringExtra("name");
        itemPrice         = getIntent().getDoubleExtra("price", 0);
        String detailDesc = getIntent().getStringExtra("detailDesc");
        String shortDesc  = getIntent().getStringExtra("desc");
        int imageRes      = getIntent().getIntExtra("image", 0);
        String tag        = getIntent().getStringExtra("tag");

        //bind views
        TextView tvName     = findViewById(R.id.tvDetailName);
        TextView tvPrice    = findViewById(R.id.tvDetailPrice);
        TextView tvDesc     = findViewById(R.id.tvDetailDesc);
        TextView tvCategory = findViewById(R.id.tvDetailCategory);
        ImageView imgDetail = findViewById(R.id.imgDetail);
        btnOrder         = findViewById(R.id.btnOrder);
        quantityStepper  = findViewById(R.id.quantityStepper);
        tvQtyError       = findViewById(R.id.tvQtyError);
        etQuantity       = findViewById(R.id.etQuantity);

        tvName.setText(name);
        tvPrice.setText(formatPrice(itemPrice));
        tvCategory.setText(tag != null ? tag : "");
        //prefer full description, fall back to short if not set
        tvDesc.setText(detailDesc != null && !detailDesc.isEmpty() ? detailDesc : shortDesc);
        if (imageRes != 0) imgDetail.setImageResource(imageRes);

        setupDropdowns();
        setupQuantityButtons();
        setupOrderButton();

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void setupDropdowns() {
        //expose dropdown menus for ice and sugar preference
        String[] iceLevels = {"Full Ice", "Less Ice", "Half Ice", "No Ice"};
        String[] sugarLevels = {"Full Sugar", "Less Sugar", "Half Sugar", "No Sugar"};

        AutoCompleteTextView actvIce   = findViewById(R.id.actvIceLevel);
        AutoCompleteTextView actvSugar = findViewById(R.id.actvSugarLevel);

        ArrayAdapter<String> iceAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, iceLevels);
        ArrayAdapter<String> sugarAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, sugarLevels);

        actvIce.setAdapter(iceAdapter);
        actvIce.setText(iceLevels[0], false);

        actvSugar.setAdapter(sugarAdapter);
        actvSugar.setText(sugarLevels[0], false);
    }

    private void setupQuantityButtons() {
        ImageButton btnMinus = findViewById(R.id.btnMinus);
        ImageButton btnPlus  = findViewById(R.id.btnPlus);

        btnMinus.setOnClickListener(v -> {
            //clamp at 0, clear field when it hits zero
            if (quantity > 0) {
                quantity--;
                etQuantity.setText(quantity > 0 ? String.valueOf(quantity) : "");
                updateOrderButton();
            }
        });

        btnPlus.setOnClickListener(v -> {
            quantity++;
            etQuantity.setText(String.valueOf(quantity));
            clearQtyError();
            updateOrderButton();
        });

        //keep quantity in sync if user types directly
        etQuantity.addTextChangedListener(new android.text.TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override public void afterTextChanged(android.text.Editable s) {
                String text = s.toString().trim();
                try {
                    quantity = text.isEmpty() ? 0 : Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    quantity = 0;
                }
                if (quantity > 0) clearQtyError();
                updateOrderButton();
            }
        });
    }

    private void updateOrderButton() {
        //show running total in button text
        if (quantity > 0) {
            double total = itemPrice * quantity;
            btnOrder.setText(getString(R.string.btn_order_with_price, formatPrice(total)));
        } else {
            btnOrder.setText(getString(R.string.btn_order));
        }
    }

    private void setupOrderButton() {
        //hover: change color on press, restore on release
        btnOrder.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                btnOrder.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.btn_pressed, null)));
            } else if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {
                btnOrder.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.primary, null)));
            }
            //return false so the click listener can still get the event
            return false;
        });

        btnOrder.setOnClickListener(v -> {
            if (quantity <= 0) {
                //highlight stepper field and show inline error
                quantityStepper.setBackgroundResource(R.drawable.bg_stepper_field_error);
                tvQtyError.setVisibility(View.VISIBLE);
                return;
            }

            clearQtyError();

            //success: notify then go back to item list
            new MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.dialog_order_title))
                    .setMessage(getString(R.string.dialog_order_msg))
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                        Intent intent = new Intent(this, ItemActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    })
                    .show();
        });
    }

    private void clearQtyError() {
        tvQtyError.setVisibility(View.GONE);
        quantityStepper.setBackgroundResource(R.drawable.bg_stepper_field);
    }

    private String formatPrice(double price) {
        return String.format(Locale.US, "Rp. %,.0f", price).replace(',', '.');
    }
}
