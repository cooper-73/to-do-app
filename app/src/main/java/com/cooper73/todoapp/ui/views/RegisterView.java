package com.cooper73.todoapp.ui.views;

public interface RegisterView extends BaseView {
    void setActionBar();
    void onRegisterButtonClick();
    void notifyEmptyFirstName();
    void notifyEmptyLastName();
    void notifyEmptyEmail();
    void notifyEmailAlreadyUsed();
    void notifySuccessRegister(String userId);
}
