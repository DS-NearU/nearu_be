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

    public UserInfo() {

    }

    public UserInfo(String name, Boolean gender, String email, String phoneNumber, String emerPhoneNumber, String presentation, String condition, String similarExp, Boolean purpose) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.emerPhoneNumber = emerPhoneNumber;
        this.presentation = presentation;
        this.condition = condition;
        this.similarExp = similarExp;
        this.purpose = purpose;
    }
}
