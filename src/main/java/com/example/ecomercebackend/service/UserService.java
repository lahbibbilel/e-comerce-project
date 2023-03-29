package com.example.ecomercebackend.service;

import com.example.ecomercebackend.user.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    // Read operation
    List<User> fetchUserList();

    // Update operation
    User updateUser(User user,
                                Integer userId);

    // Delete operation
    void deleteUserById(Integer userId);
}
