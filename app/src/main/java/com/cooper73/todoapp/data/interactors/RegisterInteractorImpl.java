package com.cooper73.todoapp.data.interactors;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.daos.UserDao;
import com.cooper73.todoapp.data.entities.User;
import com.cooper73.todoapp.ui.mappers.UserMapper;
import com.cooper73.todoapp.ui.viewmodels.UserViewModel;

public class RegisterInteractorImpl implements RegisterInteractor {
    private final RegisterInteractor.Callbacks callbacks;

    public RegisterInteractorImpl(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void registerUser(UserViewModel userViewModel) {
        UserDao userDao = MyApplication.getDatabase().userDao();
        MyApplication.getExecutorService().execute(() -> {
            try {
                User user = UserMapper.from(userViewModel);
                userDao.insertUser(user);
                callbacks.successRegisterUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
