package com.nearu.nearu.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {
    private Integer ratingNo;
    private Integer userNo;
    private Integer applicationNo;
    private Integer rating;
    private String comment;

    public Rating () {

    }

    public Rating(Integer rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
}


