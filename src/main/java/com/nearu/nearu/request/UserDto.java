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
    String userId;
    String password;
    String type;
    String name;
    Boolean gender;
    String email;
    String phoneNum;
    String emergencyNum;
    String presentation;
    String condition;
    String experience;
    Boolean purpose;
    Boolean emailNotification;
    Boolean msgNotification;
    Boolean kakaoNotification;
}
