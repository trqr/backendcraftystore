package com.beboustore.demo.controllers;

import com.beboustore.demo.models.Customer;
import com.beboustore.demo.models.User;
import com.beboustore.demo.repositories.CustomerRepository;
import com.beboustore.demo.repositories.OrderRepository;
import com.beboustore.demo.repositories.UserRepository;
import com.beboustore.demo.services.UserService;
import com.beboustore.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("/submit-user-data")
    public String submitUserData(@RequestBody User user) {
        if (userService.isUserAlreadyRegistered(user)) {
            return "Email déja utilisé";
        } else {
            user.setUserFidelityPoints((userService.getUserTotalPurchase(user)*10));
            userService.registerUser(user);
            return "Utilisateur sauvegardé";
        }
    }

    @PostMapping("/login")
    public String loginCheck(@RequestBody Map<String, String> body) {
        String userEmail = body.get("userEmail");
        String userPassword = body.get("userPassword");

        if (userService.loginCheck(userEmail, userPassword)){
            return jwtUtil.generateToken(userEmail);
        }
        return "Authentification échouée";
    }

    @GetMapping("/userorders")
    public ResponseEntity<?> getUserOrders(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            System.out.println("Token : " + jwt);
        }
        String jwt = authHeader.substring(7);
        String userEmail = jwtUtil.extractUsername(jwt);
        Customer customer = customerRepository.findByCustomerMail(userEmail);
        return ResponseEntity.ok(orderRepository.findByCustomer(customer));
    }

    @GetMapping("/get-user-info")
    public User getUserInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            return userService.GetUserInfo(jwt);
        }
        else {
            return null;
        }

    }
}
