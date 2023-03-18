package com.cooper73.todoapp.ui.mappers;

import com.cooper73.todoapp.data.entities.User;
import com.cooper73.todoapp.ui.viewmodels.UserViewModel;

public class UserMapper {
    public static UserViewModel from(User user) {
        UserViewModel userViewModel = new UserViewModel();

        userViewModel.setId(user.getId());
        userViewModel.setFirstName(user.getFirstName());
        userViewModel.setLastName(user.getLastName());
        userViewModel.setEmail(user.getEmail());

        return userViewModel;
    }

    public static User from(UserViewModel userViewModel) {

        return new User(
                userViewModel.getFirstName(),
                userViewModel.getLastName(),
                userViewModel.getEmail()
        );
    }
}
