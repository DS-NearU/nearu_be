package com.nearu.nearu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPw {
    private Integer userNo;
    private String password;

    public UserPw() {

    }

    public UserPw(String password) {
        this.password = password;
    }
}
