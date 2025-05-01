package com.beboustore.demo.repositories;

import com.beboustore.demo.models.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserName(String userName);

    User findByUserEmail(String userEmail);
}

