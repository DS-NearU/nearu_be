package com.nearu.nearu.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class Qa {
    private Integer qaNo;
    private Integer userNo;
    private Boolean anonymous;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;
    private String title;
    private String question;


    public Qa() {
    }

    public Qa(Boolean anonymous, LocalDateTime date, String title, String question) {
        this.anonymous = anonymous;
        this.createdDt = date;
        this.title = title;
        this.question = question;
    }
}
