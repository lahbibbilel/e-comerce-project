package com.example.ecomercebackend.service.ServiceImpl;


import com.example.ecomercebackend.service.UserService;
import com.example.ecomercebackend.user.User;
import com.example.ecomercebackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserImpl  implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> fetchUserList() {
        return userRepo.findAll();
    }

    @Override
    public User updateUser(User user, Integer userId) {
        User depDB
                = userRepo.findById(userId)
                .get();



        if (Objects.nonNull(user.getEmail())
                && !"".equalsIgnoreCase(
                user.getEmail())) {
            depDB.setEmail(
                    user.getEmail());
        }

        if (Objects.nonNull(user.getFirstname())
                && !"".equalsIgnoreCase(
                user.getFirstname())) {
            depDB.setFirstname(
                    user.getFirstname());
        }

        if (Objects.nonNull(
                user.getLastname())
                && !"".equalsIgnoreCase(
                user.getLastname())) {
            depDB.setLastname(
                    user.getLastname());
        }



        if (Objects.nonNull(user.getPassword())
                && !"".equalsIgnoreCase(
                user.getPassword())) {
            depDB.setPassword(
                    user.getPassword());
        }

        if (Objects.nonNull(user.getRole())
                && !"".equalsIgnoreCase(
                user.getRole().toString())) {
            depDB.setRole(
                    user.getRole());
        }



        return userRepo.save(depDB);
    }


    @Override
    public void deleteUserById(Integer userId) {
userRepo.deleteById(userId);
    }
}
