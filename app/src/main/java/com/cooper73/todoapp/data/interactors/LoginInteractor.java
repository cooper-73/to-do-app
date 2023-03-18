package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.data.entities.User;

public interface LoginInteractor {
    void loginUser(String email);

    interface Callbacks {
        void successLoginUser(User user);
        void errorNoExistsUser();
    }
}
