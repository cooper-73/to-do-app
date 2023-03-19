package com.cooper73.todoapp.presentation.presenters;

import com.cooper73.todoapp.MyApplication;
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
        MyApplication.getMainThreadHandler().post(() -> view.notifySuccessLogin());
    }

    @Override
    public void errorNoExistsUser() {
        MyApplication.getMainThreadHandler().post(() -> view.notifyErrorNoUserFound());
    }
}
