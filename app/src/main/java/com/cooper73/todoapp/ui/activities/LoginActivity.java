package com.cooper73.todoapp.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.views.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText emailEditText;
    private Button loginButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setActionBar();
        initPresenter();
        bindUI();
        initUI();
        initEvents();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void bindUI() {
        emailEditText = findViewById(R.id.et_login_email);
        loginButton = findViewById(R.id.btn_login_login);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEvents() {
        loginButton.setOnClickListener(v -> onLoginButtonClick());
    }

    @Override
    public void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
    }

    @Override
    public void onLoginButtonClick() {
        Toast.makeText(this, "Login...", Toast.LENGTH_SHORT).show();
    }
}