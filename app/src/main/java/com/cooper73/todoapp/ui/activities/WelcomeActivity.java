package com.cooper73.todoapp.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cooper73.todoapp.PreferencesHelper;
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
        getStartedButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = new PreferencesHelper(this).getEditor();
            editor.putBoolean(getString(R.string.shared_preferences_on_boarding), true);
            editor.apply();
            goNextActivity();
        });
    }

    @Override
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)  actionBar.hide();
    }

    @Override
    public void goNextActivity() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }
}