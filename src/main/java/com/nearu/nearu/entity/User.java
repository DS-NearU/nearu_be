package com.nearu.nearu.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.nearu.nearu.UserType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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