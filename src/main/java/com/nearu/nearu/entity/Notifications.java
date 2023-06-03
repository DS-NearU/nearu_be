package com.nearu.nearu.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notifications {
    private Integer userNo;
    private Boolean emailNotif;
    private Boolean msgNotif;
    private Boolean kakaoNotif;

    public Notifications() {
    }

    public Notifications(Boolean emailNotif, Boolean msgNotif, Boolean kakaoNotif) {
        this.emailNotif = emailNotif;
        this.msgNotif = msgNotif;
        this.kakaoNotif = kakaoNotif;
    }
}

