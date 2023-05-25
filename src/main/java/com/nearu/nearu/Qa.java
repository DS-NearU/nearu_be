package com.nearu.nearu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Qa {
    private Integer qaNo;
    private Integer userNo;
    private Boolean anonymous;
    private String date;
    private String title;
    private String question;

    public Qa() {
    }

    public Qa(Boolean anonymous, String date, String title, String question) {
        this.anonymous = anonymous;
        this.date = date;
        this.title = title;
        this.question = question;
    }
}
