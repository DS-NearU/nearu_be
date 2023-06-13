package com.nearu.nearu.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.nearu.nearu.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {
    private String userId;
    private String password;
    private String type;
    private String name;
    private Boolean gender;
    private String email;
    private String phoneNum;
    private String emergencyNum;
    private String presentation;
    private String condition;
    private String experience;
    private Boolean purpose;
    private Boolean emailNotification;
    private Boolean msgNotification;
    private Boolean kakaoNotification;
}
