package com.cooper73.todoapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.presentation.presenters.LoginPresenter;
import com.cooper73.todoapp.presentation.presenters.LoginPresenterImpl;
import com.cooper73.todoapp.ui.views.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText emailEditText;
    private Button loginButton;
    private LoginPresenter presenter;
    
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
        presenter = new LoginPresenterImpl(this);
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

    public void notifyEmptyEmail() {
        emailEditText.setText("");
        emailEditText.setError("The item cannot be empty.");
    }

    @Override
    public void onLoginButtonClick() {
        String email = emailEditText.getText().toString();
        presenter.loginUser(email);
    }

    @Override
    public void notifySuccessLogin(String userId) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void notifyErrorNoUserFound() {
        emailEditText.setError("Not found users with this email");
    }
}