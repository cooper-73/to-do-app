package com.cooper73.todoapp.presentation.presenters;

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
    public void registerUser(UserViewModel userViewModel) {
        interactor.registerUser(userViewModel);
    }

    @Override
    public void successRegisterUser(User user) {

    }
}
