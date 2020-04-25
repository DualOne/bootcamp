package com.nexign.bootcamp.token_service.controllers;

import com.nexign.bootcamp.token_service.controllers.dto.RequestError;
import com.nexign.bootcamp.token_service.controllers.dto.TokenDTO;
import com.nexign.bootcamp.token_service.exceptions.InvalidTokenFormat;
import com.nexign.bootcamp.token_service.services.UserTokenService;
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

    @PostMapping("/{username}/token/generate")
    public ResponseEntity<?> generateToken(@PathVariable String username) {
        if (username == null || username.isBlank()) {
            return new ResponseEntity<>(new RequestError("Username is empty"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userTokenService.getToken(username));
    }

    @PostMapping("/{username}/token/validate")
    public ResponseEntity<?> validateToken(@PathVariable String username,
                                           @RequestBody TokenDTO token) {
        if (username == null || username.isBlank()) {
            return new ResponseEntity<>(new RequestError("Username is empty"), HttpStatus.BAD_REQUEST);
        }
        if (token == null || token.getToken() == null || token.getToken().isBlank()) {
            return new ResponseEntity<>(new RequestError("Token is empty"), HttpStatus.BAD_REQUEST);
        }

        try {
            return userTokenService.isTokenValid(username, token.getToken())
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(new RequestError("Token is invalid"), HttpStatus.CONFLICT);
        } catch (InvalidTokenFormat ex) {
            return new ResponseEntity<>(new RequestError("Invalid token format"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
