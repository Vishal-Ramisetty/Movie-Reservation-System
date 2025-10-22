package com.vrshowbiz.moviebuff.controller;

import com.vrshowbiz.moviebuff.dto.request.UserRequest;
import com.vrshowbiz.moviebuff.dto.response.UserResponse;
import com.vrshowbiz.moviebuff.model.User;
import com.vrshowbiz.moviebuff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private UserService userService;
    // Create User
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user= userService.registerUser(userRequest);
        return ResponseEntity.ok().body((new UserResponse()).toUserResponse(user));
    }
    // Update User Previleges ( Roles- Admin, General, Moderator)

    // Delete User - Admin Only
}
