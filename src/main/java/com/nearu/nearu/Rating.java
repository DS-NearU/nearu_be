package com.nearu.nearu;

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
}
