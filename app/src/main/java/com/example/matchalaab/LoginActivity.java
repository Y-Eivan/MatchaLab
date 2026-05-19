package com.example.matchalaab;

import android.annotation.SuppressLint;
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

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilUsername, tilPassword;
    private TextInputEditText etUsername, etPassword;
    private MaterialButton btnSignIn;
    private TextView tvRegisterLink;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilUsername   = findViewById(R.id.tilUsername);
        tilPassword   = findViewById(R.id.tilPassword);
        etUsername    = findViewById(R.id.etUsername);
        etPassword    = findViewById(R.id.etPassword);
        btnSignIn     = findViewById(R.id.btnSignIn);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);

        // Register link — SpannableString to color "Create an account"
        SpannableString ss = new SpannableString("New to Chi Matcha? Create an account");
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.primary, null)),
                19, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegisterLink.setText(ss);
        tvRegisterLink.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));



        // Sign In button — hover via touch listener (ACTION_DOWN = darker, ACTION_UP = restore)
        btnSignIn.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                btnSignIn.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.btn_hover, null)));
            } else if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {
                btnSignIn.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.primary, null)));
                // Run validation on ACTION_UP
                validateAndLogin();
            }
            return false;
        });
    }

    private void validateAndLogin() {
        String user = etUsername.getText().toString().trim();
        String pass = etPassword.getText().toString();
        boolean valid = true;

        if (user.isEmpty()) {
            tilUsername.setError(getString(R.string.err_username_empty));
            valid = false;
        } else if (user.length() <= 6) {
            tilUsername.setError(getString(R.string.err_username_length));
            valid = false;
        } else {
            tilUsername.setError(null);
        }

        if (pass.isEmpty()) {
            tilPassword.setError(getString(R.string.err_password_empty));
            valid = false;
        } else {
            tilPassword.setError(null);
        }

        if (valid) {
            getSharedPreferences("chi_matcha", MODE_PRIVATE)
                    .edit().putString("username", user).apply();
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}