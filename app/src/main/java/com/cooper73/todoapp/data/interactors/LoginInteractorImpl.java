package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.daos.UserDao;
import com.cooper73.todoapp.data.entities.User;

public class LoginInteractorImpl implements LoginInteractor {
    LoginInteractor.Callbacks callbacks;

    public LoginInteractorImpl(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void loginUser(String email) {
        UserDao userDao = MyApplication.getDatabase().userDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                User user = userDao.getUserByEmail(email);
                callbacks.successLoginUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
