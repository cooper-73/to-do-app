package com.cooper73.todoapp.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.views.WelcomeView;

public class WelcomeActivity extends AppCompatActivity implements WelcomeView {
    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
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
        getStartedButton = findViewById(R.id.btn_welcome_get_started);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEvents() {
        getStartedButton.setOnClickListener(view -> goHomeActivity());
    }

    @Override
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)  actionBar.hide();
    }

    @Override
    public void goHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}