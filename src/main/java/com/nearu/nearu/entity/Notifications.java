package com.nearu.nearu.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "notifications")
public class Notifications {

    @Id
    @OneToOne
    @Column(name = "user_no")
    private Integer userNo;

    @Column(name = "email_notif")
    private Boolean emailNotif;

    @Column(name = "msg_notif")
    private Boolean msgNotif;

    @Column(name = "kakao_notif")
    private Boolean kakaoNotif;

    public Notifications() {
    }

    public Notifications(Boolean emailNotif, Boolean msgNotif, Boolean kakaoNotif) {
        this.emailNotif = emailNotif;
        this.msgNotif = msgNotif;
        this.kakaoNotif = kakaoNotif;
    }
}

