package com.cooper73.todoapp.data.interactors;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.cooper73.todoapp.MyApplication;
import com.cooper73.todoapp.data.daos.UserDao;
import com.cooper73.todoapp.data.entities.User;
import com.cooper73.todoapp.ui.mappers.UserMapper;
import com.cooper73.todoapp.ui.viewmodels.UserViewModel;

import java.util.Objects;

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
            } catch (SQLiteConstraintException sqLiteConstraintException) {
                final String UNIQUE_EMAIL_EXCEPTION_MESSAGE = "UNIQUE constraint failed: users.email (code 2067 SQLITE_CONSTRAINT_UNIQUE)";
                switch (Objects.requireNonNull(sqLiteConstraintException.getMessage())) {
                    case UNIQUE_EMAIL_EXCEPTION_MESSAGE:
                        callbacks.errorEmailAlreadyUsed();
                        break;
                    default:
                        sqLiteConstraintException.printStackTrace();
                }
            } catch (Exception e) {
                Log.i("EXCEPTION", e.getMessage());
                Log.i("EXCEPTION", e.getLocalizedMessage());
                e.printStackTrace();
            }
        });
    }
}
