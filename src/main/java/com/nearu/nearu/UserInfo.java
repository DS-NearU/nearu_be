package com.nearu.nearu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private Integer userNo;
    private String name;
    private Boolean gender;
    private String email;
    private String phoneNumber;
    private String emerPhoneNumber;
    private String presentation;
    private String condition;
    private String similarExp;
    private Boolean purpose;
    private Double rating;
}
