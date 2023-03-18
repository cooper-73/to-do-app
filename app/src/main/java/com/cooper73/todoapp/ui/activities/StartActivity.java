package com.cooper73.todoapp.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.views.StartView;

public class StartActivity extends AppCompatActivity implements StartView {

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

    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEvents() {

    }

    @Override
    public void hideActionBar() {

    }

    @Override
    public void goLoginActivity() {

    }

    @Override
    public void goRegisterActivity() {

    }
}