package com.cooper73.todoapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.views.StartView;

public class StartActivity extends AppCompatActivity implements StartView {
    private Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        hideActionBar();
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
        loginButton = findViewById(R.id.btn_start_login);
        registerButton = findViewById(R.id.btn_start_register);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEvents() {
        loginButton.setOnClickListener(v -> goLoginActivity());
        registerButton.setOnClickListener(v -> goRegisterActivity());
    }

    @Override
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void goRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}