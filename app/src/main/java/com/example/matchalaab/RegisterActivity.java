package com.example.matchalaab;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.WindowInsetsController;
import android.text.Spanned;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    //fields declared at class level so validateAndRegister can access them too
    private TextInputLayout tilUsername, tilPassword, tilConfirm;
    private TextInputEditText etUsername, etPassword, etConfirm;
    private MaterialButton btnCreate;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //white status bar with dark icons
        getWindow().setStatusBarColor(android.graphics.Color.WHITE);
        WindowInsetsController controller = getWindow().getInsetsController();
        if (controller != null) {
            controller.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        }

        //bind views
        tilUsername = findViewById(R.id.tilUsername);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirm  = findViewById(R.id.tilConfirm);
        etUsername  = findViewById(R.id.etUsername);
        etPassword  = findViewById(R.id.etPassword);
        etConfirm   = findViewById(R.id.etConfirm);
        btnCreate   = findViewById(R.id.btnCreate);
        tvLoginLink = findViewById(R.id.tvLoginLink);

        //link back to login with colored "sign in" span
        SpannableString ss = new SpannableString("Already have an account? Sign in");
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.primary, null)),
                25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(Typeface.BOLD),
                25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLoginLink.setText(ss);
        tvLoginLink.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        //mousedown: trigger color change + validate on press not release
        btnCreate.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                btnCreate.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.btn_pressed, null)));
                validateAndRegister();
            } else if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {
                btnCreate.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.primary, null)));
            }
            return true;
        });
    }

    private void validateAndRegister() {
        String user    = etUsername.getText().toString().trim();
        String pass    = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();
        boolean valid  = true;

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

        //confirm password checks
        if (confirm.isEmpty()) {
            tilConfirm.setError(getString(R.string.err_confirm_empty));
            valid = false;
        } else if (!pass.equals(confirm)) {
            tilConfirm.setError(getString(R.string.err_password_mismatch));
            valid = false;
        } else {
            tilConfirm.setError(null);
        }

        //save username and go home if all clear
        if (valid) {
            getSharedPreferences("chi_matcha", MODE_PRIVATE)
                    .edit().putString("username", user).apply();
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}