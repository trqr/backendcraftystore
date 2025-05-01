package com.beboustore.demo.services;

import com.beboustore.demo.models.User;
import com.beboustore.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private UserRepository userRepository;

    public boolean isUserAlreadyRegistered(User user){
        return userRepository.findByUserEmail(user.getUserEmail()) != null;
    }

    public void registerUser(User user){
        String encryptedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        userRepository.save(user);
    }

    public boolean loginCheck(String userEmail, String password){

        User loginUser = userRepository.findByUserEmail(userEmail);
        return passwordEncoder.matches(password, loginUser.getUserPassword());
    }
}
