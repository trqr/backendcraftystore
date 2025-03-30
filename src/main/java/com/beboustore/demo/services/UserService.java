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

    public void registerUser(User user){
        String encryptedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        userRepository.save(user);
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


    public boolean loginCheck(String user, String password){

        User loginUser = userRepository.findByUserName(user);
        return passwordEncoder.matches(password, loginUser.getUserPassword());
    }

}
