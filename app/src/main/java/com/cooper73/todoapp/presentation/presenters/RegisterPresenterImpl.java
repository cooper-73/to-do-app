package com.cooper73.todoapp.presentation.presenters;

import android.content.Context;
import android.content.SharedPreferences;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.PreferencesHelper;
import com.cooper73.todoapp.R;
import com.cooper73.todoapp.data.entities.User;
import com.cooper73.todoapp.data.interactors.RegisterInteractor;
import com.cooper73.todoapp.data.interactors.RegisterInteractorImpl;
import com.cooper73.todoapp.ui.viewmodels.UserViewModel;
import com.cooper73.todoapp.ui.views.RegisterView;

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.Callbacks {
    private final RegisterView view;
    private final RegisterInteractor interactor;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        interactor = new RegisterInteractorImpl(this);
    }

    @Override
    public void registerUser(String firstName, String lastName, String email) {
        firstName = firstName.trim();
        lastName = lastName.trim();
        email = email.trim();

        if (firstName.isEmpty()) {
            view.notifyEmptyFirstName();
            return;
        }
        if (lastName.isEmpty()) {
            view.notifyEmptyLastName();
            return;
        }
        if (email.isEmpty()) {
            view.notifyEmptyEmail();
            return;
        }

        UserViewModel userViewModel = new UserViewModel(firstName, lastName, email);
        interactor.registerUser(userViewModel);
    }

    @Override
    public void successRegisterUser(User user) {
        SharedPreferences.Editor editor = new PreferencesHelper((Context) view).getEditor();
        editor.putString(((Context) view).getString(R.string.shared_preferences_user_id), user.getId());
        editor.apply();
        MyApplication.getMainThreadHandler().post(() -> view.notifySuccessRegister(user.getId()));
    }

    @Override
    public void errorEmailAlreadyUsed() {
        MyApplication.getMainThreadHandler().post(() -> view.notifyEmailAlreadyUsed());
    }
}
