package com.cooper73.todoapp.ui.views;

public interface LoginView extends BaseView {
    void setActionBar();
    void notifyEmptyEmail();
    void onLoginButtonClick();
    void notifySuccessLogin();
    void notifyErrorNoUserFound();
}
