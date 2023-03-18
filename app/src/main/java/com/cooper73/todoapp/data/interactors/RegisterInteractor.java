package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.data.entities.User;
import com.cooper73.todoapp.ui.viewmodels.UserViewModel;

public interface RegisterInteractor {
    void registerUser(UserViewModel userViewModel);

    interface Callbacks {
        void successRegisterUser(User user);
    }
}
