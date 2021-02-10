package com.nexign.bootcamp.tokenservice.controllers;

import com.nexign.bootcamp.tokenservice.controllers.dto.RequestError;
import com.nexign.bootcamp.tokenservice.controllers.dto.TokenDTO;
import com.nexign.bootcamp.tokenservice.controllers.dto.UserDTO;
import com.nexign.bootcamp.tokenservice.entities.Token;
import com.nexign.bootcamp.tokenservice.entities.User;
import com.nexign.bootcamp.tokenservice.exceptions.InvalidTokenFormat;
import com.nexign.bootcamp.tokenservice.services.UserService;
import com.nexign.bootcamp.tokenservice.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final UserTokenService<? extends Token<?>> userTokenService;

    @Autowired
    public UsersController(UserService userService,
                           UserTokenService<? extends Token<?>> userTokenService) {
        this.userService = userService;
        this.userTokenService = userTokenService;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserDTO user) {
        if (user == null) {
            return ResponseEntity.badRequest().body(new RequestError("User info is not provided"));
        }
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            return ResponseEntity.badRequest().body(new RequestError("Username is empty"));
        }

        User result = userService.create(user.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable Long userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/token")
    public ResponseEntity<TokenDTO> generateToken(@PathVariable Long userId) {
        return ResponseEntity.of(userTokenService.getToken(userId).map(TokenDTO::of));
    }

    @PostMapping("/{userId}/token/validate")
    public ResponseEntity<Object> validateToken(@PathVariable Long userId,
                                                @RequestBody TokenDTO token) {
        if (token == null) {
            return ResponseEntity.badRequest().body(new RequestError("Token info is not provided"));
        }
        if (token.getToken() == null || token.getToken().isBlank()) {
            return ResponseEntity.badRequest().body(new RequestError("Token is empty"));
        }

        try {
            return userTokenService.isTokenValid(userId, token.getToken())
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.status(HttpStatus.CONFLICT).body(new RequestError("Token is invalid"));
        } catch (InvalidTokenFormat ex) {
            return ResponseEntity.unprocessableEntity().body(new RequestError("Invalid token format"));
        }
    }
}
