package com.example.matchalaab;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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

        // Set status bar
        getWindow().setStatusBarColor(android.graphics.Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        tilUsername   = findViewById(R.id.tilUsername);
        tilPassword   = findViewById(R.id.tilPassword);
        etUsername    = findViewById(R.id.etUsername);
        etPassword    = findViewById(R.id.etPassword);
        btnSignIn     = findViewById(R.id.btnSignIn);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);

        if (tvRegisterLink != null) {
            String registerText = "New to MatchaLab? <font color='#506051'><b>Create an account</b></font>";
            tvRegisterLink.setText(Html.fromHtml(registerText));
            tvRegisterLink.setOnClickListener(v -> {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            });
        }

        if (btnSignIn != null) {
            btnSignIn.setOnClickListener(v -> validateAndLogin());
        }
    }

    private void validateAndLogin() {
        String user = etUsername.getText().toString().trim();
        String pass = etPassword.getText().toString();

        if (user.isEmpty()) {
            tilUsername.setError("Required");
            return;
        }
        if (pass.isEmpty()) {
            tilPassword.setError("Required");
            return;
        }

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}