package com.smart.smartcontact.Services;

import com.smart.smartcontact.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    User saveUser(User user);

    User getUserByEmail(String email);

    Optional<User> getUserById(String userId);
    List<User> getAllUsers();
    void deleteUser(String userid);
    boolean isUserExist(String userid);
    boolean isEmailExist(String email);
    Optional<User> updateUser(User user);


}
