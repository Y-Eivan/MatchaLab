package com.example.matchalaab;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    // Fields — declared at class level so both onCreate and validateAndRegister can use them
    private TextInputLayout tilUsername, tilPassword, tilConfirm;
    private TextInputEditText etUsername, etPassword, etConfirm;
    private MaterialButton btnCreate;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Bind every view from the layout
        tilUsername = findViewById(R.id.tilUsername);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirm  = findViewById(R.id.tilConfirm);
        etUsername  = findViewById(R.id.etUsername);
        etPassword  = findViewById(R.id.etPassword);
        etConfirm   = findViewById(R.id.etConfirm);
        btnCreate   = findViewById(R.id.btnCreate);
        tvLoginLink = findViewById(R.id.tvLoginLink);

        // Sign in link — SpannableString colors "Sign in" with @color/primary
        SpannableString ss = new SpannableString("Already have an account? Sign in");
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.primary, null)),
                25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // indices for "Sign in"
        tvLoginLink.setText(ss);
        tvLoginLink.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();  // don't stack Register on top of Login
        });

        // MouseDown interaction: validate on ACTION_DOWN itself (not ACTION_UP)
        btnCreate.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                btnCreate.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.btn_pressed, null))); // #2F3A30
                validateAndRegister();
            } else if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {
                btnCreate.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.primary, null)));
            }
            return false;
        });
    }

    private void validateAndRegister() {
        String user    = etUsername.getText().toString().trim();
        String pass    = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();
        boolean valid  = true;

        // Username
        if (user.isEmpty()) {
            tilUsername.setError(getString(R.string.err_username_empty));
            valid = false;
        } else if (user.length() <= 6) {
            tilUsername.setError(getString(R.string.err_username_length));
            valid = false;
        } else {
            tilUsername.setError(null);
        }

        // Password
        if (pass.isEmpty()) {
            tilPassword.setError(getString(R.string.err_password_empty));
            valid = false;
        } else {
            tilPassword.setError(null);
        }

        // Confirm password
        if (confirm.isEmpty()) {
            tilConfirm.setError(getString(R.string.err_confirm_empty));
            valid = false;
        } else if (!pass.equals(confirm)) {
            tilConfirm.setError(getString(R.string.err_password_mismatch));
            valid = false;
        } else {
            tilConfirm.setError(null);
        }

        if (valid) {
            getSharedPreferences("chi_matcha", MODE_PRIVATE)
                    .edit().putString("username", user).apply();
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}