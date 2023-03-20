package com.cooper73.todoapp.presentation.presenters;

import android.content.Context;
import android.content.SharedPreferences;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.PreferencesHelper;
import com.cooper73.todoapp.R;
import com.cooper73.todoapp.data.entities.User;
import com.cooper73.todoapp.data.interactors.LoginInteractor;
import com.cooper73.todoapp.data.interactors.LoginInteractorImpl;
import com.cooper73.todoapp.ui.views.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.Callbacks {
    private final LoginView view;
    private final LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void loginUser(String email) {
        email = email.trim();
        if (email.isEmpty()) {
            view.notifyEmptyEmail();
            return;
        }

        interactor.loginUser(email);
    }

    @Override
    public void successLoginUser(User user) {
        SharedPreferences.Editor editor = new PreferencesHelper((Context) view).getEditor();
        editor.putString(((Context) view).getString(R.string.shared_preferences_user_id), user.getId());
        editor.apply();
        MyApplication.getMainThreadHandler().post(() -> view.notifySuccessLogin());
    }

    @Override
    public void errorNoExistsUser() {
        MyApplication.getMainThreadHandler().post(() -> view.notifyErrorNoUserFound());
    }
}
