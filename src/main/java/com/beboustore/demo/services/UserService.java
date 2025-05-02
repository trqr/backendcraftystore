package com.beboustore.demo.services;

import com.beboustore.demo.models.Customer;
import com.beboustore.demo.models.User;
import com.beboustore.demo.repositories.CustomerRepository;
import com.beboustore.demo.repositories.OrderRepository;
import com.beboustore.demo.repositories.UserRepository;
import com.beboustore.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private JwtUtil jwtUtil;

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

    public User GetUserInfo(String jwt){
        String userEmail = jwtUtil.extractUsername(jwt);
        return userRepository.findByUserEmail(userEmail);
    }

    public double getUserTotalPurchase(User user){
        final double[] totalPurchase = {0};
        String userEmail = user.getUserEmail();
        Customer customer = customerRepository.findByCustomerMail(userEmail);
        orderRepository.findByCustomer(customer).forEach(order -> totalPurchase[0] += order.getPrice());
        return totalPurchase[0];
    }
}
