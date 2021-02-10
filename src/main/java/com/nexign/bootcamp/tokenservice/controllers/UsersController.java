package com.nexign.bootcamp.tokenservice.controllers;

import com.nexign.bootcamp.tokenservice.controllers.dto.RequestError;
import com.nexign.bootcamp.tokenservice.controllers.dto.TokenDTO;
import com.nexign.bootcamp.tokenservice.exceptions.InvalidTokenFormat;
import com.nexign.bootcamp.tokenservice.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserTokenService<?> userTokenService;

    @Autowired
    public UsersController(UserTokenService<?> userTokenService) {
        this.userTokenService = userTokenService;
    }

    @PostMapping("/{username}/token")
    public ResponseEntity<Object> generateToken(@PathVariable String username) {
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body(new RequestError("Username is empty"));
        }
        return ResponseEntity.ok(userTokenService.getToken(username));
    }

    @PostMapping("/{username}/token/validate")
    public ResponseEntity<Object> validateToken(@PathVariable String username,
                                                @RequestBody TokenDTO token) {
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body(new RequestError("Username is empty"));
        }
        if (token == null || token.getToken() == null || token.getToken().isBlank()) {
            return ResponseEntity.badRequest().body(new RequestError("Token is empty"));
        }

        try {
            return userTokenService.isTokenValid(username, token.getToken())
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.status(HttpStatus.CONFLICT).body(new RequestError("Token is invalid"));
        } catch (InvalidTokenFormat ex) {
            return ResponseEntity.unprocessableEntity().body(new RequestError("Invalid token format"));
        }
    }
}
