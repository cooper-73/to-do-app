package com.cooper73.todoapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cooper73.todoapp.R;
import com.cooper73.todoapp.presentation.presenters.RegisterPresenter;
import com.cooper73.todoapp.presentation.presenters.RegisterPresenterImpl;
import com.cooper73.todoapp.ui.views.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private EditText firstNameEditText, lastNameEditText, emailEditText;
    private Button registerButton;
    private RegisterPresenter presenter;
    
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
        presenter = new RegisterPresenterImpl(this);
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
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();

        presenter.registerUser(firstName, lastName, email);
    }

    @Override
    public void notifyEmptyFirstName() {
        firstNameEditText.setText("");
        firstNameEditText.setError("The item cannot be empty.");
    }

    @Override
    public void notifyEmptyLastName() {
        lastNameEditText.setText("");
        lastNameEditText.setError("The item cannot be empty.");
    }

    @Override
    public void notifyEmptyEmail() {
        emailEditText.setText("");
        emailEditText.setError("The item cannot be empty.");
    }

    @Override
    public void notifyEmailAlreadyUsed() {
        emailEditText.setError("This email is already used.");
    }

    @Override
    public void notifySuccessRegister() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}