package com.nexign.bootcamp.token_service.controllers;

import com.nexign.bootcamp.token_service.controllers.dto.ResponseError;
import com.nexign.bootcamp.token_service.controllers.dto.UserDTO;
import com.nexign.bootcamp.token_service.entities.Token;
import com.nexign.bootcamp.token_service.services.UserService;
import com.nexign.bootcamp.token_service.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yaroslav.Zakharenko
 * @since 30.04.2020 18:43
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserTokenService userTokenService;
    private final UserService userService;

    @Autowired
    public UserController(UserTokenService userTokenService,
                          UserService userService) {
        this.userTokenService = userTokenService;
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        if (user == null || user.getUsername() == null || user.getUsername().isBlank()) {
            return new ResponseEntity<>("Username is empty", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.create(user.getUsername()), HttpStatus.CREATED);
    }

    @PostMapping("/{username}/token/generate")
    public ResponseEntity<?> generateToken(@PathVariable String username) {
        if (username.isBlank()) {
            return new ResponseEntity<>(new ResponseError("Username is empty"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(userTokenService.getToken(username));
    }

    @PostMapping("/{username}/token/validate")
    public ResponseEntity<?> validateToken(@PathVariable String username,
                                           @RequestBody Token token) {
        if (username.isBlank()) {
            return new ResponseEntity<>(new ResponseError("Username is empty"), HttpStatus.BAD_REQUEST);
        }
        if (token == null || token.getToken() == null || token.getToken().isBlank()) {
            return new ResponseEntity<>(new ResponseError("User token is empty"), HttpStatus.BAD_REQUEST);
        }

        return userTokenService.isTokenValid(username, token)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(new ResponseError("Token is invalid"), HttpStatus.CONFLICT);
    }
}
