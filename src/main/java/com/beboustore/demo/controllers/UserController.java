package com.beboustore.demo.controllers;

import com.beboustore.demo.models.User;
import com.beboustore.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/submit-user-data")
    public ResponseEntity<String> submitUserData(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("utilisateur sauvegardé");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestBody Map<String, String> body) {
        String userName = body.get("userName");
        String userPassword = body.get("userPassword");

        if (userService.loginCheck(userName, userPassword)){
            return ResponseEntity.ok("authentification réussie");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentification échouée");
    }
}
