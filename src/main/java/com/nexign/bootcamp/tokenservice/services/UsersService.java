package com.nexign.bootcamp.tokenservice.services;

import com.nexign.bootcamp.tokenservice.dao.UsersRepository;
import com.nexign.bootcamp.tokenservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User create(String username) {
        final User user = new User();
        user.setName(username);

        return usersRepository.save(user);
    }
}
