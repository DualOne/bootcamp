package com.nexign.bootcamp.tokenservice.controllers;

import com.nexign.bootcamp.tokenservice.controllers.dto.UserDTO;
import com.nexign.bootcamp.tokenservice.entities.Token;
import com.nexign.bootcamp.tokenservice.entities.User;
import com.nexign.bootcamp.tokenservice.services.UserTokensService;
import com.nexign.bootcamp.tokenservice.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private final UserTokensService tokensService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UserTokensService tokensService, UsersService usersService) {
        this.tokensService = tokensService;
        this.usersService = usersService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody UserDTO user) {
        return usersService.create(user.getName());
    }

    @PostMapping("/users/{username}/token")
    public Token generateToken(@PathVariable String username) {
        return tokensService.create(username);
    }

    @PostMapping("/users/{username}/token/validate")
    public boolean validateToken(@PathVariable String username,
                                 @RequestBody Token validatedToken) {
        return tokensService.validate(username, validatedToken);
    }
}
