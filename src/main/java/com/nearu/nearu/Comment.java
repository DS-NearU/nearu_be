package com.nearu.nearu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private Integer commentNo;
    private String content;
    private String createdAt;
}
