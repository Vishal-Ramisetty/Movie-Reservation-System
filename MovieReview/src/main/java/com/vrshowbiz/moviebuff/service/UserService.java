package com.vrshowbiz.moviebuff.service;

import com.vrshowbiz.moviebuff.model.User;
import com.vrshowbiz.moviebuff.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> findUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public User updateUserRole(String role, String userId){
        User user=userRepository.findById(UUID.fromString(userId)).orElseThrow(()->new RuntimeException("User not found"));
        user.setRole(role);
        return userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }


}
