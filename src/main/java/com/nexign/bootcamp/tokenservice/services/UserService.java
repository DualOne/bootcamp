package com.nexign.bootcamp.tokenservice.services;

import com.nexign.bootcamp.tokenservice.dao.UserDAO;
import com.nexign.bootcamp.tokenservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User create(String username) {
        User user = new User();
        user.setName(username);
        return userDAO.save(user);
    }

    public Optional<User> findById(Long userId) {
        return userDAO.findById(userId);
    }
}
