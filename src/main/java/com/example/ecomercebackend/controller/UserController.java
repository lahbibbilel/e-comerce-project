package com.example.ecomercebackend.controller;


import com.example.ecomercebackend.service.UserService;
import com.example.ecomercebackend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    // Save operation
    @PostMapping("/users")

    public User saveUser(
            @Valid @RequestBody User user)
    {
        return userService.saveUser(user);
    }

    // Read operation
    @GetMapping("/users")

    public List<User> fetchUserList()
    {
        return userService.fetchUserList();
    }

    // Update operation
    @PutMapping("/users/{id}")

    public User
    updateUser(@RequestBody User user,
                     @PathVariable("id") Integer userId)
    {
        return userService.updateUser(
                user, userId);
    }

    // Delete operation
    @DeleteMapping("/users/{id}")

    public String deleteuserById(@PathVariable("id")
                                       Integer UserId)
    {
        userService.deleteUserById(
                UserId);
        return "Deleted Successfully";
    }
}