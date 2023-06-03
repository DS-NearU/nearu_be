package com.nearu.nearu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer userNo;
    private UserType userType;
    private String userId;

    public User() {
    }
    public User(String userId) {
        this.userId = userId;
    }
    public User(UserType userType, String userId) {
        this.userType = userType;
        this.userId = userId;
    }


}