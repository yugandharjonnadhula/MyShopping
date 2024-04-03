package com.myshopping.My.Shopping.service;

import com.myshopping.My.Shopping.models.AppUser;
import com.myshopping.My.Shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void registerUser(AppUser user) {
        userRepository.save(user);
    }
    public AppUser getUserById(UUID id){
        AppUser user=userRepository.findById(id).orElse(null);
        return user;
    }
}
