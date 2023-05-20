package com.nearu.nearu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notifications {
    private Integer userNo;
    private Boolean emailNotif;
    private Boolean msgNotif;
    private Boolean kakaoNotif;
}
