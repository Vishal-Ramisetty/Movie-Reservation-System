package com.vrshowbiz.moviebuff.dto.response;

import com.vrshowbiz.moviebuff.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    // TO DO: Send user details according to user role

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getUserId().toString())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
