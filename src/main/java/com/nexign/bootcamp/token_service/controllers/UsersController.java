package com.nexign.bootcamp.token_service.controllers;

import com.nexign.bootcamp.token_service.controllers.dto.RequestError;
import com.nexign.bootcamp.token_service.controllers.dto.TokenDTO;
import com.nexign.bootcamp.token_service.controllers.dto.UserDTO;
import com.nexign.bootcamp.token_service.exceptions.InvalidTokenFormat;
import com.nexign.bootcamp.token_service.services.UserService;
import com.nexign.bootcamp.token_service.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final UserTokenService<?> userTokenService;

    @Autowired
    public UsersController(UserService userService, UserTokenService<?> userTokenService) {
        this.userService = userService;
        this.userTokenService = userTokenService;
    }

    @PutMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        if (user == null) {
            return new ResponseEntity<>(new RequestError("User info is not provided"), HttpStatus.BAD_REQUEST);
        }
        if (user.getUsername() == null) {
            return new ResponseEntity<>(new RequestError("Username is empty"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.create(user.getUsername()), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable Long userId) {
        final var result = userService.findById(userId);

        return result.isPresent()
                ? ResponseEntity.ok(result)
                : new ResponseEntity<>(
                new RequestError(String.format("User with id %s was not found", userId)), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userId}/token/generate")
    public ResponseEntity<?> generateToken(@PathVariable Long userId) {
        return ResponseEntity.ok(userTokenService.getToken(userId));
    }

    @PostMapping("/{userId}/token/validate")
    public ResponseEntity<?> validateToken(@PathVariable Long userId,
                                           @RequestBody TokenDTO token) {
        if (token == null || token.getToken() == null || token.getToken().isBlank()) {
            return new ResponseEntity<>(new RequestError("Token is empty"), HttpStatus.BAD_REQUEST);
        }

        try {
            return userTokenService.isTokenValid(userId, token.getToken())
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(new RequestError("Token is invalid"), HttpStatus.CONFLICT);
        } catch (InvalidTokenFormat ex) {
            return new ResponseEntity<>(new RequestError("Invalid token format"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
