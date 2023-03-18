package com.cooper73.todoapp.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.ui.views.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private EditText firstNameEditText, lastNameEditText, emailEditText;
    private Button registerButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        firstNameEditText = findViewById(R.id.et_register_first_name);
        lastNameEditText = findViewById(R.id.et_register_last_name);
        emailEditText = findViewById(R.id.et_register_email);
        registerButton = findViewById(R.id.btn_register_register);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEvents() {
        registerButton.setOnClickListener(v -> onRegisterButtonClick());
    }

    @Override
    public void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
    }

    @Override
    public void onRegisterButtonClick() {
        Toast.makeText(this, "Register...", Toast.LENGTH_SHORT).show();
    }
}