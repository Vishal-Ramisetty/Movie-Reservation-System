package com.vrshowbiz.moviebuff.dto.request;

import com.vrshowbiz.moviebuff.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    // Try using OTP service for passwordless authentication
    private String password;

    public User toUser() {
        return User.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .role("Public")
                .build();
    }
}
