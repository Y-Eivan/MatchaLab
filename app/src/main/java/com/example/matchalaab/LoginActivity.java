package com.example.matchalaab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Html;
import android.view.WindowInsetsController;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //white status bar with dark icons
        getWindow().setStatusBarColor(android.graphics.Color.WHITE);
        WindowInsetsController controller = getWindow().getInsetsController();
        if (controller != null) {
            controller.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }

        //bind views
        tilUsername   = findViewById(R.id.tilUsername);
        tilPassword   = findViewById(R.id.tilPassword);
        etUsername    = findViewById(R.id.etUsername);
        etPassword    = findViewById(R.id.etPassword);
        btnSignIn     = findViewById(R.id.btnSignIn);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);

        //link to register page with colored span
        if (tvRegisterLink != null) {
            String registerText = "New to MatchaLab? <font color='#506051'><b>Create an account</b></font>";
            tvRegisterLink.setText(Html.fromHtml(registerText, Html.FROM_HTML_MODE_LEGACY));
            tvRegisterLink.setOnClickListener(v -> {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            });
        }

        if (btnSignIn != null) {
            //hover: change color on press, restore on release
            btnSignIn.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnSignIn.setBackgroundTintList(ColorStateList.valueOf(
                            getResources().getColor(R.color.btn_pressed, null)));
                } else if (event.getAction() == MotionEvent.ACTION_UP
                        || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    btnSignIn.setBackgroundTintList(ColorStateList.valueOf(
                            getResources().getColor(R.color.primary, null)));
                }
                return false;
            });
            btnSignIn.setOnClickListener(v -> validateAndLogin());
        }
    }

    private void validateAndLogin() {
        String user = etUsername.getText().toString().trim();
        String pass = etPassword.getText().toString();
        boolean valid = true;

        //username checks
        if (user.isEmpty()) {
            tilUsername.setError(getString(R.string.err_username_empty));
            valid = false;
        } else if (user.length() <= 6) {
            tilUsername.setError(getString(R.string.err_username_length));
            valid = false;
        } else {
            tilUsername.setError(null);
        }

        //password checks
        if (pass.isEmpty()) {
            tilPassword.setError(getString(R.string.err_password_empty));
            valid = false;
        } else {
            tilPassword.setError(null);
        }

        if (!valid) return;

        getSharedPreferences("chi_matcha", MODE_PRIVATE)
                .edit()
                .putString("username", user)
                .apply();

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}