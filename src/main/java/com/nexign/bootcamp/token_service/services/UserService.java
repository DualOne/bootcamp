package com.nexign.bootcamp.token_service.services;

import com.nexign.bootcamp.token_service.dao.UserDAO;
import com.nexign.bootcamp.token_service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yaroslav.Zakharenko
 * @since 30.04.2020 19:17
 */
@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User create(String username) {
        final var user = new User();
        user.setName(username);
        userDAO.save(user);

        return user;
    }
}
